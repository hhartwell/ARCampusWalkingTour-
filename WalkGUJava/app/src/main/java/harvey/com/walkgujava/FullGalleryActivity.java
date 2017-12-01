    package harvey.com.walkgujava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

    public class FullGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_gallery);
        setTitle("Gallery");



        // retrieve spinner resource and assign adapter to it with the dorms string array
        Spinner dormSpinner = (Spinner) findViewById(R.id.dormSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FullGalleryActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dorms));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dormSpinner.setAdapter(adapter);
        dormSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {

                } else if (i == 2) {

                } else if (i == 3) {

                } else if (i == 4) {

                } else if (i == 5) {

                } else if (i == 6) {

                } else if (i == 7) {

                } else if (i == 8) {

                } else if (i == 9) {

                } else if (i == 10) {
                    startActivity(new Intent(FullGalleryActivity.this, DesmetGalleryActivity.class));
                } else if (i == 11) {

                } else if (i == 12) {

                } else if (i == 13) {

                } else if (i == 14) {

                } else if (i == 15) {

                } else if (i == 16) {

                } else if (i == 17) {

                } else if (i == 18) {

                } else if (i == 19) {

                } else if (i == 20) {

                } else if (i == 21) {

                } else if (i == 22) {

                } else if (i == 23) {

                } else {}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    private void backToKeys(){
        Intent intent = new Intent(this, AchievementActivity.class);
        startActivity(intent);
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
                finish();
                return true;
            case R.id.option_get_map:
                backToMap();
                return true;
            case R.id.option_get_keys:
                backToKeys();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        private void backToCamera(){
            //Intent intent = new Intent(this,FullGalleryActivity.class);
            //startActivity(intent);
            //this.finish();
        }

        /**
         * Sets up the options menu.
         * @param menu The options menu.
         * @return Boolean.
         */
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.full_gallery_menu, menu);
            return true;
        }

}
