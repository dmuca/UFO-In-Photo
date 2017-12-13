package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityImgReady;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.StoreImg;
import com.kaizen.hoymm.ufoinphoto.R;

public class ImgReadyActivity extends AppCompatActivity {
    private Button shareButton, saveInAlbumButton, startOverButton;
    private Bitmap editedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_ready);

        initButtons();
        setButtonsListeners();
        loadEditedImg();
        insertEditetImgAsPreview();
    }

    private void loadEditedImg() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        String photoPath = StoreImg.getTempPhotoPath(this);
        Log.d("SAVE_TEMP_IMG", "Path: " + photoPath);
        editedImg = BitmapFactory.decodeFile(photoPath, options);
    }

    private void insertEditetImgAsPreview() {
        ImageView selected_photo = (ImageView) findViewById(R.id.illustrativeImgId);
        selected_photo.setImageBitmap(editedImg);
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
            if (StoreImg.tryStoreImagePermanently(editedImg, this))
                Toast.makeText(this, R.string.img_has_been_saved, Toast.LENGTH_SHORT).show();
        });
    }

    private void setStartOverButtonListener() {
        startOverButton.setOnClickListener(v -> Toast.makeText(this, "Start Over", Toast.LENGTH_SHORT).show());
    }
}
