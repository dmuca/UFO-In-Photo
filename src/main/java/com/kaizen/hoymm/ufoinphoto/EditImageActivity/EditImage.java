package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;
public class EditImage extends AppCompatActivity {
    public static final String URI_OF_PICKED_IMAGE_KEY = "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_PICKED_IMAGE_KEY";

    private ImageView imageToEditImageView;
    private HeaderButtons headerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        initHeaderButtons();
        loadImage();
    }

    private void initHeaderButtons() {
        headerButtons = new HeaderButtons(this);
        headerButtons.hideReadyButton();
    }

    private void loadImage() {
        Uri imageUri = getIntent().getParcelableExtra(URI_OF_PICKED_IMAGE_KEY);
        imageToEditImageView = (ImageView) findViewById(R.id.imageToEditId);
        imageToEditImageView.setImageURI(imageUri);
    }
}
