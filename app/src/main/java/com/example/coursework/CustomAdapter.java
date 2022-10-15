package com.example.coursework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyView> {
    private Context context;

    private ArrayList trip_id, trip_name, trip_destination, date_of_trip,trip_assessment, description;

    CustomAdapter(Context context, ArrayList trip_id, ArrayList trip_name, ArrayList trip_destination,
                  ArrayList date_of_trip,ArrayList trip_assessment, ArrayList description){

        this.context = context;
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.date_of_trip = date_of_trip;
        this.trip_assessment = trip_assessment;
        this.description = description;

    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyView holder, @SuppressLint("RecyclerView") final int position) {

        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_name_txt.setText(String.valueOf(trip_name.get(position)));
        holder.trip_destination_txt.setText(String.valueOf(trip_destination.get(position)));
        holder.date_of_trip_txt.setText(String.valueOf(date_of_trip.get(position)));
        holder.assessment_txt.setText(String.valueOf(trip_assessment.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(trip_id.get(position)));
                intent.putExtra("name", String.valueOf(trip_name.get(position)));
                intent.putExtra("destination", String.valueOf(trip_destination.get(position)));
                intent.putExtra("date", String.valueOf(date_of_trip.get(position)));
                intent.putExtra("assessment", String.valueOf(trip_assessment.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        TextView trip_id_txt, trip_name_txt, trip_destination_txt, date_of_trip_txt,assessment_txt,description_txt;
        LinearLayout mainLayout;
        public MyView(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            date_of_trip_txt = itemView.findViewById(R.id.date_of_trip_txt);
            assessment_txt = itemView.findViewById(R.id.assessment_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

}
