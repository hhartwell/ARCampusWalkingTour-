package com.example.schwartz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ToursFragment extends Fragment {


    public ToursFragment() {
        // Required empty public constructor
    }

    public static ToursFragment newInstance(String param1, String param2) {
        ToursFragment fragment = new ToursFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tours, container, false);


        Button desmet = (Button) rootView.findViewById(R.id.tour1);
        desmet.setOnClickListener(new View.OnClickListener() {
            Fragment fragment;
            @Override
            public void onClick(View view) {
                fragment = new MapFragment();
                loadFragment(fragment);

            }
        });
        return rootView;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

