package com.hpr.hus.capstone_stage_2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MessageDetailActivity extends AppCompatActivity {
    static String STEP_STACK_DETAIL="Step_Stack_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);

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
            if (findViewById(R.id.fragments_message_list_linear_layout).getTag()!=null && findViewById(R.id.fragments_message_list_linear_layout).getTag().equals("tablet")) {
                Log.v("jjj", "RecipeDetailActivity 3");

                final ContentMessageFragment fragment2 = new ContentMessageFragment();
                fragment2.setArguments(selectedRecipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.FRAGMENT_CONTAINER_TWO, fragment2).addToBackStack(STEP_STACK_DETAIL)
                        .commit();

            }
        }




    }



}
