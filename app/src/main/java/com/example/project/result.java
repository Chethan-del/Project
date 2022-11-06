package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    TextView win;
    TextView win_score;
    MediaPlayer tada;
    Button play_again;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        play_again = (Button) findViewById(R.id.play_again);

        tada = MediaPlayer.create(result.this, R.raw.tada);
        tada.start();

        win = (TextView) findViewById(R.id.win);
        win_score = (TextView) findViewById(R.id.win_score);

        Bundle bundle = getIntent().getBundleExtra("data");
        win.setText(bundle.getString("result"));
        win_score.setText(bundle.getString("win_score"));

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(result.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        tada.release();
        finish();
    }

}