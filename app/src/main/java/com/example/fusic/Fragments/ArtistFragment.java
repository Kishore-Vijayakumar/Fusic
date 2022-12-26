package com.example.fusic.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Activities.ArtistAlbumActivity;
import com.example.fusic.Activities.DetailActivity;
import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.AlbumAdapter;
import com.example.fusic.Adapters.Artist;
import com.example.fusic.Adapters.ArtistAdapter;
import com.example.fusic.Loader.AlbumLoader;
import com.example.fusic.Loader.ArtistLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class ArtistFragment extends Fragment implements ArtistAdapter.ItemClicked {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Artist> artistList;
    ArtistLoader artistLoader;
    View v;
    TextView noSongMessage;
    RecyclerView.Adapter myAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_artist,container,false);


        noSongMessage=v.findViewById(R.id.noArtistText);

        getActivity().setTitle("Artist");


        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Log.e("STARTER","GRANTED");


            recyclerView=v.findViewById(R.id.artist_view);
            recyclerView.setHasFixedSize(false);
            layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            artistLoader=new ArtistLoader();
            artistList=new ArrayList<Artist>();
            artistList=artistLoader.loadArtist(getContext());
            if(artistList!=null) {


                myAdapter = new ArtistAdapter(ArtistFragment.this, artistList);

                recyclerView.setAdapter(myAdapter);
            }
            else{
                noSongMessage.setText("No Media Present");
            }



        }
        else
        {
            noSongMessage.setText("No Storage Access");
        }

        return v;



    }

    @Override
    public void onItemClicked(int index) {

       //  Toast.makeText(getActivity(),"Artist id= "+artistList.get(index).getArtistId(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), ArtistAlbumActivity.class).putExtra("artist_id", artistList.get(index).getArtistId())
                .putExtra("artist_title",artistList.get(index).getArtistName()));

    }
}
