package com.example.schwartz.myapplication;

import android.content.Intent;

import com.gonzaga.walkgonzagacolab.UnityPlayerActivity;

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
