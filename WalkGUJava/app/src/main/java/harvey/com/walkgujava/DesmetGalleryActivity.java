package harvey.com.walkgujava;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DesmetGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desmet_gallery);
        setTitle("Desmet Gallery");


        // retieve button resources
        ImageButton fatherDesmet = (ImageButton) findViewById(R.id.fatherDesmet);

        fatherDesmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs to be fixed to be a true back button
                Intent intent = new Intent(view.getContext(), FatherDesmetInformation.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

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

    private void backToGallery(){
        Intent intent = new Intent(this,FullGalleryActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void backToMap(){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    /**
     * Sets up the options menu.
     * @param menu The options menu.
     * @return Boolean.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.desmet_gallery_menu, menu);
        return true;
    }


    /**
     * assigns what each button of the menu does
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.option_get_gallery:
                backToGallery();
                return true;
            case R.id.option_get_map:
                backToMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
