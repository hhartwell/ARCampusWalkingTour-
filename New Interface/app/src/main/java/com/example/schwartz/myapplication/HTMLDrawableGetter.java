package com.example.schwartz.myapplication;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Harvey on 3/28/2018.
 */

public class HTMLDrawableGetter extends AsyncTask<String, Integer, ArrayList<Drawable>> {
    private ProgressBar progressBar;

    public HTMLDrawableGetter(ProgressBar progressBar){
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute(){
        progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    protected ArrayList<Drawable> doInBackground(String... strings) {
        ArrayList<Drawable> drawables = new ArrayList<Drawable>();
        for (int i = 0; i < strings.length; i++){
            drawables.add(loadImageFromWebOperations(strings[i]));
            progressBar.incrementProgressBy(i/strings.length);
        }
        return drawables;
    }
    @Override
    protected void onProgressUpdate(Integer... values){
        progressBar.setProgress(values[0]);
    }
    public static Drawable loadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "https://i.imgur.com/HSYZZWK.jpg");
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
