package com.example.q.gridview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.support.v4.content.FileProvider;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraUtils {
    /**
     * Refreshes gallery on adding new image. Gallery won't be refreshed
     * on older devices until device is rebooted
     */
    public static void refreshGallery(Context context, String filepath){
        //ScanFile so ti will be appeared on Gallery
        MediaScannerConnection.scanFile(context, new String[]{filepath}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {

                    }
                });
    }
    public static boolean checkPermissions(Context context){
        return ActivityCompat.checkSelfPermission(context,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    /*Downsizing the bitmap to avoid OutOfMemory exceptions
     */
    public static Bitmap optimizeBitmap(int sampleSize, String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(filePath,options);
    }
    /**
     * Checks whether device has camera or not. This method not necessary if
     * android:required="true" is used in manifest file
     */
    public static boolean isDeviceSupportCamera(Context context){
        if(context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)){
            //this device has a camera
            return true;
        }else{
            //no camera on this device
            return false;
        }

    }
    public static void openSettings(Context context){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",BuildConfig.APPLICATION_ID,null));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static Uri getOutputMediaFileUri(Context context, File file){
        return FileProvider.getUriForFile(context,context.getPackageName()+".provider",file);
    }
    /**
     * creates and returns the image file before opening the camera
     */
    public static File getOutputMediaFile(){
        //external sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                MainActivity.GALLERY_DIRECTORY_NAME);
        //create the storage directory if it does not exist
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                Log.e(MainActivity.GALLERY_DIRECTORY_NAME,"Oops! Failed create"
                        +MainActivity.GALLERY_DIRECTORY_NAME+" directory");
                return null;
            }
        }
        //preparing media file naming convention
        //adds timestamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_"+timeStamp+"."+MainActivity.IMAGE_EXTENSION);
        return mediaFile;
    }
}