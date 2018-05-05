package com.hpr.hus.capstone_stage_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapterList extends RecyclerView.Adapter<RecyclerViewAdapterList.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data passed to constructor
    RecyclerViewAdapterList(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    public RecyclerViewAdapterList(ItemClickListener listener, Context context, List<String> data) {
        Log.v("uuu11", "RecipeAdapter object made");
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mClickListener = listener;
    }
        // inflates row layout from xml whenever needs
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    // bindING   data to TextView in row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
    }

    //number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // recycles views , stores as they will be scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvMessageName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // method for getting data at clicked position
    String getItem(int id) {
        return mData.get(id);
    }

    // allowing to click event to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity would implement thhis methods for responding to click event
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
