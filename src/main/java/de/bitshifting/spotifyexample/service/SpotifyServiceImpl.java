package de.bitshifting.spotifyexample.service;

import de.bitshifting.spotifyexample.dto.SpotifyArtist;
import de.bitshifting.spotifyexample.dto.SpotifyArtistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
@lombok.Getter
@lombok.Setter
@lombok.RequiredArgsConstructor
@Slf4j
public class SpotifyServiceImpl implements SpotifyService {
    private static final String BASE_URL = "https://api.spotify.com/v1/";
    private static final String ARTISTS_ENPOINT = "artists";

    //This will be autowired by the @lombok.RequiredArgsConstructor
    private final RestTemplate restTemplate;

    @Override
    public void retrieveArtistsExample(List<String> ids) {
        // I am construction the Request URI with the help of the URIComponentsBuilder class.
        // The outcome is the same as your constructed URI, its just a bit more readable and convenient
        // You can stick to your way though!
        if (Objects.nonNull(ids)) {
            final URI uri = UriComponentsBuilder
                    .fromUriString(BASE_URL)
                    .pathSegment(ARTISTS_ENPOINT)
                    .queryParam("id", ids)
                    .build()
                    .toUri();

            // Now you can issue a http get request to the URI we just built above.
            // We give the getForEntity() method a reference to the uri it should issue the http-get to, as well
            // as the class we would like the json marshaller (Jackson in our case) to convert the response to.
            // Convenient enough, we created the SpotifyArtistsResponse class earlier (look into the "dto" package i created
            // in this project as well to see how i mapped the Spotify Json structure), reflection the JSON structure
            // Spotify is giving as in their documentation
            ResponseEntity<SpotifyArtistsResponse> responseEntity = restTemplate.getForEntity(uri, SpotifyArtistsResponse.class);

            // Now we check if the status of the response was a "200 - OK". If it was, we can go on
            // (if it wasnt, we just ignore what happened and dont actually do any error handling here, but this is out of scope)
            if (responseEntity.getStatusCode().is2xxSuccessful() && Objects.nonNull(responseEntity.getBody())) {
                // The response had a status code of 200 - Ok and the body wasnt null, so we can safely retrieve our
                // already mapped response body
                SpotifyArtistsResponse response = responseEntity.getBody();

                // Now you have access to the SpotifyArtistsResponse (as mapped in the dto package)
                log.info(response.getNextResultURL());
                log.info(response.getPreviousResultURL());

                List<SpotifyArtist> artists = response.getItems();

                artists.forEach(spotifyArtist -> {
                    // You have access to all the fields of the SpotifyArtist class here as well
                    log.info(spotifyArtist.getHref());
                    log.info(spotifyArtist.getId());
                    log.info(spotifyArtist.getFollowers().getHref());
                    log.info(spotifyArtist.getFollowers().getTotal().toString());
                });
            }
        }
    }
}
