package com.example.schwartz.walkgonzaga20.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schwartz.walkgonzaga20.R;


public class ARFragment extends Fragment {

    public ARFragment() {
        // Required empty public constructor
    }

    public static ARFragment newInstance(String param1, String param2) {
        ARFragment fragment = new ARFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ar, container, false);
    }
}
