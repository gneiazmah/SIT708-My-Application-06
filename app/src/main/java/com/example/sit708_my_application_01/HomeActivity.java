package com.example.sit708_my_application_01;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    EditText link;

    Button play, add, playlist;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        link = findViewById(R.id.etLink);

        play = findViewById(R.id.btnPlay);
        add = findViewById(R.id.btnAddPlayList);
        playlist = findViewById(R.id.btnPlayList);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int savedAttempts = sharedPreferences.getInt("playlist", 0);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (link.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter the YouTube Link", Toast.LENGTH_LONG).show();
                } else {

                    String Mylink = link.getText().toString();

                    Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                    intent.putExtra("VIDEO_LINK_VALUE", Mylink);
                    startActivity(intent);

                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (link.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter the YouTube Link", Toast.LENGTH_LONG).show();
                } else {


                    PlayList playClass = new PlayList(savedAttempts, link.getText().toString());

                    if (dbHelper.CreateNewPlayListItem((playClass))) {

                        Toast.makeText(getApplicationContext(), "Added to the PlayList", Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                        int currentAttempts = savedAttempts + 1;

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("playlist", currentAttempts);
                        editor.apply();

                    }else
                    {
                        Toast.makeText(getApplicationContext(),
                                "Can't add to the PlayList",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });



        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MyPlayListActivity.class));
            }
        });
    }
}