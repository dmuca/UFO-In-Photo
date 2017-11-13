package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.MainActivity;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by hoymm on 03.11.17.
 */

class HeaderButtons {
    private Button backBtn, helpBtn, readyBtn;
    private Activity activity;

    HeaderButtons(Activity activity) {
        this.activity = activity;
        initButtons();
        setButtonsBehavior();
    }

    private void initButtons() {
        backBtn = activity.findViewById(R.id.btnBackId);
        helpBtn = activity.findViewById(R.id.btnHelpId);
        readyBtn = activity.findViewById(R.id.btnReadyId);
    }

    void showReadyButton(){
        readyBtn.setVisibility(View.VISIBLE);
    }

    void hideReadyButton(){
        readyBtn.setVisibility(View.INVISIBLE);
    }

    private void setButtonsBehavior() {
        setBackButtonsBehavior();
        setHelpButtonsBehavior();
        setReadyButtonsBehavior();
    }

    private void setBackButtonsBehavior() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    private void setHelpButtonsBehavior() {
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Help", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setReadyButtonsBehavior() {
        readyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Ready", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
