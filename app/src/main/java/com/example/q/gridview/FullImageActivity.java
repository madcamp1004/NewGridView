package com.example.q.gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class FullImageActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_main);

        //get intent data
        Intent i = getIntent();
        //Selected image id
        int position = i.getExtras().getInt("id");
        Log.i("FULL IMAGE BITMAP", String.valueOf(position));
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        imageView.setImageBitmap(ImageAdapter.imageList.get(position));
    }

}
