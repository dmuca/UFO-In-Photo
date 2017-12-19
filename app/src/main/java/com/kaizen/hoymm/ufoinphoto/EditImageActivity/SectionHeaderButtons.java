package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.DynamicPermissions;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityImgReady.ImgReadyActivity;
import com.kaizen.hoymm.ufoinphoto.MainActivity;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by hoymm on 03.11.17.
 */

public class SectionHeaderButtons {
    private Button backBtn, helpBtn, readyBtn;
    private Activity activity;
    private final EditImageCommunication editImageCommunication;

    SectionHeaderButtons(Activity activity) {
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
                    editImageCommunication.removeDashedBorder();
                    Bitmap bitmapNewImage = editImageCommunication.getEditedImage();
                    if (DynamicPermissions.isWriteExternalStoragePermissionGranted(activity))
                        StoreImg.storeImageTemporarily(bitmapNewImage, activity);
                    else
                        DynamicPermissions.askForWriteExternalStoragePermission(activity);
                    Intent intent = new Intent(activity, ImgReadyActivity.class);
                    activity.startActivity(intent);
                });
    }
}
