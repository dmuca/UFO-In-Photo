package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityImgReady;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.SectionHeaderButtons;
import com.kaizen.hoymm.ufoinphoto.R;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.SectionHeaderButtons.TEMP_IMG_NAME;

public class ImgReadyActivity extends AppCompatActivity {
    private Button shareButton, saveInAlbumButton, startOverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_ready);

        initButtons();
        setButtonsListeners();
        setTempImageToPreview();
    }

    private void setTempImageToPreview() {
        ImageView selected_photo = (ImageView) findViewById(R.id.illustrativeImgId);
        SectionHeaderButtons.getOutputMediaFile(this);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        String photoPath = SectionHeaderButtons.getPathToFolder(this) + "/" + TEMP_IMG_NAME;
        Log.d("SAVE_TEMP_IMG", "Path: " + photoPath);
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
        selected_photo.setImageBitmap(bitmap);
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

        });
    }

    private void setStartOverButtonListener() {
        startOverButton.setOnClickListener(v -> Toast.makeText(this, "Start Over", Toast.LENGTH_SHORT).show());
    }
}
