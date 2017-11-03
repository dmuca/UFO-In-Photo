package com.kaizen.hoymm.ufoinphoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

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
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
            }
        };
    }

    private View.OnClickListener getCaptureUsingCameraButtonAction() {
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap selectedImage = getSelectedImageAsBitmap(requestCode, resultCode, data);
        if (selectedImage == null)
            Toast.makeText(this, "Image not picked.", Toast.LENGTH_SHORT).show();
        else{
            // TODO send image to new INTENT... :)
        }

    }

    private Bitmap getSelectedImageAsBitmap(int requestCode, int resultCode, Intent data) {
        Bitmap selectedImage;
        if (requestCode == PICK_IMAGE)
            selectedImage = pickImageFromGalleryResult(resultCode, data);
        else if (requestCode == CAPTURE_IMAGE)
            selectedImage = capturePhotoUsingCamera(resultCode, data);
        else
            selectedImage = null;
        return selectedImage;
    }

    private Bitmap pickImageFromGalleryResult(int resultCode, Intent data) {
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

    private Bitmap pickImageFromGalleryAndConvertToBitmap(Intent data) throws FileNotFoundException {
        final Uri imageUri = data.getData();
        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
        return BitmapFactory.decodeStream(imageStream);
    }

    private Bitmap capturePhotoUsingCamera(int resultCode, Intent data) {
        return null;
    }
}
