package com.example.schwartz.myapplication;

/**
 * Imports
 */
import android.content.Intent;
import com.Gonzaga.walkGonzagaColab.UnityPlayerActivity;

/**
 * Class that controls the AR Camera
 */
public class ARCameraActivity extends UnityPlayerActivity {

    /**
     * Opens up the GalleryFragment.
     */
    @Override
    protected void startNewActivity() {
        super.startNewActivity();
        Intent i = new Intent(this, GalleryFragment.class);
        startActivity(i);
    }
}
