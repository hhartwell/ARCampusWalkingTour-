package harvey.com.walkgujava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

    /**
     * function that will open an alert dialog with an image when given an image view.
     * meant to be used with GalleryItem
     * @param imageView
     * @param width
     * @param height
     */
    private void viewPhoto(ImageView imageView, int width, int height){
        ImageView tmp = imageView;
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflator.inflate(R.layout.custom_fullimage_dialog,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
        image.setImageDrawable(tmp.getDrawable());
        imageDialog.setView(layout);
        imageDialog.setPositiveButton("done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        imageDialog.create();
        imageDialog.show();
    }
}
