package com.hpr.hus.capstone_stage_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContentMessageFragment  extends Fragment {
    public ContentMessageFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {


        View view = layoutInflater.inflate(R.layout.fragment_message_content, viewGroup, false);

        return view;

    }
}
