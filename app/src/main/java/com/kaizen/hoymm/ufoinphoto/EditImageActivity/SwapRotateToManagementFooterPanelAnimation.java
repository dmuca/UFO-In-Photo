package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

/**
 * Created by Damian Muca (Kaizen) on 21.11.17.
 */

public class SwapRotateToManagementFooterPanelAnimation {
    static final int ANIMATIONS_DURATION = 300;

    static void showFooterLayout(Context context){
        final TranslateAnimation animationGetInManagementPanel = getMoveInAnimation(context);
        final TranslateAnimation animationGetOutManagementPanel = getMoveOutAnimation(context, animationGetInManagementPanel);
        animationGetOutManagementPanel.start();
    }

    @NonNull
    private static TranslateAnimation getMoveInAnimation(Context context) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final TranslateAnimation moveInAnimation =
                new TranslateAnimation(0, 0, editImageCommunication.getFooterFrameLayout().getHeight(), 0);
        moveInAnimation.setDuration(ANIMATIONS_DURATION);

        return moveInAnimation;
    }

    @NonNull
    private static TranslateAnimation getMoveOutAnimation(Context context, final TranslateAnimation moveInAnimation) {
        final EditImageCommunication editImageCommunication = tryToInitEditImageCommunication(context);
        final FrameLayout frameLayout = editImageCommunication.getFooterFrameLayout();
        final TranslateAnimation moveOutAnimation = new TranslateAnimation(0, 0, 0, frameLayout.getHeight());
        moveOutAnimation.setDuration(ANIMATIONS_DURATION);

        moveOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                editImageCommunication.showManagementFooterFragmentAndHideRotate();
                setAnimationForChoosenFragment(editImageCommunication.getManagementFooterFragment(), moveInAnimation);
                moveInAnimation.start();

            }
        });

        setAnimationForChoosenFragment(editImageCommunication.getRotateFooterFragment(), moveOutAnimation);
        Log.i("FragName 1", editImageCommunication.getManagementFooterFragment().getClass().getName());
        return moveOutAnimation;
    }

    private static void setAnimationForChoosenFragment(Fragment fragment, TranslateAnimation animation) {
        View view = fragment.getView();
        if (view != null) {
            view.setAnimation(animation);
        }
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
