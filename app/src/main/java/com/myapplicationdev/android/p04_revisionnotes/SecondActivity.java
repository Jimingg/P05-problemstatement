package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


	ListView lv;
	ArrayList<Note> al;
	RevisionNotesArrayAdapter aa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView
        lv = findViewById(R.id.lv);
        al = new ArrayList<Note>();
        DBHelper db = new DBHelper(SecondActivity.this);
        al = db.getAllNotes();


        aa = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, al);

        lv.setAdapter(aa);

        aa.notifyDataSetChanged();
	}


}
