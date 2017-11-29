package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ImgReadyActivity.ImgReadyActivity;
import com.kaizen.hoymm.ufoinphoto.MainActivity;
import com.kaizen.hoymm.ufoinphoto.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by hoymm on 03.11.17.
 */

class HeaderButtons {
    private Button backBtn, helpBtn, readyBtn;
    private Activity activity;
    private final EditImageCommunication editImageCommunication;

    HeaderButtons(Activity activity) {
        this.activity = activity;

        editImageCommunication = tryInitEditImageCommunication(activity);
        initButtons();
        setButtonsBehavior();
    }

    private EditImageCommunication tryInitEditImageCommunication(Activity activity) {
        try {
            return (EditImageCommunication) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement OnFragmentSendText");
        }
    }

    private void initButtons() {
        backBtn = activity.findViewById(R.id.btnBackId);
        helpBtn = activity.findViewById(R.id.btnHelpId);
        readyBtn = activity.findViewById(R.id.btnReadyId);
    }

    void showReadyButton(){
        readyBtn.setText(R.string.ready);
        readyBtn.setEnabled(true);
    }

    void hideReadyButton(){
        readyBtn.setText("");
        readyBtn.setEnabled(false);
    }

    private void setButtonsBehavior() {
        setBackButtonsBehavior();
        setHelpButtonsBehavior();
        setReadyButtonsBehavior();
    }

    private void setBackButtonsBehavior() {
        backBtn.setOnClickListener(v -> {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            });
    }

    private void setHelpButtonsBehavior() {
        helpBtn.setOnClickListener
                (v -> Toast.makeText(activity, "Help", Toast.LENGTH_SHORT).show());
    }

    private void setReadyButtonsBehavior() {
        readyBtn.setOnClickListener
                (v -> {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmapNewImage = editImageCommunication.getEditedImage();
                    bitmapNewImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent = new Intent(activity, ImgReadyActivity.class);
                    intent.putExtra("BitmapImage", byteArray);
                    activity.startActivity(intent);
                });
    }
}
