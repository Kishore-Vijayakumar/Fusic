package com.example.fusic;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.fusic.Adapters.Playlist;
import com.example.fusic.Loader.PlaylistLoader;

import java.util.ArrayList;

public class PlaylistAdder  {



    public void addPlaylist(Context context,String playlist_name){

        ContentResolver contentResolver = context.getContentResolver();
        Uri u = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
        ContentValues values = new ContentValues();
        values.put(MediaStore.Audio.Playlists.NAME,playlist_name);
        Uri insertUri=contentResolver.insert(u,values);

         ArrayList<Playlist> templist ;
                            PlaylistLoader playlistLoader =new PlaylistLoader();
                            templist= playlistLoader.loadPlaylist(context);
                           int flag=-1;
                            for(int i=0; i<templist.size();++i){

                                if(templist.get(i).getPlaylist_name().equals(playlist_name)){

                                    flag=i;

                                }

                            }
                            if(flag>=0){

                                Log.e("STARTER","Name="+templist.get(flag).getPlaylist_name());
                            }
                            else{
                                Toast.makeText(context,"Error Creating Playlist",Toast.LENGTH_SHORT).show();
                            }





    }



}
