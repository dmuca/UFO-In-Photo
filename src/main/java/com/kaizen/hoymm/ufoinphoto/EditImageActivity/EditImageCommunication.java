package com.kaizen.hoymm.ufoinphoto.EditImageActivity;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by hoymm on 20.11.17.
 */

interface EditImageCommunication {
    void showReadyButton();
    void changeFooterPanelFromRotateToManagementFragmentUsingAnimation();
    FrameLayout getFooterFrameLayout();
    Fragment getManagementFooterFragment();
    Fragment getRotateFooterFragment();
    void showManagementFooterFragmentAndHideRotate();
}
