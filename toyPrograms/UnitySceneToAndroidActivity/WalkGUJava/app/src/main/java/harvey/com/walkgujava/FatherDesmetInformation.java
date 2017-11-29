package harvey.com.walkgujava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class FatherDesmetInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father_desmet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Father Desmet Information");
    }

    private void backToMain(){
        Intent intent = new Intent(this, DesmetGalleryActivity.class);
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
            case android.R.id.home:
                backToMain();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
