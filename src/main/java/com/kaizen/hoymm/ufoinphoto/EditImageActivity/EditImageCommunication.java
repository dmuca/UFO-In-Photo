package com.kaizen.hoymm.ufoinphoto.EditImageActivity;
import android.widget.FrameLayout;

/**
 * Created by hoymm on 20.11.17.
 */

interface EditImageCommunication {
    void showReadyButton();
    void changeButtonsToShowInFooter(boolean [] buttonsToShow);
    void addFooterManagementPanelFragmentIfNotAlreadyAdded();
    FrameLayout getFooterFrameLayout();
}
