package com.example.fusic.Adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.R;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    @NonNull
    private ArrayList<VideoFile> videoList;
    VideoAdapter.ItemClicked activity;
    ContentResolver contentResolver;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public VideoAdapter (Context context, ArrayList<VideoFile> video){

        videoList=video;
        activity=(VideoAdapter.ItemClicked) context;
        contentResolver=context.getContentResolver();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView vd_name;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vd_name=itemView.findViewById(R.id.vd_name);
            imageView=itemView.findViewById(R.id.iv_thmnail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(videoList.indexOf((VideoFile) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_view,parent,false);

        return new VideoAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        BitmapFactory.Options options=new BitmapFactory.Options();
        holder.itemView.setTag(videoList.get(position));
        holder.vd_name.setText(videoList.get(position).getVdname());
       // Bitmap bitmap= MediaStore.Video.Thumbnails.getThumbnail(contentResolver,videoList.get(position).getVdid(),MediaStore.Video.Thumbnails.MICRO_KIND,options);
        Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(videoList.get(position).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.imageView.setImageBitmap(bitmap);

   // Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(albumArray.get(position).getAlbum_id(), MediaStore.Images.Thumbnails.MINI_KIND);
   // holder.albumArt.setImageBitmap(bitmap);




    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }




}
