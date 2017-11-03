package com.kaizen.hoymm.ufoinphoto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
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
        return null;
    }

    private View.OnClickListener getCaptureUsingCameraButtonAction() {
        return null;
    }
}
