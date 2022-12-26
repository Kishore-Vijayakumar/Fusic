package com.example.fusic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Adapters.Music;
import com.example.fusic.Adapters.PlaylistSongAdapter;
import com.example.fusic.Loader.PlaylistSongLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class PlaylistSongsActivity extends AppCompatActivity implements PlaylistSongAdapter.ItemClicked {
    RecyclerView.Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Music> playlist_song;
    PlaylistSongLoader playlistSongLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_song);
        TextView playlist_folder_title,noPlaylist;
        noPlaylist = findViewById(R.id.noPlaylistSong);
        Intent i = getIntent();
        String playlist_id = i.getStringExtra("playlist_id");
        String  playlist_title=i.getStringExtra("playlist_name");
        getSupportActionBar().setTitle("Playlist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        playlist_folder_title=findViewById(R.id.playlist_folder_title);
        playlist_folder_title.setText(playlist_title);


        recyclerView=findViewById(R.id.recycler_playlist_song);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        playlistSongLoader=new PlaylistSongLoader();
        playlist_song =new ArrayList<Music>();

        playlist_song=playlistSongLoader.loadPlaylistSong(this,playlist_id);
        if(playlist_song!=null) {
            myAdapter = new PlaylistSongAdapter(this, playlist_song, playlist_id);
            Log.e("STARTER", "REACHED");
      /*  runOnUiThread(new Runnable() {
            public void run() {
                myAdapter.notifyDataSetChanged();
            }
        });*/
            //myAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(myAdapter);
        }
        else {
            noPlaylist.setText("Empty Playlist");
        }


    }
    /*public void dataChanged(){
        myAdapter.notifyDataSetChanged();

    }
    */



    @Override
    public void onItemClicked(int index) {
        /*Toast.makeText(this,"Song clicked= ",Toast.LENGTH_SHORT).show();*/
       startActivity(new Intent(this, PlayerActivity.class).putExtra("song", playlist_song)
                .putExtra("songname", playlist_song.get(index).getTitle())
                .putExtra("pos", index));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();

        }
        return super.onOptionsItemSelected(item);
    }


}
