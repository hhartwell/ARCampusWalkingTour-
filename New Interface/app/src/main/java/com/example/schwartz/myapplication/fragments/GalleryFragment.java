package com.example.schwartz.myapplication.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.schwartz.myapplication.HTMLImageGetter;
import com.example.schwartz.myapplication.R;

import java.util.concurrent.ExecutionException;

public class GalleryFragment extends Fragment {

    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        retrieveGalleryItem("https://imgur.com/HSYZZWK.jpg", rootView);
        return rootView;
    }
    private void retrieveGalleryItem(String url, View rootView){
        Drawable desmet = null;
        try {
            desmet = new HTMLImageGetter().execute(url).get();
            System.out.println(desmet.getIntrinsicHeight());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = ((BitmapDrawable) desmet).getBitmap();
        ImageButton ib;
        ib = (ImageButton) rootView.findViewById(R.id.desmet);
        System.out.println("HEIGHT " + ib.getMeasuredWidth() + ", " + ib.getHeight());
        Drawable desmetResize = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 600, 600, true));
        ib.setImageDrawable(desmetResize);
        System.out.println(ib.getWidth());
    }

}