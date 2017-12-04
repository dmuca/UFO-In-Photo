package com.kaizen.hoymm.ufoinphoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.URI_OF_PICKED_IMAGE_KEY;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static String mCurrentPhotoPath = "";
    private static final int CAPTURE_IMAGE_REQUEST = 2;
    private Button galleryButton, captureUsingCameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        setButtonsActions();
    }

    private void initButtons() {
        galleryButton = (Button) findViewById(R.id.galleryButtonId);
        captureUsingCameraButton = (Button) findViewById(R.id.captureUsingCameraButtonId);
    }

    private void setButtonsActions() {
        galleryButton.setOnClickListener(getGalleryButtonAction());
        captureUsingCameraButton.setOnClickListener(v -> {
            if (DynamicPermissions.getInstance().isWriteExternalStoragePermissionGranted(this)){
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST);
            }
            else
                DynamicPermissions.getInstance().askForWriteExternalStoragePermission(this);
        });
    }

    private View.OnClickListener getGalleryButtonAction() {
        return v -> {
                if (DynamicPermissions.getInstance().isReadExternalStoragePermissionGranted(this))
                    pickAnImageFromGallery();
                else
                    DynamicPermissions.getInstance().askForReadExternalStoragePermission(this);
            };
    }

    private void pickAnImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = getSelectedImageAsBitmap(requestCode, resultCode, data);
        ifImageContainsDataThenStartNewActivityAndPassImgUriToIt(selectedImageUri);
    }

    private Uri getSelectedImageAsBitmap(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case PICK_IMAGE_REQUEST:
                return pickImageFromGalleryResult(resultCode, data);
            case CAPTURE_IMAGE_REQUEST:
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "Title", null);
                return Uri.parse(path);
            default:
                return null;
        }
    }

    private Uri pickImageFromGalleryResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                return pickImageFromGalleryAndConvertToBitmap(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        return null;
    }

    private Uri pickImageFromGalleryAndConvertToBitmap(Intent data) throws FileNotFoundException {
        return data.getData();
    }

    private void ifImageContainsDataThenStartNewActivityAndPassImgUriToIt(Uri selectedImageUri) {
        if (selectedImageUri == null)
            Toast.makeText(this, "Image not picked.", Toast.LENGTH_SHORT).show();
        else{
            Intent editImageActivity = new Intent(this, EditImageActivity.class);
            editImageActivity.putExtra(URI_OF_PICKED_IMAGE_KEY, selectedImageUri);
            startActivity(editImageActivity);
        }
    }
}
