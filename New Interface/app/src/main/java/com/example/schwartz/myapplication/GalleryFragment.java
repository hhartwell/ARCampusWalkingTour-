package com.example.schwartz.myapplication;

import android.widget.ImageButton;

import java.util.ArrayList;
import android.content.Context;

/**
 * Imports
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * Fragment class that holds the images for the gallery screen.
 */
public class GalleryFragment extends Fragment {

    /**
     * Empty Constructor
     */
    public GalleryFragment() { }

    /**
     * Creates actions for this Activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates actions for this Activity
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        // add all images into the fragment
        ArrayList<ImageButton> imageButtons = assignAllPicturesToGalleryFragment(rootView);
        GridLayout gridLayout = (GridLayout) rootView.findViewById(R.id.grid_layout);
        for (int i = 0; i < imageButtons.size(); i++){
            gridLayout.addView(imageButtons.get(i));
        }
        return rootView;
    }

    /**
     * Helper function that adds all images from the WalkGU server to all
     * Gallery image buttons in the fragment
     * @param rootView
     */

    private ArrayList<ImageButton> assignAllPicturesToGalleryFragment(View rootView) {
            ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
            ImageButton ib;
            for (int i = 0; i < getContext().getResources().getStringArray(R.array.dorm_gallery_urls).length; i++) {
                imageButtons.add(new ImageButton(getContext()));
            }

            assignDrawablesToImageButtons(imageButtons, getDormDrawables(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));

            return imageButtons;
    }

    /**
     *
     * @param imageButtons
     * @param drawables
     */
    private void assignDrawablesToImageButtons(ArrayList<ImageButton> imageButtons, ArrayList<Drawable> drawables){
       ImageButton imageButton;
       Drawable drawable;
       for (int i = 0; i < drawables.size(); i++){
           imageButton = imageButtons.get(i);
           drawable = drawables.get(i);
           imageButton.setImageDrawable(drawable);
       }
    }

    /**
     * helper function to retrieve all the drawables for the dorms given an array of urls
     * returns a list of drawables
     * @param urls
     * @return
     */
    private ArrayList<Drawable> getDormDrawables(String ... urls){
        ArrayList<Drawable> drawables = new ArrayList<>();
        ProgressBar p = (ProgressBar) getActivity().findViewById(R.id.progressBar);
        try {
            drawables = new HTMLDrawableGetter(p).execute(urls).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return resizeDrawables(drawables);
    }

    /**
     * helper function that resizes all of the drawables.
     * resizing occures in the for loop.
     * this is necessary because the image that is retrieved from the server needs to be resized
     * @param drawables
     * @return
     */
    private ArrayList<Drawable> resizeDrawables(ArrayList<Drawable> drawables){
        ArrayList<Drawable> newDrawables = new ArrayList<Drawable>();
        Bitmap bitmap;
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int dimensions = size.x/2 - 65;
        for (int i = 0; i < drawables.size(); i++){
            bitmap = ((BitmapDrawable) drawables.get(i)).getBitmap();
            newDrawables.add(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, dimensions, dimensions, true)));
        }
        return newDrawables;
    }
}