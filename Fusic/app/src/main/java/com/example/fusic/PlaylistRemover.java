package com.example.fusic;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class PlaylistRemover  {


    public  void deletePlaylist(Context context ,  String playlist_id) {
        ContentResolver resolver = context.getContentResolver();
        String[] cols = new String[] {
                "count(*)"
        };
        Uri uri = MediaStore.Audio.Playlists.getContentUri("external");
        Cursor cur = resolver.query(uri, cols, null, null, null);
        cur.moveToFirst();
        final int base = cur.getInt(0);
        cur.close();
        ContentValues values = new ContentValues();

        resolver.delete(uri, MediaStore.Audio.Playlists._ID +" = "+playlist_id, null);
    }

}
