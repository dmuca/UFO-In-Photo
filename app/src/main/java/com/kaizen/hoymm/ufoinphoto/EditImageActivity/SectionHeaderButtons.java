package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.DynamicPermissions;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityImgReady.ImgReadyActivity;
import com.kaizen.hoymm.ufoinphoto.MainActivity;
import com.kaizen.hoymm.ufoinphoto.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by hoymm on 03.11.17.
 */

public class SectionHeaderButtons {
    public static final String TEMP_IMG_NAME="MI_TEMP.jpg";
    private Button backBtn, helpBtn, readyBtn;
    private Activity activity;
    private final EditImageCommunication editImageCommunication;
    private String TAG = "SAVE_TEMP_IMG";

    SectionHeaderButtons(Activity activity) {
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
                    Bitmap bitmapNewImage = editImageCommunication.getEditedImage();
                    if (DynamicPermissions.isWriteExternalStoragePermissionGranted(activity))
                        storeImage(bitmapNewImage);
                    else
                        DynamicPermissions.askForWriteExternalStoragePermission(activity);
                    Intent intent = new Intent(activity, ImgReadyActivity.class);
                    activity.startActivity(intent);
                });
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile(activity);
        if (pictureFile == null) {
            Log.d(TAG, "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            Toast.makeText(activity, "Saved to gallery...", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Path: " + pictureFile.getPath());
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

    public static File getOutputMediaFile(Context context){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(getPathToFolder(context));

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + TEMP_IMG_NAME);
        return mediaFile;
    }

    @NonNull
    public static String getPathToFolder(Context context) {
        return Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/Files";
    }


}
