package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ImgReadyActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImgReadyActivity extends AppCompatActivity {
    String TAG = "SAVE EDITED IMG";
    private Bitmap bitmapImg;
    private ImageView illustrativeImage;
    private Button shareButton, saveInAlbumButton, startOverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_ready);

        retrieveBitmapImage();
        illustrativeImage.setImageBitmap(bitmapImg);

        initButtons();
        setButtonsListeners();
    }

    private void retrieveBitmapImage() {
        illustrativeImage = (ImageView) findViewById(R.id.illustrativeImgId);
        byte[] byteArray = getIntent().getByteArrayExtra("BitmapImage");
        bitmapImg = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    private void initButtons() {
        shareButton = (Button) findViewById(R.id.shareButtonId);
        saveInAlbumButton = (Button) findViewById(R.id.saveInAlbumButtonId);
        startOverButton = (Button) findViewById(R.id.startOverButtonId);
    }

    private void setButtonsListeners() {
        setShareButtonListener();
        setSaveInAlbumButtonListener();
        setStartOverButtonListener();
    }

    private void setShareButtonListener() {
        shareButton.setOnClickListener(v -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show());
    }

    private void setSaveInAlbumButtonListener() {
        saveInAlbumButton.setOnClickListener(v -> {
            storeImage(bitmapImg);
        });
    }

    private void setStartOverButtonListener() {
        startOverButton.setOnClickListener(v -> Toast.makeText(this, "Start Over", Toast.LENGTH_SHORT).show());
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d(TAG, "Error creating media file, check storage permissions: ");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            Toast.makeText(this, R.string.saved_in_album, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
            Toast.makeText(this, R.string.file_not_found, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
            Toast.makeText(this, R.string.error_access_file, Toast.LENGTH_SHORT).show();
        }
    }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
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
        String mImageName="UFO_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }
}
