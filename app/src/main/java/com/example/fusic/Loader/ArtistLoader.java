package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.Artist;

import java.util.ArrayList;

public class ArtistLoader {

    ArrayList<Artist> artistList;


    public ArrayList<Artist> loadArtist(Context context)
    {               Log.e("STARTER","ENTERED LOAD ALBUM");

        ContentResolver contentResolver=context.getContentResolver();
        Uri uri= MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

        String selection=MediaStore.Audio.Media.IS_MUSIC;

        String sortOrder= MediaStore.Audio.Artists.ARTIST + " ASC";
        // String sortOrder=MediaStore.Audio.Albums.ALBUM;
        String[] projection=new String[]
                { MediaStore.Audio.Artists.ARTIST, MediaStore.Audio.Artists._ID,MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,MediaStore.Audio.Artists.NUMBER_OF_TRACKS

                };
        Cursor cursor=contentResolver.query(uri,projection,null, null,sortOrder);

        if(cursor!=null && cursor.getCount() > 0){
            Log.e("STARTER","IF STATEMENT ACCESSED==="+cursor.getCount());

            artistList=new ArrayList<Artist>();
            while(cursor.moveToNext()){

                /* String count=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID));*/

                String artist_name=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
                // Log.e("STARTER","Name="+album_name);

                String artist_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists._ID));
                // Log.e("STARTER","artist="+album_artist);

                // String no_songs=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS_FOR_ARTIST));

                //Log.e("STARTER","number="+no_songs);
                String no_album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS));
                String no_tracks=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));


                //String t=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //Log.e("STARTER","TITLE"+t);

                artistList.add(new Artist(artist_name,artist_id,no_album,no_tracks));






            }


        }
        cursor.close();



        return artistList;
    }

}
