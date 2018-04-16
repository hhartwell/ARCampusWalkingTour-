package com.example.schwartz.myapplication;

import android.app.Activity;
import com.gonzaga.walkgonzaga.UnityPlayerActivity;
/**
 * Imports
 */

import android.content.Intent;
import android.os.Bundle;


import com.gonzaga.walkgonzaga.UnityPlayerActivity;

import java.lang.reflect.Field;

/**
 * Class that controls the AR Camera
 */
public class ARCameraActivity extends UnityPlayerActivity {
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UnityFragment unityFragment = new UnityFragment();
        fragmentTransaction.add(R.id.linearLayout, unityFragment);
        fragmentTransaction.commit();
        setContentView(R.layout.activity_arcamera);
    */
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_unityplayer);
//    }

    /**
     * Opens up the GalleryFragment.
     */
    @Override
    protected void startNewActivity() {

        super.startNewActivity();
        Intent i = new Intent(this, DesmetActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}