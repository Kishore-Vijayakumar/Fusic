package com.example.fusic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {




    @NonNull
    private ArrayList<Album> albumArray;
    ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public AlbumAdapter (Fragment fragment, ArrayList<Album> album){

        albumArray=album;
        activity=(ItemClicked) fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView album_title,album_details;
        ImageView albumArt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            album_title=itemView.findViewById(R.id.album_title);
            album_details=itemView.findViewById(R.id.album_details);
            albumArt=itemView.findViewById(R.id.album_art);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                  //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                   // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_album_view,parent,false);

        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {

    holder.itemView.setTag(albumArray.get(position));
    holder.album_title.setText(albumArray.get(position).getAlbum_name());
    holder.album_details.setText(albumArray.get(position).getArtist_name());
   /* Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(albumArray.get(position).getAlbum_id(), MediaStore.Images.Thumbnails.MINI_KIND);
    holder.albumArt.setImageBitmap(bitmap);*/




    }

    @Override
    public int getItemCount() {
        return albumArray.size();
    }






}
