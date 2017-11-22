package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
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

public class FooterRotateFragment extends Fragment {
    private ImageView imageToEdit;
    private ImageButton rotateLeftButton, rotateRightButton, acceptButton;
    private EditImageCommunication editImageCommunication;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.footer_rotate_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            editImageCommunication = (EditImageCommunication) context;
        } catch(ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnFragmentSendText");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initXMLObjects();
        setButtonsBehavior();
    }

    private void setButtonsBehavior() {
        setButtonRotationChange(rotateLeftButton, -90);
        setButtonRotationChange(rotateRightButton, 90);
        setAcceptButtonBehavior();
    }

    private void initXMLObjects() {
        imageToEdit = getActivity().findViewById(R.id.imageToEditId);
        acceptButton = getActivity().findViewById(R.id.accept_button_id);
        rotateLeftButton = getActivity().findViewById(R.id.rotate_left_button_id);
        rotateRightButton = getActivity().findViewById(R.id.rotate_right_button_id);
    }

    @NonNull
    private void setButtonRotationChange(ImageButton button, final float angle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageToEdit.setRotation(imageToEdit.getRotation()+angle);
            }
        });
    }

    private void setAcceptButtonBehavior() {
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editImageCommunication.showReadyButton();
                editImageCommunication.changeButtonsToShowInFooter
                        (new boolean [] {true, false, false, false, false});
            }
        });
    }
}
