package info.schwartz.walkgonzaga;

import android.content.Intent;
import android.os.Bundle;

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
        Intent i = new Intent(this, DesmetGalleryActivity.class);
        startActivity(i);
    }
}
