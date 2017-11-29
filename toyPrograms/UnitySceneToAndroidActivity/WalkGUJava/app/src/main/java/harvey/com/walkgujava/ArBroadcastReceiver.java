package harvey.com.walkgujava;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Harvey on 11/27/2017.
 */

public class ArBroadcastReceiver extends BroadcastReceiver{
    private static final String TAG = "AR BROADCAST RECIEVER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "IN BROADCAST RECIEVER");
        int flag = intent.getIntExtra("ActivityToLaunch", 0);
        if (flag == 1){
            Intent i = new Intent(context, DesmetGalleryActivity.class);
            context.startActivity(i);
        }
    }
}
