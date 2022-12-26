package com.example.fusic.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Activities.DetailActivity;
import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.AlbumAdapter;
import com.example.fusic.Loader.AlbumLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class AlbumFragment extends Fragment implements AlbumAdapter.ItemClicked {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Album> albumList;
    AlbumLoader albumLoader;
    View v;
    TextView noSongMessage;
    RecyclerView.Adapter myAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_album,container,false);

        noSongMessage=v.findViewById(R.id.noAlbumText);

        getActivity().setTitle("Album");


        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Log.e("STARTER","GRANTED");



            recyclerView=v.findViewById(R.id.album_view);
            recyclerView.setHasFixedSize(false);
            layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            albumLoader=new AlbumLoader();
            albumList=new ArrayList<Album>();
            albumList=albumLoader.loadAlbum(getContext());
            if(albumList!=null) {

                myAdapter = new AlbumAdapter(AlbumFragment.this, albumList);

                recyclerView.setAdapter(myAdapter);
            }
            else
            {
                noSongMessage.setText("No Media Found");
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
       // Toast.makeText(getActivity(),"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();

        startActivity(new Intent(getActivity(), DetailActivity.class).putExtra("album_id", albumList.get(index).getAlbum_id())
                .putExtra("title",albumList.get(index).getAlbum_name()));
    }



}
