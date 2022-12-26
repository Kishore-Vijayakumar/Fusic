package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.Music;

import java.util.ArrayList;

public class ArtistAlbumLoader {

    ArrayList<Album> albumlist;



    public ArrayList<Album> loadArtistAlbum (Context context, String artist_id) {
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        String selection = " artist_id=" + artist_id;

        String sortOrder = MediaStore.Audio.Albums.ALBUM;

        String[] projection=new String[]
                { MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST,MediaStore.Audio.Albums._ID};
        Cursor cursor = contentResolver.query(uri, projection, selection, null, sortOrder);


        if (cursor != null && cursor.getCount() > 0) {
            albumlist = new ArrayList<Album>();
            while (cursor.moveToNext()) {

                /* String count=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID));*/

                String album_name=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
                // Log.e("STARTER","Name="+album_name);

                String album_artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST));
                // Log.e("STARTER","artist="+album_artist);

                // String no_songs=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS_FOR_ARTIST));

                //Log.e("STARTER","number="+no_songs);
                String album_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID));


                //String t=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //Log.e("STARTER","TITLE"+t);

                albumlist.add(new Album(album_id,album_name,album_artist,null));
            }
        }
        cursor.close();
        return albumlist;


    }
}
