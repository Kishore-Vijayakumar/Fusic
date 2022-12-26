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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Activities.PlaylistSongsActivity;
import com.example.fusic.Adapters.Playlist;
import com.example.fusic.Adapters.PlaylistAdapter;
import com.example.fusic.Loader.PlaylistLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class PlaylistFragment extends Fragment implements PlaylistAdapter.ItemClicked{
    TextView noPlaylist;
    View v;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Playlist> playlists;
    PlaylistLoader playlistLoader;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_playlist,container,false);

        noPlaylist=v.findViewById(R.id.noPlaylistText);
        getActivity().setTitle("Playlist");
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Log.e("STARTER","GRANTED");

            recyclerView=   v.findViewById(R.id.playlist_grid_view);
            recyclerView.setHasFixedSize(false);
           layoutManager=new LinearLayoutManager(getContext());
           // layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            playlistLoader = new PlaylistLoader();
            playlists = new ArrayList<Playlist>();
            playlists = playlistLoader.loadPlaylist(getContext());
            if(playlists!=null) {
                myAdapter = new PlaylistAdapter(this, playlists);
                /*if(myAdapter.getItemCount()==0){
                    noPlaylist.setText("No Playlist Found");
                }
                else {*/
                    recyclerView.setAdapter(myAdapter);
                //}
            }
            else {
                noPlaylist.setText("No Playlist Found");
            }






        }
        else
        {
            noPlaylist.setText("No Storage Access");
        }



        return v;


    }


    @Override
    public void onItemClicked(int index) {
        //Toast.makeText(getContext()," playlist clicked ",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), PlaylistSongsActivity.class).putExtra("playlist_id", playlists.get(index).getPlaylist_id())
                .putExtra("playlist_name", playlists.get(index).getPlaylist_name()));

    }

    @Override
    public void isEmpty(int flag) {
        if(flag==1){
            noPlaylist.setText("No Playlist Found");
        }

    }
}
