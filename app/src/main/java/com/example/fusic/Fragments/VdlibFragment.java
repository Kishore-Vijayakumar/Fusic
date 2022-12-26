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

import com.example.fusic.Activities.VideoFileActivity;
import com.example.fusic.Activities.VideoPlayerActivity;
import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.AlbumAdapter;
import com.example.fusic.Adapters.Folder;
import com.example.fusic.Adapters.VideoAdapter;
import com.example.fusic.Adapters.VideoFile;
import com.example.fusic.Adapters.VideoFileAdapter;
import com.example.fusic.Loader.VideoFileLoader;
import com.example.fusic.Loader.VideoLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class VdlibFragment extends Fragment implements VideoFileAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<VideoFile> videoList;
    VideoLoader videoLoader;

    View v;
    TextView noSongMessage;
    RecyclerView.Adapter myAdapter;

    ArrayList<Folder> folder;
    VideoFileLoader videoFileLoader;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_vdlib,container,false);



        noSongMessage=v.findViewById(R.id.noAlbumText_vdlib);

        getActivity().setTitle("Video");


        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
            Log.e("STARTER", "GRANTED");



           /* recyclerView=v.findViewById(R.id.video_view_recycler);
            recyclerView.setHasFixedSize(false);
            layoutManager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            videoLoader=new VideoLoader();
            videoList=new ArrayList<VideoFile>();
            videoList=videoLoader.loadVideo(getContext());

            if(videoList!=null) {
                myAdapter = new VideoAdapter(VdlibFragment.this, videoList);

                recyclerView.setAdapter(myAdapter);
            }
            else{
                noSongMessage.setText("No Media Present");
            }


        }
        else
        {
            noSongMessage.setText("No Storage Access");
        }*/
            recyclerView = v.findViewById(R.id.video_folder_view);
            layoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            videoFileLoader = new VideoFileLoader();
            folder = new ArrayList<Folder>();
            folder = videoFileLoader.loadFile(getContext());

            if (folder != null) {
                myAdapter = new VideoFileAdapter(VdlibFragment.this, folder);
                recyclerView.setAdapter(myAdapter);
            } else {
                noSongMessage.setText("No Media Present");
            }
        }
        else {
            noSongMessage.setText("No Storage Access");
        }

        return v;
    }


    @Override
    public void onItemClicked(int index) {

        //startActivity(new Intent(getActivity(), VideoPlayerActivity.class).putExtra("position", index).putExtra("array",videoList));
        startActivity(new Intent(getActivity(), VideoFileActivity.class).putExtra("bucketId", folder.get(index).getbId())
                .putExtra("bucketName",folder.get(index).getbName()));
    }

}
