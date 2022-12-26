package com.example.fusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fusic.Adapters.AlbumListAdapter;
import com.example.fusic.Adapters.Music;
import com.example.fusic.Loader.AlbumSongLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements AlbumListAdapter.ItemClicked{
    RecyclerView.Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Music> albumSongList;
    AlbumSongLoader albumSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        TextView album_title;
        Intent i = getIntent();
        //Bundle bundle = i.getExtras();
        String albumid = i.getStringExtra("album_id");
        String  title=i.getStringExtra("title");
        getSupportActionBar().setTitle("Album");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        album_title=findViewById(R.id.album_title);
        album_title.setText(title);


        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        albumSong=new AlbumSongLoader();
        albumSongList=new ArrayList<Music>();
        albumSongList=albumSong.loadAlbumSong(this,albumid);
        myAdapter=new AlbumListAdapter(this,albumSongList);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onItemClicked(int index) {
        //Toast.makeText(this,"Song name= "+albumSongList.get(index).getTitle(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PlayerActivity.class).putExtra("song", albumSongList)
                .putExtra("songname", albumSongList.get(index).getTitle())
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
