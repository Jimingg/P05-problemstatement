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
    ArrayList<Song> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.btnFilter);
        DBHelper db = new DBHelper(SecondActivity.this);
        al = db.getAllSongs();
        db.close();

        adapter = new SongAdapter(SecondActivity.this, R.layout.row, al);
        lv.setAdapter(adapter);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsWithFilter(5));
                adapter = new SongAdapter(SecondActivity.this, R.layout.row,al);
                dbh.close();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                int dataID = al.get(position).getId();
                String dataTitle = al.get(position).getTitle();
                String dataSinger = al.get(position).getSingers();
                int dataYear = al.get(position).getYear();
                int dataStar = al.get(position).getStars();

                Song target = new Song(dataID, dataTitle, dataSinger, dataYear, dataStar);
                i.putExtra("data", target);
                startActivityForResult(i,9);

            }
        });
    }
}
