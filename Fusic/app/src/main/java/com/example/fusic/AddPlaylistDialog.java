package com.example.fusic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.fusic.Adapters.Music;
import com.example.fusic.Adapters.Playlist;
import com.example.fusic.Loader.PlaylistLoader;

import java.util.ArrayList;

public class AddPlaylistDialog extends AppCompatDialogFragment {


    ArrayList<Playlist> playlist;
    PlaylistLoader playlistLoader;
    Button create_playlist;
    ListView playlist_list;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //super.onCreateDialog(savedInstanceState);

        final String temp_id=getArguments().getString("audio_id");

      //  Toast.makeText(getContext(),"audio id = " + temp_id,Toast.LENGTH_SHORT).show();

        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.item_dialog,null);
        playlist_list=v.findViewById(R.id.playlist_list);
        create_playlist=v.findViewById(R.id.create_playlist);

        builder.setView(v)
                .setTitle("Playlist");
        /*
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });*/

        create_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewPlaylistDialog newPlaylistDialog = new NewPlaylistDialog();
                Bundle args = new Bundle();
                args.putString("id",temp_id);
                newPlaylistDialog.setArguments(args);
                newPlaylistDialog.show(getFragmentManager(),"New playlist");

               dismiss();
                //Toast.makeText(getContext(),"DISMISSED",Toast.LENGTH_SHORT).show();



            }
        });

        playlistLoader=new PlaylistLoader();
        playlist=playlistLoader.loadPlaylist(getContext());
        if(playlist!=null) {
            String[] name;
            name = new String[playlist.size()];

            for (int i = 0; i < playlist.size(); ++i) {

                name[i] = playlist.get(i).getPlaylist_name().toString();
            }


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, name);
            playlist_list.setAdapter(arrayAdapter);


            playlist_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   /* Toast.makeText(getContext(), "Playlist name = " + playlist.get(position).getPlaylist_name() +
                            " id = " +playlist.get(position).getPlaylist_id(), Toast.LENGTH_SHORT).show();*/

                    ArrayList<Music> musicList;
                    ContentResolver contentResolver = getContext().getContentResolver();

                    Uri uri = MediaStore.Audio.Playlists.Members.getContentUri("external",Long.parseLong(
                            playlist.get(position).getPlaylist_id()));
                    String selection = "audio_id = " + temp_id;
                    Cursor cursor = contentResolver.query(uri,null,selection,null,null);
                    if(cursor.getCount()<=0){
                        Toast.makeText(getContext(),"SONG ADDED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        SongAdder songAdder = new SongAdder();
                        songAdder.addSong(getContext(), temp_id, playlist.get(position).getPlaylist_id());
                    }
                    else{
                        Toast.makeText(getContext(),"SONG ALREADY EXIST",Toast.LENGTH_SHORT).show();

                    }

                  /*  if(cursor!=null && cursor.getCount() > 0){
                        Log.e("STARTER","IF STATEMENT ACCESSED==="+cursor.getCount());


                        while(cursor.moveToNext()){



                            String song_id=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.AUDIO_ID));


                            String song_name=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.TITLE));


                            Log.e("STARTER","Song name = " +song_name+ " id = " +song_id  );



                        }


                    }
                    cursor.close();*/




                    dismiss();

                }
            });
        }


        return builder.create();
    }
}
