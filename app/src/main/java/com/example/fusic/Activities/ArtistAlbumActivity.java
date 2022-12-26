package com.example.fusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.AlbumListAdapter;
import com.example.fusic.Adapters.ArtistAlbumAdapter;
import com.example.fusic.Adapters.Music;
import com.example.fusic.Loader.AlbumSongLoader;
import com.example.fusic.Loader.ArtistAlbumLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class ArtistAlbumActivity extends AppCompatActivity implements ArtistAlbumAdapter.ItemClicked{
    RecyclerView.Adapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Album> albumList;
    ArtistAlbumLoader artistAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_album);

        TextView artist_title;
        Intent i = getIntent();
        //Bundle bundle = i.getExtras();
        String artistId = i.getStringExtra("artist_id");
        String  artistTitle=i.getStringExtra("artist_title");
        getSupportActionBar().setTitle("Artist");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        artist_title=findViewById(R.id.artist_title);
        artist_title.setText(artistTitle);


        recyclerView=findViewById(R.id.artist_album_view);
        recyclerView.setHasFixedSize(false);
        layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        artistAlbum=new ArtistAlbumLoader();
        albumList=new ArrayList<Album>();
        albumList=artistAlbum.loadArtistAlbum(this,artistId);
        myAdapter=new ArtistAlbumAdapter(this,albumList);
        recyclerView.setAdapter(myAdapter);

    }
    @Override
    public void onItemClicked(int index) {
      //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, DetailActivity.class).putExtra("album_id", albumList.get(index).getAlbum_id())
                .putExtra("title",albumList.get(index).getAlbum_name()));
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
