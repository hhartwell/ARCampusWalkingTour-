package com.example.schwartz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.Gonzaga.walkGonzagaColab.UnityPlayerActivity;

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
  }
  */
/*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FrameLayout fl = findViewById(R.id.frameLayout);
        fl.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        setContentView(R.layout.activity_unityplayer);
    }
*/
    @Override
    protected void startNewActivity() {
        super.startNewActivity();
        Intent i = new Intent(this, GalleryFragment.class);
        startActivity(i);
    }
}
