package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateExpenseActivity extends AppCompatActivity {

    EditText amount_input, time_input;
    Button update_expense_button;

    String id, type, amount, date_of_expense;

    private final String[] typeStatusArray = {
            "Travel",
            "Food",
            "Transport"

    };
    private Spinner spinnerType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);

        spinnerType = (Spinner) findViewById(R.id.spinnerType2);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeStatusArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);

        amount_input = findViewById(R.id.amount_input2);
        time_input   = findViewById(R.id.time_input2);
        update_expense_button = findViewById(R.id.update_expense_button);

        getAndSetIntentDataExpense();
        update_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerType = (Spinner) findViewById(R.id.spinnerType2);
                amount = amount_input.getText().toString().trim();
                date_of_expense = time_input.getText().toString().trim();
                type = spinnerType.getSelectedItem().toString();
                ExpenseDatabase exDb = new ExpenseDatabase(UpdateExpenseActivity.this);
                exDb.updateExpenseData(id, type, amount, date_of_expense);
                Intent intent = new Intent(UpdateExpenseActivity.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });

    }
    void getAndSetIntentDataExpense(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("amount")
                && getIntent().hasExtra("date_of_expense")){

            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            amount = getIntent().getStringExtra("amount");
            date_of_expense = getIntent().getStringExtra("date_of_expense");


            //Setting Intent Data
            spinnerType = (Spinner) findViewById(R.id.spinnerType2);
            amount_input.setText(amount);
            time_input.setText(date_of_expense);
        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}