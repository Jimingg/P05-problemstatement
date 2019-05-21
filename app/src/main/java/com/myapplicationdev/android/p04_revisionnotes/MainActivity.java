package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etNote;
    Button btninsert;
    TextView btnshowlist;
    RadioGroup rgstar;
    ListView lv;
    ArrayList<Note> al;
    SongAdapter aa;
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNote = findViewById(R.id.editTextNote);
        btninsert = findViewById(R.id.buttonInsertNote);
        btnshowlist = findViewById(R.id.buttonShowList);
        rgstar = findViewById(R.id.radioGroupStars);

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String note = etNote.getText().toString();
                if(note.equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Enter Something",
                            Toast.LENGTH_LONG).show();
                }else {
                    Boolean repeated = true;
                    ArrayList<String> data = db.getNoteContent();
                    db.close();
                    for( int i=0; i<data.size();i++){
                        if(data.get(i).equalsIgnoreCase(note)){
                            repeated = true;
                        }else {
                            repeated = false;
                        }
                    }

                    if (repeated == true){
                        Toast.makeText(MainActivity.this, "Repeated note ,try again!",
                                Toast.LENGTH_LONG).show();
                    }else{
                        int starid = rgstar.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) findViewById(starid);
                        String star = radioButton.getText().toString();
                        int starselected = Integer.valueOf(star);
                        // Insert a task
                        db.insertNote(note, starselected);
                        db.close();
                        Toast.makeText(MainActivity.this, "Successfully inserted",
                                Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        btnshowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
