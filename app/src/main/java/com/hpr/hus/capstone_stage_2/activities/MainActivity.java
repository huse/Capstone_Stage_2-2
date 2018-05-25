package com.hpr.hus.capstone_stage_2.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.login.LoginActivity3;
import com.hpr.hus.capstone_stage_2.recycler.RecyclerViewAdapterList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterList.ItemClickListener {
    RecyclerViewAdapterList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);
        FirebaseAnalytics mFirebaseAnalytics;
       // StorageReference mStorageRef;

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

      //  mStorageRef = FirebaseStorage.getInstance().getReference();


        Bundle selectedBundle = new Bundle();
        Log.v("hhh", "MainActivity   onCreate ");
        final Intent intent = new Intent(this, LoginActivity3.class);
        intent.putExtras(selectedBundle);
        startActivity(intent);


    }

    //Adding action lestenter to recyclereview.
    @Override
    public void onItemClick(View view, int position) {
        // Toast.makeText(this, R.string.You_clicked + adapter.getItem(position) + R.string.on_row_number + position, Toast.LENGTH_SHORT).show();
    }


}
