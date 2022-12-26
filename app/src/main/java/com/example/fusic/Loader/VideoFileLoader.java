package com.example.fusic.Loader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.fusic.Adapters.Folder;

import java.util.ArrayList;
import java.util.HashSet;

public class VideoFileLoader {
    ArrayList<String> bId;

    public ArrayList<Folder> loadFile (Context contex){

        ContentResolver contentResolver = contex.getContentResolver();
        Uri  u = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection=new String[]
                { MediaStore.Video.Media.BUCKET_ID};
        String sortOrder = MediaStore.Video.Media.BUCKET_DISPLAY_NAME + " ASC";
        Cursor cursor = contentResolver.query(u,projection,null,null,sortOrder);
        if(cursor!=null && cursor.getCount() > 0){
            bId = new ArrayList<String>();

            while(cursor.moveToNext()){

                bId.add(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID)));


            }


        }
        cursor.close();
      /*  for(int i=0;i<bId.size();++i){
            Log.e("STARTER  1","BID  = " +bId.get(i)+ " size " +bId.size());

        }*/

        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(bId);
        bId.clear();
        bId.addAll(hashSet);
        for(int i=0;i<bId.size();++i){
            Log.e("STARTER  2","BID  = " +bId.get(i)+ " size " +bId.size());

        }

        return getObject(contex);

    }
    ArrayList<Folder> getObject(Context tcontext){
        ArrayList<Folder> fileArray ;
        fileArray= new ArrayList<Folder>();
        ContentResolver tContentresolver = tcontext.getContentResolver();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection=new String[]
                { MediaStore.Video.Media.BUCKET_ID,MediaStore.Video.Media.DATA,MediaStore.Video.Media.BUCKET_DISPLAY_NAME};
        String sortOrder = MediaStore.Video.Media.BUCKET_DISPLAY_NAME + " ASC";

        for(int i =0;i< bId.size();++i){

            Log.e("SELECTED 1","BID  = " +bId.get(i)+ " size " +bId.size());
            String selection = "bucket_id="+bId.get(i);

            Cursor tcursor = tContentresolver.query(uri,projection,selection,null,sortOrder);

            if (tcursor != null && tcursor.getCount() > 0) {
             //   fileArray = new ArrayList<Folder>();
                tcursor.moveToNext();
               // while (i<bId.size()) {

                    String data = tcursor.getString(tcursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    String id =     tcursor.getString(tcursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID));
                    String name = tcursor.getString(tcursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));

                    Log.e("SELECTED  2","BID  = " +id+ " NAME " +name+ "CHECK ID = " +bId.get(i));

                    fileArray.add(new Folder(data,id,name));
              //  }

            }
            tcursor.close();


        }




        return fileArray;
    }

}
