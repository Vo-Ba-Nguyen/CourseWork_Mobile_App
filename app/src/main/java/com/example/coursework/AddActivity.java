package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.time.LocalDate;

public class AddActivity extends AppCompatActivity {

    EditText name_input, destination_input, date_input, description_input;
    Button button_add_database;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_button);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) findViewById(i);
            }
        });

        name_input = findViewById(R.id.name_input);
        destination_input = findViewById(R.id.destination_input);
        date_input = findViewById(R.id.date_input);
        description_input = findViewById(R.id.description_input);
        button_add_database = findViewById(R.id.button_add_database);
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        button_add_database.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                MyDatabase myDB = new MyDatabase(AddActivity.this);
                myDB.addTrip(name_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString(),
                        description_input.getText().toString().trim());

                displayAlert();
            }
        });
    }
    void displayAlert(){
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage(
                "Name: " + name_input.getText().toString() + "\n" +
                "Destination: " + destination_input.getText().toString() + "\n" +
                "Date: " + date_input.getText().toString() + "\n" +
                "Risk Assessment: " + ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString() + "\n" +
                "Description: " + description_input.getText().toString()
                ).setNeutralButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
            }
        }).show();
    }

    public void updateDate(LocalDate date) { date_input.setText(date.toString());
    }
}