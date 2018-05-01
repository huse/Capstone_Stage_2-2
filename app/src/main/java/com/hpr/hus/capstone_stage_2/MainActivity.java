package com.hpr.hus.capstone_stage_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // populate  RecyclerView
        ArrayList<String> placeHolder = new ArrayList<>();
        placeHolder.add("Message 1");
        placeHolder.add("Message 2");
        placeHolder.add("Message 3");
        placeHolder.add("Message 4");
        placeHolder.add("Message 5");

        // setting up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, placeHolder);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        // setup layoutManager

        LinearLayoutManager  layoutManager = new LinearLayoutManager (this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };

        //Adding divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);



    }
    //Adding action lestenter to recyclereview.
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}
