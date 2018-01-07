package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.ANIMATIONS_DURATION;

/**
 * Created by Damian Muca (Kaizen) on 21.11.17.
 */

public class FooterAnimation {
    private static boolean topPanelAnimating;

    static void swapRotatePanelToManagement(Context context){
        final TranslateAnimation animationGetInManagementPanel = getInBottomFooterAnimation(context);
        getOutBottomFooterAnimation(context, animationGetInManagementPanel);
    }

    @NonNull
    private static TranslateAnimation getInBottomFooterAnimation(Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        float fromDeltaY =  editImageCommunication.getFooterBottomFrameLayout().getHeight(), toDeltaY = 0;

        return getFooterSwapAnimation(fromDeltaY, toDeltaY);
    }

    @NonNull
    private static TranslateAnimation getFooterSwapAnimation(float fromDeltaY, float toDeltaY) {
        final TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, fromDeltaY, toDeltaY);
        translateAnimation.setDuration(ANIMATIONS_DURATION);
        return translateAnimation;
    }

    @NonNull
    private static TranslateAnimation getOutBottomFooterAnimation(Context context, final TranslateAnimation moveInAnimation) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final FrameLayout bottomFooterFrame = editImageCommunication.getFooterBottomFrameLayout();
        final TranslateAnimation moveOutAnimation = getFooterSwapAnimation(0, bottomFooterFrame.getHeight());

        moveOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}


            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                editImageCommunication.showManagementFragmentAndHideRotate();
                editImageCommunication.getManagementFooterFragment().getView().startAnimation(moveInAnimation);
            }
        });

        editImageCommunication.getRotateFooterFragment().getView().startAnimation(moveOutAnimation);
        return moveOutAnimation;
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

    static void showHideEffectsPanel(EditImageActivity editImageActivity) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(editImageActivity);
        Fragment effectFrag = editImageCommunication.getEffectFooterFragment();
        Fragment transformFrag = editImageCommunication.getTransformFooterFragment();

        if (editImageCommunication.getEffectFooterFragment().isVisible())
            hideTopFrag(editImageActivity, effectFrag);
        else if (editImageCommunication.getTransformFooterFragment().isVisible())
            hideOneFragmentAndShowSecond(editImageActivity, transformFrag, effectFrag);
        else
            showTopFrag(editImageActivity, effectFrag);
    }

    static void showHideTransformPanel(EditImageActivity editImageActivity) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(editImageActivity);
        Fragment effectFrag = editImageCommunication.getEffectFooterFragment();
        Fragment transformFrag = editImageCommunication.getTransformFooterFragment();

        if (editImageCommunication.getTransformFooterFragment().isVisible())
            hideTopFrag(editImageActivity, transformFrag);
        else if (editImageCommunication.getEffectFooterFragment().isVisible())
            hideOneFragmentAndShowSecond(editImageActivity, effectFrag, transformFrag);
        else
            showTopFrag(editImageActivity, transformFrag);
    }

    private static void hideTopFrag(final EditImageActivity editImageActivity, Fragment fragToHide) {
        final TranslateAnimation animationGetOutTopPanel = getOutTopFooterAnimation(editImageActivity);
        animationGetOutTopPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                topPanelAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                editImageActivity.getSupportFragmentManager().beginTransaction().hide(fragToHide).commit();
                topPanelAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (!topPanelAnimating)
            fragToHide.getView().startAnimation(animationGetOutTopPanel);
    }

    private static void hideOneFragmentAndShowSecond(final EditImageActivity editImageActivity, Fragment fragToHide, Fragment fragToShow) {
        final TranslateAnimation animationGetInTopPanel = getInTopFooterAnimation(editImageActivity);
        final TranslateAnimation animationGetOutTopPanel = getOutTopFooterAnimation(editImageActivity);
        animationGetOutTopPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                topPanelAnimating = true;

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                editImageActivity.getSupportFragmentManager().beginTransaction().hide(fragToHide).commit();
                fragToShow.getView().startAnimation(animationGetInTopPanel);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationGetInTopPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editImageActivity.getSupportFragmentManager().beginTransaction().show(fragToShow).commit();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topPanelAnimating = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if (!topPanelAnimating)
            fragToHide.getView().startAnimation(animationGetOutTopPanel);
    }

    private static void showTopFrag(final EditImageActivity editImageActivity, Fragment fragToShow) {
        final TranslateAnimation animationGetInTopPanel = getInTopFooterAnimation(editImageActivity);
        animationGetInTopPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editImageActivity.getSupportFragmentManager().beginTransaction().show(fragToShow).commit();
                topPanelAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                topPanelAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if (!topPanelAnimating)
            fragToShow.getView().startAnimation(animationGetInTopPanel);
    }

    private static TranslateAnimation getInTopFooterAnimation(Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        float fromDeltaY =  editImageCommunication.getFooterTopFrameLayout().getHeight(), toDeltaY = 0;

        return getFooterSwapAnimation(fromDeltaY, toDeltaY);
    }

    private static TranslateAnimation getOutTopFooterAnimation(Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        float fromDeltaY =  0, toDeltaY = editImageCommunication.getFooterTopFrameLayout().getHeight();
        return getFooterSwapAnimation(fromDeltaY, toDeltaY);
    }
}
