package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.kaizen.hoymm.ufoinphoto.R;

public class EditImage extends AppCompatActivity {
    private HeaderButtons headerButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        headerButtons = new HeaderButtons(this);
    }
}
