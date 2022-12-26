package com.example.fusic.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.R;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {


    @NonNull
    private ArrayList<Artist> artistArray;
    ArtistAdapter.ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public ArtistAdapter (Fragment fragment, ArrayList<Artist> artist){

        artistArray=artist;
        activity=(ArtistAdapter.ItemClicked) fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView artist_title,content_detail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            artist_title=itemView.findViewById(R.id.album_title);
            content_detail=itemView.findViewById(R.id.album_details);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(artistArray.indexOf((Artist) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_album_view,parent,false);

        return new ArtistAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ViewHolder holder, int position) {
        String msg1=" album";
        holder.itemView.setTag(artistArray.get(position));
        holder.artist_title.setText(artistArray.get(position).getArtistName());
        holder.content_detail.setText(artistArray.get(position).getNo_albums()+ " album | "+artistArray.get(position).getNo_tracks()+ " tracks");




    }

    @Override
    public int getItemCount() {
        return artistArray.size();
    }






}
