package com.example.sit708_my_application_01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyPlayListActivity extends AppCompatActivity {

    ListView ListViewPlay;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play_list);


        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        ListViewPlay=(ListView) findViewById(R.id.list_PlayList);

        ArrayList<String> theList=new ArrayList<>();
        Cursor cursor = dbHelper.SearchAllPlayLists();
        if(cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(), "PlayList is Empty!", Toast.LENGTH_LONG).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                theList.add(cursor.getString(0));
                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                ListViewPlay.setAdapter(listAdapter);
            }
        }


        ListViewPlay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Integer ID = position;
                ArrayList<PlayList> playLists= dbHelper.SearchPlayItem(ID);
                if(playLists.size()!=0){
                    Intent intentViewList = new Intent(MyPlayListActivity.this, PlayActivity.class);
                    PlayList play = playLists.get(0);

                    intentViewList.putExtra("VIDEO_LINK_VALUE",play.getName());
                    startActivity(intentViewList);
                }
            }
        });

    }
}