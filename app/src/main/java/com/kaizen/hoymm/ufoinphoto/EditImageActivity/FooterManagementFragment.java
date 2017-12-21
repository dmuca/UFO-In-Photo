package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ActivityChooseUFO.ChooseUFOActivity;
import com.kaizen.hoymm.ufoinphoto.R;

import static android.app.Activity.RESULT_OK;
import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.ANIMATIONS_DURATION;

/**
 * Created by hoymm on 20.11.17.
 */

public class FooterManagementFragment extends Fragment{
    private EditImageCommunication editImageCommunication;
    private SelectImage selectImageInterface;
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
        selectImageInterface = (SelectImage) getActivity();
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
        transformButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Transform button", Toast.LENGTH_SHORT).show();
        });
    }

    private void setBehaviorRemoveButton() {
        removeButton.setOnClickListener(v -> {
            editImageCommunication.removeCurUFO();
            showOrHideFooterPanelButtonsAnimation(new boolean []{true, false, false, false, true});
        });
    }

    private void setBehaviorElementsButton() {
        elementsButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Elements button", Toast.LENGTH_SHORT).show();
            editImageCommunication.notifyElemenetsListDataChanged();
            editImageCommunication.hideShowUFOElementsPanel();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case CHOOSE_UFO_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    int selectedUFOImg = data.getIntExtra(ChooseUFOActivity.SELECTED_UFO_DRAWABLE_KEY, R.drawable.img1);
                    editImageCommunication.addNewUFOObj(selectedUFOImg);
                    selectImageInterface.selectLastUFOObject();
                    editImageCommunication.showHideFooterButtonsAnimation();
                }
                else
                    Toast.makeText(getContext(), R.string.no_image_was_selected, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void showOrHideFooterPanelButtonsAnimation(boolean[] buttonsToShow) {
        showOrHideButton(addButton, buttonsToShow[0]);
        showOrHideButton(effectsButton, buttonsToShow[1]);
        showOrHideButton(transformButton, buttonsToShow[2]);
        showOrHideButton(removeButton, buttonsToShow[3]);
        showOrHideButton(elementsButton, buttonsToShow[4]);
    }

    private void showOrHideButton(ImageButton imageButton, boolean buttonsToShow) {
        if (ifButtonShouldBeShown(imageButton, buttonsToShow))
            showButton(imageButton);
        else if (ifButtonShouldBeHidden(imageButton, buttonsToShow))
            hideButton(imageButton);
    }

    private boolean ifButtonShouldBeShown(ImageButton imageButton, boolean buttonsToShow) {
        return (imageButton.getVisibility() == View.INVISIBLE || imageButton.getVisibility() == View.GONE)
                && buttonsToShow;
    }

    private boolean ifButtonShouldBeHidden(ImageButton imageButton, boolean buttonsToShow) {
        return imageButton.getVisibility() == View.VISIBLE && !buttonsToShow;
    }

    private void showButton(ImageButton imageButton) {
        imageButton.setEnabled(true);
        startShowAnimation(imageButton);
    }

    private void startShowAnimation(ImageButton imageButton) {
        final TranslateAnimation moveInAnimation = new TranslateAnimation(0, 0, imageButton.getHeight(), 0);
        moveInAnimation.setDuration(ANIMATIONS_DURATION);
        imageButton.setAnimation(moveInAnimation);
        moveInAnimation.start();
        imageButton.setVisibility(View.VISIBLE);
    }

    private void hideButton(ImageButton imageButton) {
        imageButton.setEnabled(false);
        startHideAnimation(imageButton);
    }

    private void startHideAnimation(final ImageButton imageButton) {
        final TranslateAnimation moveOutAnimation = new TranslateAnimation(0, 0, 0, imageButton.getHeight());
        moveOutAnimation.setDuration(ANIMATIONS_DURATION);
        imageButton.setAnimation(moveOutAnimation);
        moveOutAnimation.start();
        moveOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    boolean isListShown(){
        return elementsButton.isActivated();
    }
}
