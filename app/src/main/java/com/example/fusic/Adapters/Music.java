package com.example.fusic.Adapters;

import java.io.File;
import java.io.Serializable;

public class Music implements Serializable {
    private String data;
    private String title;
    private String album;
    private String artist;


    private String album_id;

    public String getAudio_id() {
        return audio_id;
    }

    public void setAudio_id(String audio_id) {
        this.audio_id = audio_id;
    }

    private  String audio_id;
   // private String artist_id;

    public Music(String data, String title, String album, String artist, String album_id,String audio_id) {
        this.data = data;
        this.title = title;
        this.album = album;
        this.artist = artist;

        this.album_id = album_id;
        this.audio_id = audio_id;
        //this.artist_id = artist_id;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }



    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

   /* public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }
*/








}
