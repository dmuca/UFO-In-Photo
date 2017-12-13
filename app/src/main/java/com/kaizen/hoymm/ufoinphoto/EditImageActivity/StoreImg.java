package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityImgReady.ImgReadyActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Damian Muca (Kaizen) on 13.12.17.
 */

public class StoreImg {
    private static String TAG = "SAVE_TEMP_IMG";
    private static final String TEMP_IMG_NAME="MI_TEMP.jpg";

    private StoreImg(){}

    public static String getTempPhotoPath(ImgReadyActivity imgReadyActivity) {
        return getMediaFile(imgReadyActivity, TEMP_IMG_NAME).getPath();
    }

    public static boolean storeImageTemporarily(Bitmap bitmapNewImage, Activity activity) {
        return tryStoreImage(bitmapNewImage, getMediaFile(activity, TEMP_IMG_NAME));
    }

    public static boolean tryStoreImagePermanently(Bitmap bitmapNewImage, Activity activity){
        return tryStoreImage(bitmapNewImage, getMediaFile(activity, createNewFileName()));
    }

    private static boolean tryStoreImage(Bitmap bitmapNewImage, File mediaFile) {
        try {
            storeImage(bitmapNewImage, mediaFile);
            return true;
        } catch (StoragePermissionException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String createNewFileName(){
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        return "UFO_"+ timeStamp +".jpg";
    }

    private  static File getMediaFile(Context context, String fileName){
        File mediaStorageDir = new File(getPathToFolder(context));

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return mediaFile;
    }

    private static void storeImage(Bitmap image, File file) throws StoragePermissionException, IOException {
        if (file == null) {
            throw new StoragePermissionException("Error creating media file, check storage permissions: ");
        }
        FileOutputStream fos = new FileOutputStream(file);
        image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.close();
        Log.d(TAG, "Path: " + file.getPath());
    }

    @NonNull
    public static String getPathToFolder(Context context) {
        return Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/Files";
    }

    public static class StoragePermissionException extends Exception
    {
        // Parameterless Constructor
        public StoragePermissionException() {}

        // Constructor that accepts a message
        public StoragePermissionException(String message)
        {
            super(message);
        }
    }
}
