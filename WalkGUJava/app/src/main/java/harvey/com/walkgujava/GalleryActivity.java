package harvey.com.walkgujava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // retieve button resources
        Button backButton = (Button) findViewById(R.id.back_button);
        Button mapButton = (Button) findViewById(R.id.map_button);
        Button keysButton = (Button) findViewById(R.id.keys_button);

        // set on click listeners to the buttons
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs to be fixed to be a true back button
                Intent intent = new Intent(view.getContext(), KeysActivity.class);
                startActivity(intent);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs to be fixed to be a true back button
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        keysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs to be fixed to be a true back button
                Intent intent = new Intent(view.getContext(), KeysActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }

    /**
     * nested class for the images in the gallery
     * these items will need to display the image fullscreen when the user selects one
     */
    public class GalleryItem extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener{
        public GalleryItem(Context context, Drawable drawable){
            super(context);
            this.setImageDrawable(drawable);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
