package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;

public class AddExpenseActivity extends AppCompatActivity {

    EditText amount_input, time_input;
    Button add_button_expense;

    String trip_id;

    private final String[] typeStatusArray = {
            "Travel",
            "Food",
            "Transport"

    };
    private Spinner spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeStatusArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);

        amount_input = findViewById(R.id.amount_input);
        time_input   = findViewById(R.id.time_input);
        add_button_expense = findViewById(R.id.add_button_expense);
        trip_id = getIntent().getStringExtra("trip_id");
        add_button_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ExpenseDatabase exDb = new ExpenseDatabase(AddExpenseActivity.this);
                exDb.addExpense( spinnerType.getSelectedItem().toString(),
                        amount_input.getText().toString().trim(),
                        time_input.getText().toString().trim(),
                        trip_id);
                Intent intent = new Intent(AddExpenseActivity.this, ExpenseActivity.class);
                intent.putExtra("trip_id",trip_id);
                startActivity(intent);
            }
        });
        time_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDate localDate = LocalDate.now();
                int year = localDate.getYear();
                int month = localDate.getMonthValue();
                int day = localDate.getDayOfMonth();

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExpenseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                time_input.setText(day + "-" + (month++) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}