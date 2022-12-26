package com.example.fusic.Adapters;

import java.io.Serializable;

public class Album implements Serializable {
    private String album_id;
    private  String album_name;


    private  String no_Songs;



    private  String artist_name;
    public Album(String album_id, String album_name,String artist_name,String no_Songs) {
        this.album_id = album_id;
        this.album_name = album_name;
        this.artist_name=artist_name;
        this.no_Songs=no_Songs;
    }

    public Album() {
        album_id="";
        album_name="";
        no_Songs="";
        artist_name="";
    }

    public String getNo_Songs() {
        return no_Songs;
    }

    public void setNo_Songs(String no_Songs) {
        this.no_Songs = no_Songs;
    }


    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }




}
