package com.kaizen.hoymm.ufoinphoto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kaizen.hoymm.ufoinphoto.MainActivity.CAPTURE_IMAGE_REQUEST;

/**
 * Created by Damian Muca (Kaizen) on 04.12.17.
 */

class CapturePhoto {
    private static CapturePhoto capturePhoto = null;
    private AppCompatActivity activity;
    private String mCurrentPhotoPath;

    private CapturePhoto(AppCompatActivity activity){
        this.activity = activity;
    }

    static CapturePhoto getInstance(AppCompatActivity activity){
        capturePhoto = new CapturePhoto(activity);
        return capturePhoto;
    }


    Uri dispatchTakePictureIntentAndGetURI() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri curPhotoUri = null;
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                curPhotoUri = FileProvider.getUriForFile(activity, "com.kaizen.hoymm.ufoinphoto", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, curPhotoUri);
                activity.startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
            }
        }
        return curPhotoUri;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
