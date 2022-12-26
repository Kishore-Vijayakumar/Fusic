package com.example.fusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fusic.Adapters.VideoAdapter;
import com.example.fusic.Adapters.VideoFile;
import com.example.fusic.Loader.VideoLoader;
import com.example.fusic.R;

import java.util.ArrayList;

public class VideoFileActivity extends AppCompatActivity implements VideoAdapter.ItemClicked{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<VideoFile> videoList;
    VideoLoader videoLoader;
    RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_file);
        Intent i = getIntent();

        String bucket_Id = i.getStringExtra("bucketId");
        String bucket_name=i.getStringExtra("bucketName");
        getSupportActionBar().setTitle(bucket_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.video_view_recycler);
        recyclerView.setHasFixedSize(false);
        layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        videoLoader=new VideoLoader();
        videoList=new ArrayList<VideoFile>();
        videoList=videoLoader.loadVideo(this,bucket_Id);

        myAdapter = new VideoAdapter(this, videoList);

        recyclerView.setAdapter(myAdapter);



    }

    @Override
    public void onItemClicked(int index) {

       startActivity(new Intent(this, VideoPlayerActivity.class).putExtra("position", index).putExtra("array",videoList));
        /*startActivity(new Intent(getActivity(), VideoFileActivity.class).putExtra("bucketId", folder.get(index).getbId())
                .putExtra("bucketName",folder.get(index).getbName()));*/
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
