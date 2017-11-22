package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterManagementFragment.HOW_MANY_BUTTONS;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterComponentChanges {
    private static final int ANIMATIONS_DURATION = 300;
    private static boolean currentButtonsToShow[] = {false, false, false, false, false};
    private static boolean curFragmentIsFooterManagementFragment = false;

    public FooterComponentChanges() {
        currentButtonsToShow = new boolean[HOW_MANY_BUTTONS];
    }

    public static void showFooterLayoutWithSelectedButtons
            (Context context, boolean [] buttonsToGetIntoPanel){
        curFragmentIsFooterManagementFragment = isCurFragIsFooterManagementFragment((AppCompatActivity)context);

        ifFooterPanelComponentsIsDifferentSizeThenAmountOfPanelButtonsThenThrowException(buttonsToGetIntoPanel);
        refreshButtonsToShow(buttonsToGetIntoPanel);

        final TranslateAnimation animationGetInManagementPanel = getMoveInAnimation(context);

        final TranslateAnimation animationGetOutManagementPanel = getMoveOutAnimation(context, animationGetInManagementPanel);

        animationGetOutManagementPanel.start();
    }

    private static void ifFooterPanelComponentsIsDifferentSizeThenAmountOfPanelButtonsThenThrowException(boolean[] footerPanelComponents) {
        if (footerPanelComponents.length != 5)
            throw new ArrayHasDifferentSizeThenExpected("Expected array length is " + HOW_MANY_BUTTONS
                    + ", because footer management panel has " + HOW_MANY_BUTTONS + " buttons.");
    }

    @NonNull
    private static TranslateAnimation getMoveInAnimation(Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final TranslateAnimation moveInAnimation =
                new TranslateAnimation(0, 0, editImageCommunication.getFooterFrameLayout().getHeight(), 0);
        moveInAnimation.setDuration(ANIMATIONS_DURATION);

        return moveInAnimation;
    }

    private static void refreshButtonsToShow(boolean[] newButtonsToShow) {
        for (int i = 0; i < HOW_MANY_BUTTONS; ++i)
            currentButtonsToShow = newButtonsToShow;

    }

    @NonNull
    private static TranslateAnimation getMoveOutAnimation(Context context, final TranslateAnimation moveInAnimation) {
        final boolean [] buttonsToRemove = getButtonsToRemove(currentButtonsToShow);
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final FrameLayout frameLayout = editImageCommunication.getFooterFrameLayout();
        final TranslateAnimation moveOutAnimation = new TranslateAnimation(0, 0, 0, frameLayout.getHeight());
        moveOutAnimation.setDuration(ANIMATIONS_DURATION);

        moveOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                editImageCommunication.showManagementFooter();

                performAnimationOnWholeFragmentOrJustSomeButtons(editImageCommunication.getManagementFooterFragment(), moveInAnimation);
                Log.i("FragName 2", editImageCommunication.getManagementFooterFragment().getClass().getName());
                moveInAnimation.start();

            }
        });

        performAnimationOnWholeFragmentOrJustSomeButtons(editImageCommunication.getRotateFooterFragment(), moveOutAnimation);
        Log.i("FragName 1", editImageCommunication.getManagementFooterFragment().getClass().getName());
        return moveOutAnimation;
    }

    private static boolean[] getButtonsToRemove(boolean[] buttonsToGetIntoPanel) {
        boolean buttonsToRemove [] = new boolean[buttonsToGetIntoPanel.length];
        for (int i = 0; i < buttonsToGetIntoPanel.length; ++i)
            buttonsToRemove[i] = currentButtonsToShow[i] && !buttonsToGetIntoPanel[i];
        return buttonsToRemove;
    }

    private static void performAnimationOnWholeFragmentOrJustSomeButtons(Fragment fragment, TranslateAnimation getInAnimation) {
        if (curFragmentIsFooterManagementFragment) {
            moveOutOnlySomeButtons(fragment, getInAnimation);
            Log.i("WholeFragAnim", "false");
        } else {
            moveOutWholeFragment(fragment, getInAnimation);
            Log.i("WholeFragAnim", "true");
        }
    }

    private static void moveOutOnlySomeButtons(Fragment fragment, TranslateAnimation animation) {
        fragment.getView().setAnimation(animation);
    }

    private static void moveInOnlySomeButtons(Fragment fragment, TranslateAnimation animation) {
        fragment.getView().setAnimation(animation);
    }

    private static void moveOutWholeFragment(Fragment fragment, TranslateAnimation animation) {
        fragment.getView().setAnimation(animation);
    }

    private static boolean isCurFragIsFooterManagementFragment(AppCompatActivity appCompatActivity) {
        Fragment myFragment = appCompatActivity.getSupportFragmentManager().findFragmentById(R.id.footerFrameId);
        return myFragment instanceof FooterManagementFragment;
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
