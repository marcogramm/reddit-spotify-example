package de.bitshifting.spotifyexample.dto;

// Notice how the Spotify Response is different, if the result they return contains more than one record (i.e. "Artists")
// You need to account for that. Thats why i created a class "SpotifyPagingObject" reflecting the structure of their
// PagingObject. See: https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object
public class SpotifyArtistsResponse extends SpotifyPagingObject<SpotifyArtist> {

}
