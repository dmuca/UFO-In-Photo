package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.DynamicPermissions;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ImgReadyActivity.ImgReadyActivity;
import com.kaizen.hoymm.ufoinphoto.MainActivity;
import com.kaizen.hoymm.ufoinphoto.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by hoymm on 03.11.17.
 */

class HeaderButtons {
    private Button backBtn, helpBtn, readyBtn;
    private Activity activity;
    private final EditImageCommunication editImageCommunication;
    String TAG = "SAVE EDITED IMG";

    HeaderButtons(Activity activity) {
        this.activity = activity;

        editImageCommunication = tryInitEditImageCommunication(activity);
        initButtons();
        setButtonsBehavior();
    }

    private EditImageCommunication tryInitEditImageCommunication(Activity activity) {
        try {
            return (EditImageCommunication) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnFragmentSendText");
        }
    }

    private void initButtons() {
        backBtn = activity.findViewById(R.id.btnBackId);
        helpBtn = activity.findViewById(R.id.btnHelpId);
        readyBtn = activity.findViewById(R.id.btnReadyId);
    }

    void showReadyButton(){
        readyBtn.setText(R.string.ready);
        readyBtn.setEnabled(true);
    }

    void hideReadyButton(){
        readyBtn.setText("");
        readyBtn.setEnabled(false);
    }

    private void setButtonsBehavior() {
        setBackButtonsBehavior();
        setHelpButtonsBehavior();
        setReadyButtonsBehavior();
    }

    private void setBackButtonsBehavior() {
        backBtn.setOnClickListener(v -> {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            });
    }

    private void setHelpButtonsBehavior() {
        helpBtn.setOnClickListener
                (v -> Toast.makeText(activity, "Help", Toast.LENGTH_SHORT).show());
    }

    private void setReadyButtonsBehavior() {
        readyBtn.setOnClickListener
                (v -> {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmapNewImage = editImageCommunication.getEditedImage();



                    if (DynamicPermissions.getInstance().isWriteExternalStoragePermissionGranted(activity)){
                        storeImage(bitmapNewImage);
                    }
                    else
                        DynamicPermissions.getInstance().askForWriteExternalStoragePermission(activity);
                    //bitmapNewImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    //byte[] byteArray = stream.toByteArray();

                    Intent intent = new Intent(activity, ImgReadyActivity.class);
                    //intent.putExtra("BitmapImage", byteArray);
                    activity.startActivity(intent);
                });
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            Toast.makeText(activity, "Saved to gallery...", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + activity.getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

}
