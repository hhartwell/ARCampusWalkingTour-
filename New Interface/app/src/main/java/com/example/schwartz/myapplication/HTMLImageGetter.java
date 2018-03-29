package com.example.schwartz.myapplication;

import android.graphics.drawable.Drawable;
        import android.os.AsyncTask;

import java.io.IOException;
        import java.io.InputStream;
        import java.net.MalformedURLException;
        import java.net.URL;

/**
 * Created by Harvey on 2/19/2018.
 */

public class HTMLImageGetter extends AsyncTask<String, Drawable, Drawable>{
    String url = "https://i.imgur.com/HSYZZWK.jpg";

    @Override
    protected Drawable doInBackground(String... urls) {
        return LoadImageFromWebOperations(urls[0]);
    }

    @Override
    protected void onProgressUpdate(Drawable... values) {
        super.onProgressUpdate(values);
    }

    public static Drawable LoadImageFromWebOperations(String url) {
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