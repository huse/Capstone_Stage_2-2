package com.hpr.hus.capstone_stage_2.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.fragments.ContentMessageFragment;
import com.hpr.hus.capstone_stage_2.fragments.ListMessageFragment;

public class MessageDetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "hhh Message";
    private static String STEP_STACK_DETAIL = "Step_Stack_detail";
    private FirebaseUser mFirebaseRef;
    NavigationView navigationView;
    private String userEmail = "Welcome    ";

    private DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mFirebaseRef = FirebaseAuth.getInstance().getCurrentUser();
        if(mFirebaseRef!=null)
        userEmail = userEmail + mFirebaseRef.getEmail();


        if (mFirebaseRef != null) {
            for (UserInfo profile : mFirebaseRef.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url

                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();


                Log.v("hhh Message", "\nuser name: " + name + "\nuser email: " + email + "\nuser photoUrl: " + photoUrl);
            }
        }
        String userId = getIntent().getStringExtra("user_id");
        userDatabase = FirebaseDatabase.getInstance().getReference();

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /* String displayName=dataSnapshot.child("name").getValue().toString();*/
                /* String status = dataSnapshot.child("Status").getValue().toString();*/
                /* String image = dataSnapshot.child("image").getValue().toString();*/


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if (savedInstanceState == null) {

            Bundle selectedRecipeBundle = getIntent().getExtras();

           /* recipeArrList = new ArrayList<>();
            recipeArrList = selectedRecipeBundle.getParcelableArrayList(SELECTED_RECIPES);
            nameOfRecipe = recipeArrList.get(0).getName();*/

            final ListMessageFragment fragment = new ListMessageFragment();
            fragment.setArguments(selectedRecipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.FRAGMENT_CONTAINER_ONE, fragment)
                    .commit();
            if (findViewById(R.id.fragments_message_list_linear_layout).getTag() != null && findViewById(R.id.fragments_message_list_linear_layout).getTag().equals("tablet")) {
                Log.v("jjj", "RecipeDetailActivity 3");

                final ContentMessageFragment fragment2 = new ContentMessageFragment();
                fragment2.setArguments(selectedRecipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.FRAGMENT_CONTAINER_TWO, fragment2).addToBackStack(STEP_STACK_DETAIL)
                        .commit();

            }
        }


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.welcome).setTitle(userEmail);

        return true;
    }
}
