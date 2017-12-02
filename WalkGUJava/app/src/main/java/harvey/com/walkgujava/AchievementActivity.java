package harvey.com.walkgujava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
public class AchievementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        setTitle("Keys");
    }


    private void backToMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }
    private void backToMap(){  
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    private void backToGallery(){
        Intent intent = new Intent(this,FullGalleryActivity.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * assigns what each button of the menu does
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.option_get_home:
                backToMain();
                return true;
            case R.id.option_get_map:
                backToMap();
                return true;
            case R.id.option_get_gallery:
                backToGallery();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Sets up the options menu.
     * @param menu The options menu.
     * @return Boolean.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keys_menu, menu);
        return true;
    }

}
