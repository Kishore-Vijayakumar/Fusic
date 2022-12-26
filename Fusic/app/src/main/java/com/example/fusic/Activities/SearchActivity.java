package com.example.fusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fusic.Adapters.Music;
import com.example.fusic.Loader.MusicLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<Music> musicList,tempList;
    MusicLoader musicLoader;
    TextView noSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        listView=findViewById(R.id.listView_list);
        noSearch=findViewById(R.id.noSearch);

        musicLoader = new MusicLoader();
        musicList = new ArrayList<Music>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        musicList = musicLoader.loadAudio(this);

        if(musicList!=null) {

            String[] name;
            name = new String[musicList.size()];
            for (int i = 0; i < musicList.size(); ++i) {

                name[i] = musicList.get(i).getTitle();
            }


            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
            listView.setAdapter(arrayAdapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int tempPos = -1;
                    Log.e("STARTER", "pos = " + arrayAdapter.getPosition(arrayAdapter.getItem(position)) + " id = " + id);
                    for (int i = 0; i < musicList.size(); ++i) {

                        if (musicList.get(i).getTitle().equals(arrayAdapter.getItem(position))) {
                            tempPos = i;
                            break;
                        }

                    }
                    ArrayList<Music> tempList;
                    tempList = new ArrayList<Music>();
                    tempList.add(new Music(musicList.get(tempPos).getData(), musicList.get(tempPos).getTitle(), musicList.get(tempPos).getAlbum()
                            , musicList.get(tempPos).getArtist(), musicList.get(tempPos).getAlbum_id(), musicList.get(tempPos).getAudio_id()));

                    startActivity(new Intent(SearchActivity.this, PlayerActivity.class).putExtra("song", tempList)
                            .putExtra("songname", tempList.get(0).getTitle())
                            .putExtra("pos", 0));
                }
            });


        }
        else{
            noSearch.setText("No Media Found");
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.drawer_search,menu);

        MenuItem menuItem = menu.findItem(R.id.toolbar_search);
        menu.performIdentifierAction(R.id.toolbar_search,0);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Music Library");
        if(musicList!=null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.e("STARTER", "SUBMITED");


                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.e("STARTER", "CHANGED");

                    arrayAdapter.getFilter().filter(newText);


                    //listView.setAdapter(arrayAdapter);
                    // tempList = new ArrayList<Music>();


                    return false;
                }
            });
        }
            menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    Log.e("STARTER", "EXPANDED");

                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    Log.e("STARTER", "CLOSED");
                    // onBackPressed();
                    finish();
                    return true;
                }
            });


        return super.onCreateOptionsMenu(menu);


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
