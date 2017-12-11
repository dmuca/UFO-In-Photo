package com.kaizen.hoymm.ufoinphoto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;
import static com.kaizen.hoymm.ufoinphoto.MainActivity.PICK_IMG_FROM_GALLERY_REQUEST;

/**
 * Created by Damian Muca (Kaizen) on 04.12.17.
 */

class PickImgFromGallery {
    private static PickImgFromGallery pickImgFromGallery;
    private AppCompatActivity activity = null;

    private PickImgFromGallery(AppCompatActivity activity){
        this.activity = activity;
    }

    static PickImgFromGallery getInstance(AppCompatActivity activity){
        pickImgFromGallery = new PickImgFromGallery(activity);
        return pickImgFromGallery;
    }

    View.OnClickListener getGalleryButtonAction() {
        return v -> {
            if (DynamicPermissions.isReadExternalStoragePermissionGranted(activity))
                pickAnImageFromGallery();
            else
                DynamicPermissions.askForReadExternalStoragePermission(activity);
        };
    }

    private void pickAnImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMG_FROM_GALLERY_REQUEST);
    }

    Uri pickImageFromGalleryURIResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                return pickImageFromGalleryAndConvertToBitmap(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else
            Toast.makeText(activity, "You haven't picked Image",Toast.LENGTH_LONG).show();
        return null;
    }

    private Uri pickImageFromGalleryAndConvertToBitmap(Intent data) throws FileNotFoundException {
        return data.getData();
    }
}
