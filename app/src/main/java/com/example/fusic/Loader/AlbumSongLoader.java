package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.fusic.Adapters.Music;

import java.util.ArrayList;

public class AlbumSongLoader {

    ArrayList<Music> audiolist;



    public ArrayList<Music> loadAlbumSong (Context context,String album_id) {
        ContentResolver contentResolver = context.getContentResolver();



                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String selection = "is_music!=0 AND album_id=" + album_id;

        String sortOrder = MediaStore.Audio.Media.TITLE;

        /*String[] projection=new String[]
                { MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST,MediaStore.Audio.Albums._ID};*/
        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);


        if (cursor != null && cursor.getCount() > 0) {
            audiolist = new ArrayList<Music>();
            while (cursor.moveToNext()) {

                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String audio_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                // int t=cursor.getColumnIndex(MediaStore.Audio.Media._ID);
                // Log.e("STARTER", " id="+ id + "t="+t);
                String alb_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
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
