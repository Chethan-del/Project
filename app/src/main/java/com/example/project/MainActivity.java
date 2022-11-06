package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button single_player;
    Button multiple_player;

    MediaPlayer intromusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intromusic = MediaPlayer.create(MainActivity.this, R.raw.intro);
        intromusic.start();

        single_player = (Button) findViewById(R.id.single_player);
        multiple_player = (Button) findViewById(R.id.multiple_player);

        single_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_play_page(0);
            } //sending 0 if single_player
        });

        multiple_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_play_page(1);
            } //sending 1 if multiplayer
        });

    }

    public void open_play_page(int mode) {              //passing the mode value to play_page
        Intent it = new Intent(this, play_page.class);
        it.putExtra("mode", mode);
        startActivity(it);
    }


    @Override
    protected void onPause() {
        super.onPause();
        intromusic.release();
        finish();
    }

}