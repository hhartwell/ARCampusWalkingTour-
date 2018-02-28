package com.example.schwartz.myapplication;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class DesmetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desmet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        // retieve button resources
        ImageButton fatherDesmet = (ImageButton) findViewById(R.id.desmet1);

        fatherDesmet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // needs to be fixed to be a true back button
                Intent intent = new Intent(view.getContext(), FatherDesmetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void gallery(){
        Intent intent = new Intent(this, GalleryFragment.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                gallery();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}