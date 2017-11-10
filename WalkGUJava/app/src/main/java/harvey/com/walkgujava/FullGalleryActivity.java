    package harvey.com.walkgujava;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

    public class FullGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_gallery);

        // retreive spinner resource and assign adapter to it with the dorms string array
        Spinner dormSpinner = (Spinner) findViewById(R.id.dormSpinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.dorms,
                        android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dormSpinner.setAdapter(adapter);

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
}
