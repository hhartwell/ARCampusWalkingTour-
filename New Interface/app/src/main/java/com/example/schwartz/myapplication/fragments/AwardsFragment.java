package com.example.schwartz.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schwartz.myapplication.R;

public class AwardsFragment extends Fragment {

    public AwardsFragment() {
        // Required empty public constructor
    }

    public static AwardsFragment newInstance(String param1, String param2) {
        AwardsFragment fragment = new AwardsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_awards, container, false);
    }
}
