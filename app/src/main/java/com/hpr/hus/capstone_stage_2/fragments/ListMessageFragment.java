package com.hpr.hus.capstone_stage_2.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hpr.hus.capstone_stage_2.R;
import com.hpr.hus.capstone_stage_2.recycler.RecyclerViewAdapterList;

import java.util.ArrayList;

public class ListMessageFragment extends Fragment implements RecyclerViewAdapterList.ItemClickListener {
    RecyclerViewAdapterList adapter;

    public ListMessageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_list, container, false);


        LinearLayout linearLayout = rootView.findViewById(R.id.linear_message_list);
        // RecyclerViewAdapter adapter;

        // populate  RecyclerView
        ArrayList<String> placeHolder = new ArrayList<>();
        placeHolder.add("Person 1");
        placeHolder.add("Person 2");
        placeHolder.add("Person 3");
        placeHolder.add("Person 4");
        placeHolder.add("Person 5");
        placeHolder.add("Person 6");
        placeHolder.add("Person 7");
        placeHolder.add("Person 8");
        placeHolder.add("Person 9");


        // setting up RecyclerView
        // Log.v("hhh",getView().toString());
        RecyclerView recyclerView = rootView.findViewById(R.id.rvMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapterList(this, getActivity(), placeHolder);
        //adapter.setClickListener(new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);

        // setup layoutManager

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        };
        recyclerView.setLayoutManager(layoutManager);


        //Adding divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        return rootView;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + 1 + " on row number " + position, Toast.LENGTH_SHORT).show();

    }
}
