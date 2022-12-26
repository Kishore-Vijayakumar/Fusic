package com.example.fusic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.fusic.Adapters.Playlist;
import com.example.fusic.Loader.PlaylistLoader;

import java.util.ArrayList;

public class NewPlaylistDialog extends AppCompatDialogFragment {

    EditText playlist_name;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final String audio_id;
        audio_id=getArguments().getString("id");
     //   Toast.makeText(getContext(),"audio id = " + id,Toast.LENGTH_SHORT).show();



        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.item_new_playlist,null);


        builder.setView(v)
                .setTitle("New Playlist")

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                      //  Toast.makeText(getContext(),"Canceled",Toast.LENGTH_SHORT).show();


                    }
                })
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Toast.makeText(getContext(),"Created",Toast.LENGTH_SHORT).show();
                        String name= playlist_name.getText().toString();
                       // Toast.makeText(getContext(),"Created playlist = " +name,Toast.LENGTH_SHORT).show();
                        if(name.isEmpty()){
                            Toast.makeText(getContext(),"Can't Create Playlist",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {   ArrayList<Playlist> checklist;
                            PlaylistLoader checkPlaylist = new PlaylistLoader();
                            checklist=checkPlaylist.loadPlaylist(getContext());
                            int checker=-1;
                            int size;
                            if(checklist==null){
                                size=0;
                            }
                            else{
                                size = checklist.size();
                            }

                            for (int i=0;i<size;++i)
                                {

                                    if(checklist.get(i).getPlaylist_name().equals(name)){

                                           checker=i;
                                        }

                                 }
                            if (checker>=0){
                                Toast.makeText(getContext(), "Playlist already exist!!", Toast.LENGTH_SHORT).show();


                            }
                            else {

                                PlaylistAdder playlistAdder = new PlaylistAdder();
                                playlistAdder.addPlaylist(getContext(), name);
                                //Toast.makeText(getContext(), "RETURNED", Toast.LENGTH_SHORT).show();


                                ArrayList<Playlist> templist;
                                PlaylistLoader playlistLoader = new PlaylistLoader();
                                templist = playlistLoader.loadPlaylist(getContext());
                                int flag = -1;
                                for (int i = 0; i < templist.size(); ++i) {

                                    if (templist.get(i).getPlaylist_name().equals(name)) {

                                        flag = i;

                                    }

                                }
                                if (flag >= 0) {

                                    Log.e("STARTER", "Name=" + templist.get(flag).getPlaylist_name());

                                    SongAdder songAdder = new SongAdder();
                                    songAdder.addSong(getContext(), audio_id, templist.get(flag).getPlaylist_id());

                                } else {
                                    Toast.makeText(getContext(), "Error Creating Playlist", Toast.LENGTH_SHORT).show();
                                }


                            }





                        }


                    }
                });

        playlist_name=v.findViewById(R.id.playlist_name);


        return builder.create();
    }






}
