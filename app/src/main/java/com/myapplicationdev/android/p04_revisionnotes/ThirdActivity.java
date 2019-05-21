package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
   EditText etID,etTitle,etSingers,etYear;
    RadioGroup rgstar;
    Button btnUpdate, btnDelete,btnCancel;
    Song data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        etID = findViewById(R.id.editTextID);
        etTitle = findViewById(R.id.editTextTitle);
        etSingers = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        rgstar = findViewById(R.id.RadioGroupStars);
        btnUpdate = findViewById(R.id.buttonupdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        data = (Song)i.getSerializableExtra("data");
        etID.setText("id"+data.getId());
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(data.getYear());
        if (data.getStars() == 5){
            RadioButton  rb5 = findViewById(R.id.radioButton5);
            rb5.setChecked(true);
        }else if (data.getStars() == 4){
            RadioButton  rb4 = findViewById(R.id.radioButton4);
            rb4.setChecked(true);
        }else if (data.getStars() == 3){
            RadioButton  rb3 = findViewById(R.id.radioButton3);
            rb3.setChecked(true);
        }else if (data.getStars() == 2){
            RadioButton  rb2 = findViewById(R.id.radioButton2);
            rb2.setChecked(true);
        }else if (data.getStars() == 1){
            RadioButton  rb1 = findViewById(R.id.radioButton1);
            rb1.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(	etYear.getText().toString()));
                int radioid = rgstar.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(radioid) ;
                int radiochecked = Integer.parseInt(radioButton.getText().toString());
                data.setStars(radiochecked);

                dbh.updateNote(data);
                dbh.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteNote(data.getId());
                dbh.close();
            }
        });
    }
}
