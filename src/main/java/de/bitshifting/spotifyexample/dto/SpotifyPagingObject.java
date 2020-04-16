package de.bitshifting.spotifyexample.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

// PagingObject. See: https://developer.spotify.com/documentation/web-api/reference/object-model/#paging-object
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyPagingObject<T> {
    @JsonProperty(value = "limit")
    private Integer currentResults;

    @JsonProperty(value = "total")
    private Integer totalResults;

    @JsonProperty(value = "next")
    private String nextResultURL;

    @JsonProperty(value = "previous")
    private String previousResultURL;

    @JsonProperty(value = "items")
    private List<T> items;
}
