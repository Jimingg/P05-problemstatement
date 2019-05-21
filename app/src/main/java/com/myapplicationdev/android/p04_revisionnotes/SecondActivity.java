package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnFilter;
    ArrayAdapter adapter;
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.btnFilter);
        DBHelper db = new DBHelper(SecondActivity.this);
        ArrayList<Song> data = db.getAllSongs();
        db.close();

        adapter = new SongAdapter(SecondActivity.this, R.layout.row, data);
        lv.setAdapter(adapter);

        /* btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsWithFilter(5));
                dbh.close();
            }
        }); */

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                String data = al.get(position);
                String idContent = data.split(",")[0].split(":")[1];
                String title = data.split(",")[1].trim();
                String singer = data.split(",")[2].trim();
                String year = data.split(",")[3].trim();
                String stars = data.split(",")[4].trim();

                Song target = new Song(Integer.parseInt(idContent), title, singer, Integer.parseInt(year), Integer.parseInt(stars));
                i.putExtra("data", target);
                startActivityForResult(i,9);

            }
        });
    }
}
