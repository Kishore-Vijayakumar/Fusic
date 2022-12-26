package com.example.fusic.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import com.example.fusic.Adapters.Music;
import com.example.fusic.R;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {


    Button pause_button,previous_button,next_button;
    TextView song_name;
    SeekBar song_seek;
     MediaPlayer myMediaPlayer;
    int position;
    String sname;
    ArrayList<Music> mySongs;

    Runnable runnable;
    Handler handler;
    boolean flag=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        handler = new Handler();

        pause_button=findViewById(R.id.pause_button);
        previous_button=findViewById(R.id.previous_button);
        next_button=findViewById(R.id.next_button);
        song_name=findViewById(R.id.song_name);
        song_seek=findViewById(R.id.seekBar);

        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        if(myMediaPlayer!=null) {


            myMediaPlayer.stop();
            myMediaPlayer.release();
            //onDestroy();
        }

            Intent i = getIntent();
            Bundle bundle = i.getExtras();


            mySongs = (ArrayList) bundle.getParcelableArrayList("song");
            if(mySongs==null){
                Log.e("STARTER","MY SONGS IS NULL");

            }else             Log.e("STARTER","MY SONG NOT NULL");




        String songName = i.getStringExtra("songname");

            song_name.setText(songName);
            song_name.setSelected(true);

            position = bundle.getInt("pos", 0);
            if (position==0){
                Log.e("STARTER","POSITION IS NULL");

            }
        else             Log.e("STARTER","POSITION=" +position);


        final Uri u = Uri.parse(mySongs.get(position).getData().toString());
            if(u==null)
            {
                Log.e("STARTER","URI IS NULL");

            }
            else             Log.e("STARTER","U NOT  NULL u=" + getApplicationContext());

            myMediaPlayer=new MediaPlayer();
            myMediaPlayer.stop();
            myMediaPlayer.reset();
        myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
       /* try {
            myMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            Uri ui = Uri.parse(mySongs.get(position).getData());
            myMediaPlayer.setDataSource(this, ui);
            myMediaPlayer.prepare();
            //myMediaPlayer.setOnPreparedListener(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if(myMediaPlayer==null){
            Log.e("STARTER","MY MEDIA PLAYER NULL");

        }

            myMediaPlayer.start();

            song_seek.setMax(myMediaPlayer.getDuration());

            playCycle();



            song_seek.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
            song_seek.getThumb().setColorFilter(getResources().getColor(R.color.colorAccent),PorterDuff.Mode.SRC_IN);

            song_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                   // Log.e("STARTER","total = " +myMediaPlayer.getDuration()+ "current = " +progress);
                    if(progress==myMediaPlayer.getDuration()){
                        Log.e("STARTER","the end reached in onseek");
                        myMediaPlayer.pause();
                        myMediaPlayer.stop();

                        myMediaPlayer.release();



                        position=((position+1)%mySongs.size());
                        Uri u=Uri.parse(mySongs.get(position).getData().toString());



                        myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);


                        sname=mySongs.get(position).getTitle().toString();
                        song_name.setText(sname);



                        myMediaPlayer.start();

                        playCycle();
                        song_seek.setMax(myMediaPlayer.getDuration());
                    }

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    myMediaPlayer.seekTo(seekBar.getProgress());
                }
            });
            myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.e("STARTER","LISTENER END REACHED");
                }
            });

            pause_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    song_seek.setMax(myMediaPlayer.getDuration());
                    if(myMediaPlayer.isPlaying()){

                        pause_button.setBackgroundResource(R.drawable.icon_play);
                        myMediaPlayer.pause();

                        flag=true;

                    }
                    else
                    {
                        pause_button.setBackgroundResource(R.drawable.icon_pause);
                        myMediaPlayer.start();

                        playCycle();
                    }
                }
            });

            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    myMediaPlayer.pause();
                   myMediaPlayer.stop();

                    myMediaPlayer.release();


                    position=((position+1)%mySongs.size());
                    Uri u=Uri.parse(mySongs.get(position).getData().toString());


                    myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);


                    sname=mySongs.get(position).getTitle().toString();
                    song_name.setText(sname);



                    myMediaPlayer.start();

                    song_seek.setMax(myMediaPlayer.getDuration());
                    if(flag)
                    {
                        pause_button.setBackgroundResource(R.drawable.icon_pause);
                        flag=false;
                        playCycle();

                    }



                }
            });


            previous_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    position=((position-1)<0)?(mySongs.size()-1):(position-1);

                    Uri u=Uri.parse(mySongs.get(position).getData().toString());
                    myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);

                    sname=mySongs.get(position).getTitle().toString();
                    song_name.setText(sname);



                        myMediaPlayer.start();
                    song_seek.setMax(myMediaPlayer.getDuration());
                    if(flag)
                    {
                        pause_button.setBackgroundResource(R.drawable.icon_pause);
                        flag=false;
                        playCycle();

                    }

                }
            });



        }









    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();

        }

        myMediaPlayer.pause();

        return super.onOptionsItemSelected(item);
    }
    public  void playCycle()
    {
        song_seek.setProgress(myMediaPlayer.getCurrentPosition());
        if(myMediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };

            handler.postDelayed(runnable,500);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMediaPlayer.stop();
            //myMediaPlayer.release();
           // handler.removeCallbacks(runnable);
    }
}
