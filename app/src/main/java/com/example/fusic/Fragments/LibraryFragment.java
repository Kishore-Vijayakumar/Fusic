package com.example.fusic.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fusic.Activities.SearchActivity;
import com.example.fusic.Adapters.Music;
import com.example.fusic.Activities.PlayerActivity;
import com.example.fusic.Adapters.MusicAdapter;
import com.example.fusic.R;
import com.example.fusic.Loader.MusicLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LibraryFragment extends Fragment implements MusicAdapter.ItemClicked{

    ListView myListViewForSongs;
   String[] items;
String[] t;
   ArrayList<Music> audiolist;
   TextView noSongMessage;
   MusicLoader musicLoader;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter myAdapter;






    View v;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_library,container,false);
        noSongMessage=v.findViewById(R.id.noSongText);


        getActivity().setTitle("Music Library");
             if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
                 Log.e("STARTER","GRANTED");
                 display();

             }
             else
             {
                 noSongMessage.setText("No Storage Access");
             }



        return  v;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void display()
    {



        recyclerView=v.findViewById(R.id.song_view);
        recyclerView.setHasFixedSize(false);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        musicLoader=new MusicLoader();
        audiolist=new ArrayList<Music>();
        audiolist= musicLoader.loadAudio(getContext());
        if(audiolist!=null) {
            myAdapter = new MusicAdapter(this, audiolist);
            recyclerView.setAdapter(myAdapter);
        }
        else{
            noSongMessage.setText("No Media Found");
        }

    }

    @Override
    public void onItemClicked(int index) {
        //Toast.makeText(this,"Song name= "+albumSongList.get(index).getTitle(),Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), PlayerActivity.class).putExtra("song", audiolist)
                .putExtra("songname", audiolist.get(index).getTitle())
                .putExtra("pos", index));
    }



  /*  @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {




        inflater.inflate(R.menu.drawer_search,menu);
        MenuItem menuItem = menu.findItem(R.id.toolbar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Music Library");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("STARTER","SUBMITED");



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("STARTER","CHANGED");
               // listAdapter.getFilter().filter(newText);
                startActivity(new Intent(getContext(), SearchActivity.class));
                return false;
            }
        });

       super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }
}
