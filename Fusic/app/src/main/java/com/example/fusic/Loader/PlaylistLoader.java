package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.Playlist;

import java.util.ArrayList;

public class PlaylistLoader {


    ArrayList<Playlist> playlist;


    public ArrayList<Playlist> loadPlaylist(Context context)
    {
        ContentResolver contentResolver=context.getContentResolver();
        Uri uri= MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

      //  String selection=MediaStore.Audio.Playlists;
        // MediaStore.VideoFile.Media.

        String sortOrder= MediaStore.Audio.Playlists.NAME + " ASC";
        // String sortOrder=MediaStore.Audio.Albums.ALBUM;
        String[] projection=new String[]
                { MediaStore.Audio.Playlists._ID, MediaStore.Audio.Playlists.NAME};
        Cursor cursor=contentResolver.query(uri,projection,null, null,sortOrder);

        if(cursor!=null && cursor.getCount() > 0){
            Log.e("STARTER","IF STATEMENT ACCESSED==="+cursor.getCount());

            playlist=new ArrayList<Playlist>();
            while(cursor.moveToNext()){

                /* String count=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID));*/

                String playlist_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists._ID));
                //Log.e("STARTER","Name="+album_name);

                String playlist_name=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.NAME));
                // Log.e("STARTER","artist="+album_artist);

                //String no_songs=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.));

                //Log.e("STARTER","number="+no_songs);
               // String song_count=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.PlaylistsColum));


               // String t=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID));
                Log.e("STARTER","Playlist name = " +playlist_name+ " id = " +Long.parseLong(playlist_id)+ " audio id = "  );
                //Log.e("STARTER","TITLE"+t);

                playlist.add(new Playlist(playlist_id,playlist_name));






            }


        }
        cursor.close();



        return playlist;
    }




}
