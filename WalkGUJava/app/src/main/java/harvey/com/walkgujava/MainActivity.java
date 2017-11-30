package harvey.com.walkgujava;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * First activity to be launched by the Application
 * only contains one button that will take the user to the options screen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Button button = (Button) findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OptionsActivity.class);
                startActivity(intent);
            }
        }); */

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
                Intent intent = new Intent(view.getContext(), AchievementActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // get the menuinflater to inflate our menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * creates the button functionality in the menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // called when the user clicks on one of our actions
        // add, preferences, about
        // first, figure out which item was clicked
        int menuId = item.getItemId();
        switch(menuId) {
            case R.id.info:
                aboutMenuActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void aboutMenuActivity()
    {
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Help");
        int alert1 = R.string.aboutApp1;
        b.setMessage(R.string.aboutApp1);

        b.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int listener) {
                dialog.cancel();
            }
        });

        AlertDialog alert = b.create();
        alert.show();

    }
}
