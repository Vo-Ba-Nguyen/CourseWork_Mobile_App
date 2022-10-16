package com.example.coursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_expense_button;


    ExpenseDatabase exDb;
    ArrayList<String> expense_id, type_expense, amount_expense, date_of_expense;
    ExpenseCustomAdapter expenseCustomAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        recyclerView = findViewById(R.id.recyclerView_expense);
        add_expense_button = findViewById(R.id.add_expense_button);
        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseActivity.this, AddExpenseActivity.class);
                startActivity(intent    );
            }
        });

        exDb = new ExpenseDatabase(ExpenseActivity.this);
        expense_id = new ArrayList<>();
        type_expense = new ArrayList<>();
        amount_expense = new ArrayList<>();
        date_of_expense = new ArrayList<>();

        displayExpense();

        expenseCustomAdapter = new ExpenseCustomAdapter(ExpenseActivity.this, expense_id, type_expense, amount_expense, date_of_expense );
        recyclerView.setAdapter(expenseCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpenseActivity.this));
    }
    void displayExpense(){
        Cursor cursor = exDb.readAllDataExpense();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                expense_id.add(cursor.getString(0));
                type_expense.add(cursor.getString(1));
                amount_expense.add(cursor.getString(2));
                date_of_expense.add(cursor.getString(3));
            }
        }
    }
}