package harvey.com.walkgujava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * class responsible for displaying the main menu for the application.
 * it contains three buttons
 * start tour button
 * gallery button
 * keys button
 *
 * the start tour button will start a tour for the user to follow
 * the gallery button will direct the user to the full gallery activity
 * the keys buton will direct the user to the keysactivity
 */
public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // retrieve buton resources
        Button startTourButton = (Button) findViewById(R.id.start_tour_button);
        Button galleryButton = (Button) findViewById(R.id.gallery_button);
        Button keysButton = (Button) findViewById(R.id.keys_button);

        // assign basic on click listeners to each button
        startTourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });
        galleryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullGalleryActivity.class);
                startActivity(intent);
            }
        });
        keysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FullGalleryActivity.class);
                startActivity(intent);
            }
        });
    }
}
