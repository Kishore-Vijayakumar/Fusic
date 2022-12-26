package com.example.fusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fusic.Fragments.AlbumFragment;
import com.example.fusic.Fragments.ArtistFragment;
import com.example.fusic.Fragments.LibraryFragment;
import com.example.fusic.Fragments.PlaylistFragment;
import com.example.fusic.Fragments.VdlibFragment;
import com.example.fusic.R;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;





    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar;
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        runtimePermission();

        if(savedInstanceState ==null) {
          getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LibraryFragment()).commit();
           navigationView.setCheckedItem(R.id.library);
            Log.e("STARTER","FIRST");
            }





    }



    public void runtimePermission()
    {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)//
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LibraryFragment()).commitAllowingStateLoss();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                        token.continuePermissionRequest();
                    }


                })




               /* .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        Log.e("STARTER","HERE");
                      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LibraryFragment()).commitAllowingStateLoss();

                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })*/ .check();


    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.library:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LibraryFragment()).commit();
                break;

            case R.id.artist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ArtistFragment()).commit();
                break;
            case R.id.album:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AlbumFragment()).commit();
                break;
            case R.id.playlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PlaylistFragment()).commit();
                break;
            case R.id.video:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new VdlibFragment()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

      getMenuInflater().inflate(R.menu.search_icon,menu);
      /*  MenuItem menuItem=menu.findItem(R.id.toolbar_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Music Library");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;


            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;


            }
        });*/

      //  super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            switch (item.getItemId()){
                case R.id.search_icon:
                   // Toast.makeText(this,"SEARCH",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, SearchActivity.class));
                    break;
                default:
                    break;
            }

        }
        else
        {

        }




        return super.onOptionsItemSelected(item);
    }
}
