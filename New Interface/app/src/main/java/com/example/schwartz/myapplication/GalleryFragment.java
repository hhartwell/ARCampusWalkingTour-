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
        // retrieve button resources
//        ImageButton desmet = (ImageButton) rootView.findViewById(R.id.desmet);
//        desmet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // needs to be fixed to be a true back button
//                Intent intent = new Intent(view.getContext(), DesmetActivity.class);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }
/*
    private void formatAllImageButtonSizes(View rootView){
        rootView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        System.out.println("VIEW WIDTH: " + rootView.getMeasuredWidth());
        int newWidth = rootView.getMeasuredWidth()/2 - (30* (int) getResources().getDisplayMetrics().density);
        System.out.println("DISPLAY DENSITY: " + getResources().getDisplayMetrics().density);

        ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
        imageButtons.add((ImageButton) rootView.findViewById(R.id.alliance));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.catherinemonica));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.chardin));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.corkery));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.coughlin));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.crimont));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.cushing));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.desmet));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.dillon));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.dooley));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.dussault));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.goller));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.kennedy));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.lincoln));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.madonna));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.marian));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.roncalli));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.sharp));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.twohy));
        imageButtons.add((ImageButton) rootView.findViewById(R.id.welch));

        GridLayout.LayoutParams params = (GridLayout.LayoutParams) imageButtons.get(0).getLayoutParams();
        params.width = newWidth;

        imageButtons.get(0).setLayoutParams(params);
        params = (GridLayout.LayoutParams) imageButtons.get(1).getLayoutParams();
        params.width = newWidth;
        imageButtons.get(1).setLayoutParams(params);
    }
*/


    /**
     * Helper function that adds all images from the WalkGU server to all
     * Gallery image buttons in the fragment
     * @param rootView
     */

    private ArrayList<ImageButton> assignAllPicturesToGalleryFragment(View rootView) {
        //ArrayList<String> dormURLS = new ArrayList(Arrays.asList(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));



//        ArrayList<String> dormURLS = new ArrayList(Arrays.asList(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));

            ArrayList<ImageButton> imageButtons = new ArrayList<ImageButton>();
            ImageButton ib;
            for (int i = 0; i < getContext().getResources().getStringArray(R.array.dorm_gallery_urls).length; i++) {
                imageButtons.add(new ImageButton(getContext()));
            }

            assignDrawablesToImageButtons(imageButtons, getDormDrawables(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));

            return imageButtons;
/*
        ArrayList<String> dormURLS = new ArrayList(Arrays.asList(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));
        retrieveGalleryItem(dormURLS.get(0),rootView, R.id.alliance);
        retrieveGalleryItem(dormURLS.get(1), rootView, R.id.catherinemonica);
        //retrieveGalleryItem(dormURLS.get(2), rootView, R.id.chardin);
        //retrieveGalleryItem(dormURLS.get(3), rootView, R.id.corkery);
        //retrieveGalleryItem(dormURLS.get(4), rootView, R.id.coughlin);
        retrieveGalleryItem(dormURLS.get(5), rootView, R.id.crimont);
        //retrieveGalleryItem(dormURLS.get(6), rootView, R.id.cushing);
        retrieveGalleryItem(dormURLS.get(7), rootView, R.id.desmet);
        //retrieveGalleryItem(dormURLS.get(8), rootView, R.id.dillon);
        //retrieveGalleryItem(dormURLS.get(9), rootView, R.id.dooley);
        //retrieveGalleryItem(dormURLS.get(10), rootView, R.id.dussault);
        //retrieveGalleryItem(dormURLS.get(11), rootView, R.id.goller);
        //retrieveGalleryItem(dormURLS.get(12), rootView, R.id.kennedy);
        //retrieveGalleryItem(dormURLS.get(13), rootView, R.id.lincoln);
        retrieveGalleryItem(dormURLS.get(14), rootView, R.id.madonna);
        //retrieveGalleryItem(dormURLS.get(15), rootView, R.id.marian);
        //retrieveGalleryItem(dormURLS.get(16), rootView, R.id.roncalli);
        //retrieveGalleryItem(dormURLS.get(17), rootView, R.id.sharp);
        //retrieveGalleryItem(dormURLS.get(18), rootView, R.id.twohy);

        ArrayList<String> dormURLS = new ArrayList(Arrays.asList(getContext().getResources().getStringArray(R.array.dorm_gallery_urls)));
        retrieveGalleryItem(dormURLS.get(0),rootView, R.id.alliance);
        retrieveGalleryItem(dormURLS.get(1), rootView, R.id.catherinemonica);
//        retrieveGalleryItem(dormURLS.get(2), rootView, R.id.chardin);
//        retrieveGalleryItem(dormURLS.get(3), rootView, R.id.corkery);
//        retrieveGalleryItem(dormURLS.get(4), rootView, R.id.coughlin);
        retrieveGalleryItem(dormURLS.get(5), rootView, R.id.crimont);
//        retrieveGalleryItem(dormURLS.get(6), rootView, R.id.cushing);
        retrieveGalleryItem(dormURLS.get(7), rootView, R.id.desmet);
//        retrieveGalleryItem(dormURLS.get(8), rootView, R.id.dillon);
//        retrieveGalleryItem(dormURLS.get(9), rootView, R.id.dooley);
//        retrieveGalleryItem(dormURLS.get(10), rootView, R.id.dussault);
//        retrieveGalleryItem(dormURLS.get(11), rootView, R.id.goller);
//        retrieveGalleryItem(dormURLS.get(12), rootView, R.id.kennedy);
//        retrieveGalleryItem(dormURLS.get(13), rootView, R.id.lincoln);
        retrieveGalleryItem(dormURLS.get(14), rootView, R.id.madonna);
//        retrieveGalleryItem(dormURLS.get(15), rootView, R.id.marian);
//        retrieveGalleryItem(dormURLS.get(16), rootView, R.id.roncalli);
//        retrieveGalleryItem(dormURLS.get(17), rootView, R.id.sharp);
//        retrieveGalleryItem(dormURLS.get(18), rootView, R.id.twohy);

        retrieveGalleryItem(dormURLS.get(19), rootView, R.id.welch);
  */
    }

/*
    /**
     * Helper function that assigns an image to the resource value given the root view and a url to the image location.
     * This url is meant to be hosted on the WalkGU server
     * @param url
     * @param rootView
     * @param resourceValue
     */
/*
   private void retrieveGalleryItem(String url, View rootView, int resourceValue){
        Drawable desmet = null;
        try {
            desmet = new HTMLImageGetter().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = ((BitmapDrawable) desmet).getBitmap();
        ImageButton ib;
        ib = (ImageButton) rootView.findViewById(resourceValue);
        Drawable drawableResize = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 600, 600, true));
        ib.setImageDrawable(drawableResize);
        System.out.println(ib.getWidth());
    }

*/

    /**
     * helper function to assign gallery items to imagebuttons
     * Use the helper function getDormDrawables as the drawables parameter


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