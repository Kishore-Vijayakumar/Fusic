package com.example.fusic.Adapters;

import java.io.Serializable;

public class Artist implements Serializable {


    private String artistName;
    private String artistId;
    private String no_albums;
    private String no_tracks;


    public Artist(String artistName, String artistId, String no_albums, String no_tracks) {
        this.artistName = artistName;
        this.artistId = artistId;
        this.no_albums = no_albums;
        this.no_tracks = no_tracks;
    }
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getNo_albums() {
        return no_albums;
    }

    public void setNo_albums(String no_albums) {
        this.no_albums = no_albums;
    }

    public String getNo_tracks() {
        return no_tracks;
    }

    public void setNo_tracks(String no_tracks) {
        this.no_tracks = no_tracks;
    }


}
