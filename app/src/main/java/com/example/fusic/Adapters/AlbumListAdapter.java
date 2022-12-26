package com.example.fusic.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.R;

import java.util.ArrayList;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {

    @NonNull
    private ArrayList<Music> albumArray;
    AlbumListAdapter.ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public AlbumListAdapter(Context context, ArrayList<Music> album){

        albumArray=album;
        activity=(AlbumListAdapter.ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView song_name;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_name=itemView.findViewById(R.id.album_song_name);
            imageView=itemView.findViewById(R.id.album_art_list);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(albumArray.indexOf((Music) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public AlbumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album_list,parent,false);

        return new AlbumListAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull AlbumListAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(albumArray.get(position));
        holder.song_name.setText(albumArray.get(position).getTitle());
        /*Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(albumArray.get(position).getData(), MediaStore.Images.Thumbnails.MICRO_KIND);
        holder.imageView.setImageBitmap(bitmap);*/





    }

    @Override
    public int getItemCount() {
        return albumArray.size();
    }




}
