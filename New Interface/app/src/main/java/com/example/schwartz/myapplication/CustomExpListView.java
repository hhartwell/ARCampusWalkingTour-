package com.example.schwartz.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by Danielle on 3/16/2018.
 */

public class CustomExpListView extends ExpandableListView{
    public CustomExpListView(Context context)
    {
        super(context);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(960, View.MeasureSpec.AT_MOST);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(600, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}