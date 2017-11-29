package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ChooseUFOActivity.ChooseUFOActivity;
import com.kaizen.hoymm.ufoinphoto.R;

import java.util.ArrayList;
import java.util.Arrays;

import static android.app.Activity.RESULT_OK;

/**
 * Created by hoymm on 20.11.17.
 */

public class FooterAddPhotoFragment extends Fragment{
    private EditImageCommunication editImageCommunication;
    private ImageButton addButton, effectsButton, transformButton, removeButton, elementsButton;
    private static final int CHOOSE_UFO_REQ_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.footer_management_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editImageCommunication = (EditImageCommunication) getActivity();
        initButtonsIfViewExists();
        setButtonsBehavior();
    }

    private void initButtonsIfViewExists() {
        View view = getView();
        if (view != null)
            initButtons();
    }

    private void initButtons() {
        addButton = getView().findViewById(R.id.add_obj_id);
        effectsButton = getView().findViewById(R.id.effects_id);
        transformButton = getView().findViewById(R.id.transform_id);
        removeButton = getView().findViewById(R.id.remove_id);
        elementsButton = getView().findViewById(R.id.elements_id);
    }

    private void setButtonsBehavior() {
        setBehaviorAddButton();
        setBehaviorEffectsButton();
        setBehaviorTransformButton();
        setBehaviorRemoveButton();
        setBehaviorElementsButton();
    }

    private void setBehaviorAddButton() {
        addButton.setOnClickListener(v -> {
            Intent chooseUFOIntent = new Intent(getContext(), ChooseUFOActivity.class);
            startActivityForResult(chooseUFOIntent, CHOOSE_UFO_REQ_CODE);
        });
    }

    private void setBehaviorEffectsButton() {
        effectsButton.setOnClickListener(v -> Toast.makeText(getContext(), "Effects button", Toast.LENGTH_SHORT).show());
    }

    private void setBehaviorTransformButton() {
        transformButton.setOnClickListener(v -> Toast.makeText(getContext(), "Transform button", Toast.LENGTH_SHORT).show());
    }

    private void setBehaviorRemoveButton() {
        removeButton.setOnClickListener(v -> Toast.makeText(getContext(), "Remove button", Toast.LENGTH_SHORT).show());
    }

    private void setBehaviorElementsButton() {
        elementsButton.setOnClickListener(v -> Toast.makeText(getContext(), "Elements button", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case CHOOSE_UFO_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    int selectedUFOImg = data.getIntExtra(ChooseUFOActivity.SELECTED_UFO_DRAWABLE_KEY, R.drawable.img1);
                    editImageCommunication.addNewUFOOBj(selectedUFOImg);
                    editImageCommunication.showHideFooterButtonsAnimation();
                }
                else
                    Toast.makeText(getContext(), R.string.no_image_was_selected, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void showFooterButtons(boolean[] buttonsToShow) {
        Log.i("ButtonsToShow", "buttonsToShow[0] == " + buttonsToShow[0]);
        Log.i("ButtonsToShow", "buttonsToShow[1] == " + buttonsToShow[1]);
        Log.i("ButtonsToShow", "buttonsToShow[2] == " + buttonsToShow[2]);
        Log.i("ButtonsToShow", "buttonsToShow[3] == " + buttonsToShow[3]);
        Log.i("ButtonsToShow", "buttonsToShow[4] == " + buttonsToShow[4]);
        addButton.setVisibility(buttonsToShow[0] ? View.VISIBLE : View.INVISIBLE);
        effectsButton.setVisibility(buttonsToShow[1] ? View.VISIBLE : View.INVISIBLE);
        transformButton.setVisibility(buttonsToShow[2] ? View.VISIBLE : View.INVISIBLE);
        removeButton.setVisibility(buttonsToShow[3] ? View.VISIBLE : View.INVISIBLE);
        elementsButton.setVisibility(buttonsToShow[4] ? View.VISIBLE : View.INVISIBLE);
    }
}
