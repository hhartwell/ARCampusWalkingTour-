package com.example.schwartz.myapplication;

import android.content.Intent;
import com.Gonzaga.walkGonzagaColab.UnityPlayerActivity;

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
