package com.example.fusic.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fusic.AddPlaylistDialog;
import com.example.fusic.Fragments.LibraryFragment;
import com.example.fusic.R;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder>{

    private ArrayList<Music> musicArray;
    MusicAdapter.ItemClicked activity;
   Fragment tFragment;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }



    public MusicAdapter (Fragment fragment, ArrayList<Music> music){

        musicArray=music;
        activity=(MusicAdapter.ItemClicked) fragment;
        tFragment=fragment;


    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView music_name,song_artist,song_option;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            music_name=itemView.findViewById(R.id.music_name);
            song_artist=itemView.findViewById(R.id.song_artist);
            song_option=itemView.findViewById(R.id.song_option);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(musicArray.indexOf((Music) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song_list,parent,false);

        return new MusicAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull final MusicAdapter.ViewHolder holder, final int position) {

        holder.itemView.setTag(musicArray.get(position));
        holder.music_name.setText(musicArray.get(position).getTitle());
        holder.song_artist.setText(musicArray.get(position).getArtist());
        holder.song_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu=new PopupMenu(v.getContext(),holder.song_option);
                popupMenu.inflate(R.menu.song_options);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.add_to_playlist:
                                Toast.makeText(tFragment.getContext(),"Added to Playlist ",Toast.LENGTH_SHORT).show();
                                AddPlaylistDialog addPlaylistDialog=new AddPlaylistDialog();
                                Bundle args = new Bundle();
                                args.putString("audio_id",musicArray.get(position).getAudio_id());
                                addPlaylistDialog.setArguments(args);
                                addPlaylistDialog.show(tFragment.getFragmentManager(),"Example dialog");


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
        return musicArray.size();
    }










}
