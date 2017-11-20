package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by hoymm on 20.11.17.
 */

public class FooterFragment extends Fragment {
    private ImageView imageToEdit;
    private ImageButton rotateLeftButton, rotateRightButton, acceptButton;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.footer_init_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageToEdit = getActivity().findViewById(R.id.imageToEditId);
        Log.e("ImageTOEdit", imageToEdit == null ? "null" : "smth:)");
        Log.e("ImageTOEdit", "Pivot X: " + imageToEdit.getPivotX() + ", Pivot Y: " + imageToEdit.getPivotY());
        initRotateImageFeature();
    }

    private void initRotateImageFeature() {
        setButtonRotationBehavior((ImageButton) getActivity().findViewById(R.id.rotate_left_button_id), -90);
        setButtonRotationBehavior((ImageButton) getActivity().findViewById(R.id.rotate_right_button_id), 90);
        setAcceptButtonBehavior();
    }

    @NonNull
    private void setButtonRotationBehavior(ImageButton button, final float angle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageToEdit.setRotation(imageToEdit.getRotation()+angle);
            }
        });
    }

    private void setAcceptButtonBehavior() {
        ImageView acceptButton = getActivity().findViewById(R.id.accept_button_id);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
