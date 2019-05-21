package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
            
        }
    }
}
