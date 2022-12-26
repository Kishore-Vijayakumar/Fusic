package com.example.fusic.Adapters;

import android.graphics.BitmapFactory;
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

public class VideoFileAdapter extends RecyclerView.Adapter<VideoFileAdapter.ViewHolder> {

    VideoFileAdapter.ItemClicked activity;
    ArrayList<Folder> vFolder;


    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public VideoFileAdapter (Fragment fragment, ArrayList<Folder> folders){

        vFolder=folders;
        activity=(VideoFileAdapter.ItemClicked) fragment;

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView folder_title;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folder_title=itemView.findViewById(R.id.folder_title);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(vFolder.indexOf((Folder) v.getTag()));
                    //    mAdapterCallback.onItemClicked(albumArray.indexOf((Album) v.getTag()));
                    //  Toast.makeText(this,"Album id= "+albumList.get(index).getAlbum_id(),Toast.LENGTH_SHORT).show();
                    // Log.e("STARTER","HI HELLO");
                }
            });
        }
    }





    @Override
    public VideoFileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_folder,parent,false);

        return new VideoFileAdapter.ViewHolder(v);
    }




    @Override
    public void onBindViewHolder(@NonNull VideoFileAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(vFolder.get(position));
        holder.folder_title.setText(vFolder.get(position).getbName());





    }

    @Override
    public int getItemCount() {
        return vFolder.size();
    }





}
