package com.kaizen.hoymm.ufoinphoto.EditImageActivity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by hoymm on 20.11.17.
 */

public interface EditImageCommunication {
    void showReadyButton();
    void hideReadyButton();
    void showHideFooterButtonsAnimation();

    void addNewUFOObj(int drawableImg);

    void swapRotatePanelToManagement();
    FrameLayout getFooterBottomFrameLayout();
    FrameLayout getFooterTopFrameLayout();
    Fragment getManagementFooterFragment();
    Fragment getRotateFooterFragment();
    Fragment getEffectFooterFragment();
    Fragment getTransformFooterFragment();
    void showManagementFragmentAndHideRotate();

    Bitmap getEditedImage();

    void notifyElemenetsListDataChanged();
    void hideShowFooterElementsPanel();
    void showHideEffectsPanel();
    void showHideTransformPanel();

    void removeCurUFO();
    void removeDashedBorder();
    void setDashedAndSelectedUFOImg();
}
