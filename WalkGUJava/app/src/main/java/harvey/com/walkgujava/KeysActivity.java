package harvey.com.walkgujava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * class to display each key that has or has not been unlocked yet
 *
 * the activity also has three buttons
 * Back button
 * gallery button
 * map button
 *
 * the back button will take the user back to the previous activity
 * the gallery button will take the user to the gallery
 * the map button will take the user to the map so that he or she
 *  may continue their tour
 *
 * additionally, each key must open their coresponding informational screen
 */
public class KeysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keys);

        // grab button resources
        Button backButton = (Button) findViewById(R.id.back_button);
        Button galleryButton = (Button) findViewById(R.id.gallery_button);
        Button mapButton = (Button) findViewById(R.id.map_button);

        // attatch on click listeners to each button

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this needs to retrieve the intent that started this activity then start
                // the activity that sent the intent.
                Intent intent = new Intent(view.getContext(), OptionsActivity.class);
                startActivity(intent);
            }
        });
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
