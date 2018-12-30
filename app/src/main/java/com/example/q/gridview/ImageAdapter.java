package com.example.q.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

//    keep all Images in array
    public static Integer[] mThumbIds={
//            R.drawable.image1,R.drawable.image3,
//            R.drawable.image12,R.drawable.image6,
//            R.drawable.image9,R.drawable.image10,
//            R.drawable.image11,R.drawable.image7,
//            R.drawable.image8
    };

    public static ArrayList<Bitmap> imageList = new ArrayList<>();

    //Constructor
    public ImageAdapter(Context c){ mContext = c; }
    @Override
    public int getCount(){
        return imageList.size();
    }
    @Override
    public Object getItem(int position){
        return imageList.get(position);
    }
    @Override
    public long getItemId(int position){
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

       ImageView image;

       if (convertView == null){
//           LayoutInflater mInflater = ( LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//           convertView = mInflater.inflate(R.layout.grid_item_view,null);

//           image = (ImageView) convertView.findViewById(R.id.grid_item_image);
//           image.setImageBitmap(imageList.get(position));
//           Log.i("SET IMAGE BITMAP AS", String.valueOf(position));

//           int h = mContext.getResources().getDisplayMetrics().densityDpi;
//
//           convertView.setLayoutParams(new GridView.LayoutParams(h,h));
//           convertView.setTag(image);

           // if it's not recycled, initialize some attributes
           image = new ImageView(mContext);
           int h = mContext.getResources().getDisplayMetrics().densityDpi;
           image.setLayoutParams(new ViewGroup.LayoutParams(h,h));

           image.setScaleType(ImageView.ScaleType.CENTER_CROP);

           // image.setPadding(8, 8, 8, 8);

           image.setImageBitmap(imageList.get(position));
           Log.i("SET IMAGE BITMAP AS", String.valueOf(position));


       }else{
           image = (ImageView) convertView;
       }
        // return convertView;
        return image;
    }
}
