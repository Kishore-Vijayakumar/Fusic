package com.example.fusic;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class SongAdder {


    public void addSong(Context context, String audio_id, String playlist_id){

        ContentResolver contentResolver = context.getContentResolver();
        String[] cols = new String[] {
                "count(*)"
        };
        Uri u = MediaStore.Audio.Playlists.Members.getContentUri("external",Long.parseLong(playlist_id));
        Cursor cursor = contentResolver.query(u,cols,null,null,null);
        cursor.moveToNext();
        final int base = cursor.getInt(0);
        cursor.close();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER,Integer.valueOf(base + audio_id));
        contentValues.put(MediaStore.Audio.Playlists.Members.AUDIO_ID,audio_id);
        contentResolver.insert(u,contentValues);





    }



}
