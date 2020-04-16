package de.bitshifting.spotifyexample.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface SpotifyService {
    void retrieveArtistsExample(List<String> ids);
}
