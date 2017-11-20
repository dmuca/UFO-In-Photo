package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        imageToEdit = view.findViewById(R.id.imageToEditId);
        initRotateImageFeature(view);
    }

    private void initRotateImageFeature(View view) {
        setButtonRotationBehavior((ImageButton) view.findViewById(R.id.rotate_left_button_id), -90);
        setButtonRotationBehavior((ImageButton) view.findViewById(R.id.rotate_right_button_id), 90);
        setAcceptButtonBehavior(view);
    }

    @NonNull
    private void setButtonRotationBehavior(ImageButton button, final float rotation) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageToEdit.setRotation(rotation);
            }
        });
    }

    private void setAcceptButtonBehavior(View view) {
        ImageView acceptButton = view.findViewById(R.id.accept_button_id);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
