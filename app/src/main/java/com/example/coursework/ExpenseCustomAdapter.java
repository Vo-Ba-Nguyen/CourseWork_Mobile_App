package com.example.coursework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseCustomAdapter extends RecyclerView.Adapter<ExpenseCustomAdapter.MyViewExpense> {

    private Context context;
    private ArrayList expense_id, type_expense, amount_expense, date_of_expense;


    ExpenseCustomAdapter(Context context, ArrayList expense_id, ArrayList type_expense, ArrayList amount_expense,
                         ArrayList date_of_expense){
        this.context = context;
        this.expense_id = expense_id;
        this.type_expense = type_expense;
        this.amount_expense = amount_expense;
        this.date_of_expense = date_of_expense;
    }

    @NonNull
    @Override
    public MyViewExpense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense_row, parent, false);
        return new MyViewExpense(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewExpense holder, @SuppressLint("RecyclerView") int position) {

        holder.expense_id_txt.setText(String.valueOf(expense_id.get(position)));
        holder.type_expense_txt.setText(String.valueOf(type_expense.get(position)));
        holder.amount_expense_txt.setText(String.valueOf(amount_expense.get(position)));
        holder.date_expense_txt.setText(String.valueOf(date_of_expense.get(position)));
        holder.expenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateExpenseActivity.class);
                intent.putExtra("id", String.valueOf(expense_id.get(position)));
                intent.putExtra("type", String.valueOf(type_expense.get(position)));
                intent.putExtra("amount", String.valueOf(amount_expense.get(position)));
                intent.putExtra("date_of_expense", String.valueOf(date_of_expense.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewExpense extends RecyclerView.ViewHolder {

        TextView expense_id_txt, type_expense_txt, date_expense_txt, amount_expense_txt;
        LinearLayout expenseLayout;

        public MyViewExpense(@NonNull View itemView) {
            super(itemView);
            expense_id_txt = itemView.findViewById(R.id.expense_id_txt);
            type_expense_txt = itemView.findViewById(R.id.type_expense_txt);
            amount_expense_txt = itemView.findViewById(R.id.amount_expense_txt);
            date_expense_txt = itemView.findViewById(R.id.date_expense_txt);
            expenseLayout = itemView.findViewById(R.id.expenseLayout);

        }
    }
}
