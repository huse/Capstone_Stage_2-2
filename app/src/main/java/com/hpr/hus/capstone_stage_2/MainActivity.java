package com.hpr.hus.capstone_stage_2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hpr.hus.capstone_stage_2.login.LoginActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterList.ItemClickListener{
    RecyclerViewAdapterList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);


/*
        Bundle selectedRecipeBundle = new Bundle();
        Log.v("hhh", "MainActivity   onCreate ");
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtras(selectedRecipeBundle);
         startActivity(intent)*/;

        Bundle selectedRecipeBundle = new Bundle();
        ArrayList<Integer> selectedRecipe = new ArrayList<>();
        //  selectedRecipe.add(clickedItemIndex);
        //  selectedRecipeBundle.putParcelableArrayList("Select_Recipe",selectedRecipe);
        Log.v("jjj", "runnignIntentActivity");
        final Intent intent = new Intent(this, MessageDetailActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);






        /*MasterListFragment masterListFragment = new MasterListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.list_container,masterListFragment)
                .commit();*/

       /* // populate  RecyclerView
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
        recyclerView.addItemDecoration(dividerItemDecoration);*/



    }
    //Adding action lestenter to recyclereview.
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}
