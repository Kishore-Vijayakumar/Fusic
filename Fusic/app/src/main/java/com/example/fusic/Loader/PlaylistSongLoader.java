package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.fusic.Adapters.Music;

import java.util.ArrayList;

public class PlaylistSongLoader {

    ArrayList<Music> audiolist;



    public ArrayList<Music> loadPlaylistSong (Context context, String playlist_id) {
        ContentResolver contentResolver = context.getContentResolver();



        Uri uri = MediaStore.Audio.Playlists.Members.getContentUri("external",Long.parseLong(playlist_id));



        String sortOrder = MediaStore.Audio.Playlists.Members.TITLE;


        Cursor cursor = contentResolver.query(uri, null, null, null, sortOrder);


        if (cursor != null && cursor.getCount() > 0) {
            audiolist = new ArrayList<Music>();
            while (cursor.moveToNext()) {

                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.ARTIST));
                String audio_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID));
                // int t=cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                // Log.e("STARTER", " id="+ id + "t="+t);
                String alb_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.ALBUM_ID));
                // int i=cursor.getColumnIndex(MediaStore.Audio.Media._COUNT);

                //Log.e("STARTER", " count="+ count+" alb name="+album+ " artist="+artist+ "title="+title );
                //int album_id=cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                //String album_key=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY));


                audiolist.add(new Music(data, title, album, artist, alb_id,audio_id));
            }
        }
        cursor.close();
        return audiolist;


    }





}
