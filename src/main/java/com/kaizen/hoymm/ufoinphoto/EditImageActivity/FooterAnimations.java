package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterAnimations{
    private static final int ANIMATIONS_DURATION = 300;

    public static void startHideRotateFooterThenShowManagementFooter(final FrameLayout footerFrameLayout, Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final TranslateAnimation getInAnimation =
                getMoveInAnimation(footerFrameLayout, editImageCommunication, new FooterManagementFragment());
        final TranslateAnimation getOutAnimation =
                getPullDownAnimation(footerFrameLayout, getInAnimation);
        getOutAnimation.start();
        
    }

    @NonNull
    private static TranslateAnimation getMoveInAnimation(FrameLayout footerFrameLayout
            , final EditImageCommunication editImageCommunication
            , final Fragment fragmentToInflate) {
        final TranslateAnimation getInAnimation =
                new TranslateAnimation(0, 0, footerFrameLayout.getHeight(), 0);
        getInAnimation.setDuration(ANIMATIONS_DURATION);

        getInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editImageCommunication.addNewFragment(R.id.footerFrameId, fragmentToInflate);
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

    @NonNull
    private static TranslateAnimation getPullDownAnimation(final FrameLayout footerFrameLayout, final TranslateAnimation getInAnimation) {
        final TranslateAnimation getOutAnimation =
                new TranslateAnimation(0, 0, 0, footerFrameLayout.getHeight());
        getOutAnimation.setDuration(ANIMATIONS_DURATION);
        footerFrameLayout.setAnimation(getOutAnimation);
        getOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                footerFrameLayout.setAnimation(getInAnimation);
                getInAnimation.start();
            }
        });
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
