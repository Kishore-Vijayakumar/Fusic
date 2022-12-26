package com.example.fusic.Adapters;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.Activities.PlaylistSongsActivity;
import com.example.fusic.R;
import com.example.fusic.SongRemover;

import java.util.ArrayList;

public class PlaylistSongAdapter    extends RecyclerView.Adapter<PlaylistSongAdapter.ViewHolder> {

    private ArrayList<Music> songArray;
    PlaylistSongAdapter.ItemClicked activity;
    Context tFragment;
    String playlist_id;
    PlaylistSongsActivity playlistSongsActivity ;


    public interface ItemClicked
    {
        void onItemClicked(int index);
    }


    public PlaylistSongAdapter(Context context, ArrayList<Music> song,String id){

        songArray=song;
        activity=(PlaylistSongAdapter.ItemClicked) context;
        tFragment=context;
        playlist_id = id;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView song_name,song_option;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            song_name=itemView.findViewById(R.id.playlist_song_name);
            song_option=itemView.findViewById(R.id.playlist_song_option);
            imageView=itemView.findViewById(R.id.playlist_song_art);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                    activity.onItemClicked(songArray.indexOf((Music) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public PlaylistSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_song_view,parent,false);



        return new PlaylistSongAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull final PlaylistSongAdapter.ViewHolder holder, final int position) {

        holder.itemView.setTag(songArray.get(position));
        holder.song_name.setText(songArray.get(position).getTitle());

        holder.song_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu=new PopupMenu(v.getContext(), holder.song_option);
                popupMenu.inflate(R.menu.playlist_song_options);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.remove_song:
                                Toast.makeText(tFragment,"Song removed ",Toast.LENGTH_SHORT).show();
                                SongRemover songRemover;
                                songRemover =new SongRemover();
                                songRemover
                                        .removeFromPlaylist(tFragment,songArray.get(position).getAudio_id(),playlist_id);
                                        //notifyItemChanged(holder.getAdapterPosition());
                                        songArray.remove(position);
                                        notifyItemRemoved(position);

                                break;

                            default:
                                break;


                        }



                        return false;
                    }
                });

                popupMenu.show();

            }
        });






    }

    @Override
    public int getItemCount() {
        return songArray.size();
    }




}
