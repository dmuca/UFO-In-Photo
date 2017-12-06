package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.ANIMATIONS_DURATION;

/**
 * Created by Damian Muca (Kaizen) on 21.11.17.
 */

public class SwapRotateToManagementFooterPanelAnimation {

    static void showFooterLayout(Context context){
        final TranslateAnimation animationGetInManagementPanel = getMoveInAnimation(context);
        animateFooterMoveOut(context, animationGetInManagementPanel);
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
    private static TranslateAnimation animateFooterMoveOut(Context context, final TranslateAnimation moveInAnimation) {
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
                editImageCommunication.getManagementFooterFragment().getView().startAnimation(moveInAnimation);
            }
        });

        editImageCommunication.getRotateFooterFragment().getView().startAnimation(moveOutAnimation);
        Log.i("FragName 1", editImageCommunication.getManagementFooterFragment().getClass().getName());
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
}
