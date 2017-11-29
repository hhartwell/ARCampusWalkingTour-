package harvey.com.walkgujava;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ARCameraActivity extends AppCompatActivity {

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
}
