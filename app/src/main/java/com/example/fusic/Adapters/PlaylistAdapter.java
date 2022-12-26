package com.example.fusic.Adapters;

import android.util.Log;
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

import com.example.fusic.PlaylistRemover;
import com.example.fusic.R;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>  {


    private ArrayList<Playlist> playlists;
    ItemClicked activity;
    Fragment tFragment;

    public interface ItemClicked
    {
        void onItemClicked(int index);
        void isEmpty(int flag);
    }

    public PlaylistAdapter (Fragment fragment, ArrayList<Playlist> play){

        playlists=play;
        activity=(ItemClicked) fragment;
        tFragment = fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView playlist_name,playlist_detail,playlist_option;
        ImageView playlist_art;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlist_name = itemView.findViewById(R.id.playlist_view_name);
            playlist_detail = itemView.findViewById(R.id.playlist_view_number);
            playlist_art = itemView.findViewById(R.id.playlist_art);
            playlist_option = itemView.findViewById(R.id.playlist_view_option);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(playlists.indexOf((Playlist) v.getTag()));
                    Log.e("STARTER","VIEW HOLDER");
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    //
                }
            });
        }
    }





    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist_view,parent,false);

        return new ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull final PlaylistAdapter.ViewHolder holder, final int position) {

        holder.itemView.setTag(playlists.get(position));
        holder.playlist_name.setText(playlists.get(position).getPlaylist_name());
       // holder.playlist_detail.setText(playlists.get(position).getPlaylist_id());


   /* Bitmap bitmap= ThumbnailUtils.createVideoThumbnail(albumArray.get(position).getAlbum_id(), MediaStore.Images.Thumbnails.MINI_KIND);
    holder.albumArt.setImageBitmap(bitmap);*/
        holder.playlist_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(v.getContext(), holder.playlist_option);
                popupMenu.inflate(R.menu.playlist_options);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.delete_playlist:
                                Toast.makeText(tFragment.getContext(),"Playlist deleted ",Toast.LENGTH_SHORT).show();
                                PlaylistRemover playlistRemover;
                                playlistRemover = new PlaylistRemover();
                                playlistRemover.deletePlaylist(tFragment.getContext(),playlists.get(position).getPlaylist_id());
                                playlists.remove(position);
                               notifyItemRemoved(position);
                               notifyItemRangeChanged(position,getItemCount());
                               if(getItemCount()==0){
                                   Log.e("STARTER","ZERO");
                                   activity.isEmpty(1);

                               }
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
        return playlists.size();
    }




}
