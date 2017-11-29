package com.kaizen.hoymm.ufoinphoto.EditImageActivity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by hoymm on 20.11.17.
 */

interface EditImageCommunication {
    void showReadyButton();
    void hideReadyButton();
    void showHideFooterButtonsAnimation();
    void addNewUFOObj(int drawableImg);


    void changeFooterPanelFromRotateToManagementFragmentUsingAnimation();
    FrameLayout getFooterFrameLayout();
    Fragment getManagementFooterFragment();
    Fragment getRotateFooterFragment();
    void showManagementFooterFragmentAndHideRotate();

    Bitmap getEditedImage();
}
