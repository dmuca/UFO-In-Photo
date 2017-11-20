package com.kaizen.hoymm.ufoinphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImage;

import java.io.FileNotFoundException;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImage.URI_OF_PICKED_IMAGE_KEY;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final int CAPTURE_IMAGE = 2;
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
        captureUsingCameraButton.setOnClickListener(getCaptureUsingCameraButtonAction());
    }

    private View.OnClickListener getGalleryButtonAction() {
        final AppCompatActivity activity = this;
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DynamicPermissions.getInstance().isExternalStoragePermissionGranted(activity))
                    pickAnImageFromGallery();
                else
                    DynamicPermissions.getInstance().askForReadExternalStoragePermission(activity);
            }
        };
    }

    private void pickAnImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private View.OnClickListener getCaptureUsingCameraButtonAction() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = getSelectedImageAsBitmap(requestCode, resultCode, data);
        ifImageContainsDataThenStartNewActivityAndPassImgUriToIt(selectedImageUri);
    }

    private Uri getSelectedImageAsBitmap(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = null;
        switch (requestCode){
            case PICK_IMAGE:
                selectedImageUri = pickImageFromGalleryResult(resultCode, data);
                break;
            case CAPTURE_IMAGE:
                //selectedImageUri = capturePhotoUsingCamera(resultCode, data);
                break;
            default:
                selectedImageUri = null;
        }
        return selectedImageUri;
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

    private Bitmap capturePhotoUsingCamera(int resultCode, Intent data) {
        return null;
    }

    private void ifImageContainsDataThenStartNewActivityAndPassImgUriToIt(Uri selectedImageUri) {
        if (selectedImageUri == null)
            Toast.makeText(this, "Image not picked.", Toast.LENGTH_SHORT).show();
        else{
            Intent editImageActivity = new Intent(this, EditImage.class);
            editImageActivity.putExtra(URI_OF_PICKED_IMAGE_KEY, selectedImageUri);
            startActivity(editImageActivity);
        }
    }
}
