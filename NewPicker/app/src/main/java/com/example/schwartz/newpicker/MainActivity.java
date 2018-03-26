package com.example.schwartz.newpicker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView number;
    Button numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (TextView) findViewById(R.id.number);
        numberPicker = (Button) findViewById (R.id.numberPicker);
        numberPicker.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        numberPickerDialog();
    }

    private void numberPickerDialog()
    {
        NumberPicker myNumberPicker = new NumberPicker(this);
        final String[] values= {"Alliance House","Burch Apartments", "Campion House", "Catherine Monica Hall",
                "Chardin House", "Corkery Apartments", "Coughlin Hall", "Crimont Hall", "Cushing House",
                "Desmet Hall", "Dillon Hall", "Dussault Suites", "Goller Hall", "Kennedy Apartments",
                "Lincoln House", "Madonna Hall", "Marian Hall", "River Inn Hall", "Roncalli House",
                "Sharp Apartments", "Twohy Hall", "Welch Hall"};
        myNumberPicker.setMaxValue(values.length-1);
        myNumberPicker.setMinValue(0);
        myNumberPicker.setDisplayedValues(values);
        myNumberPicker.setWrapSelectorWheel(true);
        NumberPicker.OnValueChangeListener myValChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                number.setText(values[newVal]);
            }
        };
        myNumberPicker.setOnValueChangedListener(myValChangedListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(myNumberPicker);
        builder.setTitle("Residence Halls");

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
