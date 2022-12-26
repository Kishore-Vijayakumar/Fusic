package com.example.fusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.R;

import java.util.ArrayList;

public class ArtistAlbumAdapter extends RecyclerView.Adapter<ArtistAlbumAdapter.ViewHolder> {


    @NonNull
    private ArrayList<Album> albumArray;
    ArtistAlbumAdapter.ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public ArtistAlbumAdapter (Context context, ArrayList<Album> album){

        albumArray=album;
        activity=(ArtistAlbumAdapter.ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView album_title,album_details;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            album_title=itemView.findViewById(R.id.album_title);
            album_details=itemView.findViewById(R.id.album_details);

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
    public ArtistAlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_album_view,parent,false);

        return new ArtistAlbumAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull ArtistAlbumAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(albumArray.get(position));
        holder.album_title.setText(albumArray.get(position).getAlbum_name());
        holder.album_details.setText(albumArray.get(position).getArtist_name());




    }

    @Override
    public int getItemCount() {
        return albumArray.size();
    }


}
