package com.example.coursework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private ArrayList<TripModel> tripModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.idRVTrip);
        buildRecyclerView();

        //initialize and assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.search);

        //perform itemselectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.trip:startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<TripModel> filteredlist = new ArrayList<TripModel>();

        // running a for loop to compare elements.
        for (TripModel item : tripModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTripName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            searchAdapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        tripModelArrayList = new ArrayList<TripModel>();

        MyDatabase myDB = new MyDatabase(SearchActivity.this);
        tripModelArrayList = myDB.showAll();
        // below line is to add data to our array list.

        // initializing our adapter class.
        searchAdapter = new SearchAdapter(tripModelArrayList, SearchActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        recyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        recyclerView.setAdapter(searchAdapter);
    }
}