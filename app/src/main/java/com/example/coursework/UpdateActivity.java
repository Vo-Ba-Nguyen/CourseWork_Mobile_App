package com.example.coursework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.LocalDate;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, destination_input, date_input, description_input;
    Button update_button, delete_button, see_expense_button;
    private RadioGroup radioGroup;

    String id, name, destination, date, assessment, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        name_input = findViewById(R.id.name_input2);
        destination_input = findViewById(R.id.destination_input2);
        date_input = findViewById(R.id.date_input2);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup_button2);
        description_input = findViewById(R.id.description_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        see_expense_button = findViewById(R.id.see_expense_button);
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new UpdateDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        //We call this
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_input.getText().toString().trim();
                destination = destination_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                //and only then we call
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                int assessmentGroup = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(assessmentGroup);
                myDB.updateData(id,name,destination,date,assessment,description);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
        see_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, ExpenseActivity.class);
                startActivity(intent);

            }
        });

    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("destination")
        && getIntent().hasExtra("date") && getIntent().hasExtra("assessment") && getIntent().hasExtra("description")){

            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            assessment = getIntent().getStringExtra("assessment");
            description = getIntent().getStringExtra("description");

            //Setting Intent Data
            name_input.setText(name);
            destination_input.setText(destination);
            date_input.setText(date);
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup_button2);
            description_input.setText(description);
        }else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Are you sure want to delete " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    public void updateDateUpdate(LocalDate date) { date_input.setText(date.toString());
    }
}