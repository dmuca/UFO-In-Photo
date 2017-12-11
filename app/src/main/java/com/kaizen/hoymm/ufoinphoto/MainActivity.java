package com.kaizen.hoymm.ufoinphoto;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity;

public class MainActivity extends AppCompatActivity {
    public static final String URI_OF_ORIGINAL_IMAGE_KEY =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_ORIGINAL_IMAGE_KEY";

    static final int PICK_IMG_FROM_GALLERY_REQUEST = 1;
    static final int CAPTURE_IMAGE_REQUEST = 2;
    private static Uri curPhotoUri = null;
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
        galleryButton.setOnClickListener(PickImgFromGallery.getInstance(this).getGalleryButtonAction());

        captureUsingCameraButton.setOnClickListener(v -> {
            if (DynamicPermissions.isWriteExternalStoragePermissionGranted(this))
                curPhotoUri = CapturePhoto.getInstance(this).dispatchTakePictureIntentAndGetURI();
            else
                DynamicPermissions.askForWriteExternalStoragePermission(this);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMG_FROM_GALLERY_REQUEST)
            curPhotoUri = PickImgFromGallery.getInstance(this).pickImageFromGalleryURIResult(resultCode, data);

        if (curPhotoUri != null)
            startNewActivityAndPassImgUriToIt(curPhotoUri);
        else
            Toast.makeText(this, R.string.img_not_selected, Toast.LENGTH_SHORT).show();
    }

    private void startNewActivityAndPassImgUriToIt(Uri selectedImageUri) {
        Intent editImageActivity = new Intent(this, EditImageActivity.class);
        editImageActivity.putExtra(URI_OF_ORIGINAL_IMAGE_KEY, selectedImageUri);
        startActivity(editImageActivity);
    }
}
