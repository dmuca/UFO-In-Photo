package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

public class EditImage extends AppCompatActivity {
    public static final String IMAGE_TO_EDIT_KEY = "com.kaizen.hoymm.ufoinphoto.EditImageActivity.IMAGE_TO_EDIT_KEY";

    private ImageView imageToEditImageView;
    private HeaderButtons headerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        headerButtons = new HeaderButtons(this);

        Bitmap imageBitmap = getIntent().getParcelableExtra(IMAGE_TO_EDIT_KEY);
        imageToEditImageView = (ImageView) findViewById(R.id.imageToEditId);
        imageToEditImageView.setImageBitmap(imageBitmap);
    }
}
