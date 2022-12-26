package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Adapters.Music;

import java.util.ArrayList;
import java.util.HashSet;

public class MusicLoader  {
    ArrayList<Music> audiolist;



    public ArrayList<Music> loadAudio (Context context){
        ContentResolver contentResolver=context.getContentResolver();


        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String selection=MediaStore.Audio.Media.IS_MUSIC;

        String sortOrder= MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor=contentResolver.query(uri,null,selection, null,sortOrder);
        if(cursor!=null && cursor.getCount() > 0){
            audiolist=new ArrayList<Music>();
            while (cursor.moveToNext()){

                String data=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String audio_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

               // int t=cursor.getColumnIndex(MediaStore.Audio.Media._ID);
               Log.e("STARTER", " audio ="+ audio_id + "name =" +title);
               String alb_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
               // int i=cursor.getColumnIndex(MediaStore.Audio.Media._COUNT);

                Log.e("STARTER", " TITLE = " + title+" ALBUM NAME = " +album+ " ARTIST = " +artist+  "ID = " +audio_id );
                //int album_id=cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
              //  String album_key=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.));



                audiolist.add(new Music(data,title,album,artist,alb_id,audio_id));
            }
        }
        cursor.close();
        return audiolist;

    }






}
