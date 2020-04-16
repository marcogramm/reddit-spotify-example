package de.bitshifting.spotifyexample.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// the lombok.Data annotation is creating Getters and Setter, plus constructors and a toString() method for all fields for me
// This is just convenient - no boilerplate code
// But: you should stick with writing out all the getters/setters/toString() methods yourself for now.
@lombok.Data
// This Jackson-Annotation is tell jackson that, if the json of Spotify contains any other data we didnt map below,
// it should just ignore that data.
// See: https://developer.spotify.com/documentation/web-api/reference/artists/get-artist/
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtist {
    // the @JsonProperty annotation tell Jackson, that the Spotify Response we expect to get has a json-field name "id"
    // and that it should map this field to our class field "String id"
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "href")
    private String href;

    @JsonProperty(value = "genres")
    List<String> genres;

    @JsonProperty(value = "name")
    String artistName;

    // Notice how the Spotify Response for Artists is a nested Json structure according to their documentation
    // We account for that, in that we created a nested structure as well, reflection Spotifies nested structure
    @JsonProperty(value = "followers")
    private FollowerData followers;


    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class FollowerData {
        @JsonProperty(value = "href")
        private String href;

        @JsonProperty(value = "total")
        private Integer total;
    }

}
