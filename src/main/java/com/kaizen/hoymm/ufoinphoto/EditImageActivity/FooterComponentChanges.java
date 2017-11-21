package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterManagementFragment.HOW_MANY_BUTTONS;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterComponentChanges {
    private static final int ANIMATIONS_DURATION = 300;
    private static boolean buttonsToShow [];

    public FooterComponentChanges() {
        buttonsToShow = new boolean[HOW_MANY_BUTTONS];
    }

    public static void showFooterLayoutWithSelectedButtons
            (Context context, boolean [] buttonsToGetIntoPanel){

        ifFooterPanelComponentsIsDifferentSizeThenAmountOfPanelButtonsThenThrowException(buttonsToGetIntoPanel);
        final TranslateAnimation animationGetInManagementPanel = getMoveInAnimation(context, buttonsToGetIntoPanel);
        final TranslateAnimation animationGetOutManagementPanel = getMoveOutAnimation(context, animationGetInManagementPanel);
        animationGetOutManagementPanel.start();
        
    }

    private static void ifFooterPanelComponentsIsDifferentSizeThenAmountOfPanelButtonsThenThrowException(boolean[] footerPanelComponents) {
        if (footerPanelComponents.length != 5)
            throw new ArrayHasDifferentSizeThenExpected("Expected array length is " + HOW_MANY_BUTTONS
                    + ", because footer management panel has " + HOW_MANY_BUTTONS + " buttons.");
    }

    @NonNull
    private static TranslateAnimation getMoveInAnimation(Context context, boolean [] newFooterButtonsToShow) {
        refreshButtonsToShow(newFooterButtonsToShow);

        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final TranslateAnimation getInAnimation =
                new TranslateAnimation(0, 0, editImageCommunication.getFooterFrameLayout().getHeight(), 0);
        getInAnimation.setDuration(ANIMATIONS_DURATION);

        getInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editImageCommunication.addFooterManagementPanelFragmentIfNotAlreadyAdded();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        return getInAnimation;
    }

    private static void refreshButtonsToShow(boolean[] newButtonsToShow) {
        for (int i = 0; i < HOW_MANY_BUTTONS; ++i)
            buttonsToShow = newButtonsToShow;

    }

    @NonNull
    private static TranslateAnimation getMoveOutAnimation(Context context, final TranslateAnimation getInAnimation) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final FrameLayout frameLayout = editImageCommunication.getFooterFrameLayout();

        final TranslateAnimation getOutAnimation = new TranslateAnimation(0, 0, 0, frameLayout.getHeight());
        getOutAnimation.setDuration(ANIMATIONS_DURATION);

        getOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                frameLayout.setAnimation(getInAnimation);
                getInAnimation.start();
            }
        });

        frameLayout.setAnimation(getOutAnimation);
        return getOutAnimation;
    }

    public static EditImageCommunication tryToInitEditImageCommunication(Context context) {
        if (context == null)
            throw new NullPointerException("tryToInitEditImageCommunication() context cannot be null.");
        try {
           return (EditImageCommunication) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnFragmentSendText");
        }
    }
}
