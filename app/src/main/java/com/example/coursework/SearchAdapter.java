package com.example.coursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<TripModel> tripModelArrayList;

    // creating a constructor for our variables.
    public SearchAdapter(ArrayList<TripModel> tripModelArrayList, Context context) {
        this.tripModelArrayList = tripModelArrayList;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<TripModel> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        tripModelArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creating a variable for array list and context.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        TripModel model = tripModelArrayList.get(position);
        holder.trip_id_txt.setText(String.valueOf(model.getId()));
        holder.trip_name_txt.setText(model.getTripName());
        holder.trip_destination_txt.setText(model.getTripDestination());
        holder.date_of_trip_txt.setText(model.getTripDate());
        holder.assessment_txt.setText(model.getTripAssessment());
        holder.description_txt.setText(model.getTripDescription());

    }

    @Override
    public int getItemCount() {
        return tripModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView trip_id_txt, trip_name_txt, trip_destination_txt, date_of_trip_txt,assessment_txt,description_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_destination_txt = itemView.findViewById(R.id.trip_destination_txt);
            date_of_trip_txt = itemView.findViewById(R.id.date_of_trip_txt);
            assessment_txt = itemView.findViewById(R.id.assessment_txt);
            description_txt = itemView.findViewById(R.id.description_txt);

        }
    }
}
