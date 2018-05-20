package com.hpr.hus.capstone_stage_2.activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.login.LoginActivity;
import com.hpr.hus.capstone_stage_2.login.LoginActivity3;
import com.hpr.hus.capstone_stage_2.recycler.RecyclerViewAdapterList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterList.ItemClickListener{
    RecyclerViewAdapterList adapter;
    private FirebaseAnalytics mFirebaseAnalytics;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);



        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        Bundle selectedBundle = new Bundle();
        Log.v("hhh", "MainActivity   onCreate ");
        final Intent intent = new Intent(this, LoginActivity3.class);
        intent.putExtras(selectedBundle);
        startActivity(intent);




    }
    //Adding action lestenter to recyclereview.
    @Override
    public void onItemClick(View view, int position) {
       // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}
