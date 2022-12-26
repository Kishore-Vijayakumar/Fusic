package com.example.fusic.Adapters;

public class Playlist {
    public String getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(String playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }



    private String playlist_id;
    private String playlist_name;



    public Playlist(String playlist_id, String playlist_name) {
        this.playlist_id = playlist_id;
        this.playlist_name = playlist_name;

    }
}
