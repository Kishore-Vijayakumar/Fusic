package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.fusic.Adapters.Album;
import com.example.fusic.Adapters.VideoFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VideoLoader {
    ArrayList<VideoFile> videoList;


    public ArrayList<VideoFile> loadVideo(Context context,String bucket_Id)
    {               Log.e("STARTER","ENTERED LOAD ALBUM");

        ContentResolver contentResolver=context.getContentResolver();
        Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String selection = "bucket_id="+bucket_Id;
        // MediaStore.VideoFile.Media.

        String sortOrder= MediaStore.Video.Media.DISPLAY_NAME + " ASC";
        // String sortOrder=MediaStore.Audio.Albums.ALBUM;*/
        String[] projection=new String[]
                { MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA,MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME,
                        MediaStore.Video.VideoColumns.BUCKET_ID,MediaStore.Video.Media.RESOLUTION};
       /* String[] projection=new String[]
                { MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Video.Media.BUCKET_ID};*/

        Cursor cursor=contentResolver.query(uri,projection,selection , null,sortOrder);

        if(cursor!=null && cursor.getCount() > 0){
           // Log.e("STARTER","IF STATEMENT ACCESSED==="+cursor.getCount());

            videoList=new ArrayList<VideoFile>();

            while(cursor.moveToNext()){



               String video_name=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));


                String video_path=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));


                 String bName=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String bId=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID));



                String video_id=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID));

                String temp=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION));
                   Log.e("STARTER","RESOLUTION = " +temp);

              //  Log.e("STARTER","Volume name = " +vName+ " Id = " +vNum+ " TITLE = " +video_name+ "PATH = " +video_path);

                videoList.add(new VideoFile(video_path,video_name,video_id,bName,bId));







            }


        }
        cursor.close();




        return videoList;
    }



}
