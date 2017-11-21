package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v4.app.Fragment;

/**
 * Created by hoymm on 20.11.17.
 */

interface EditImageCommunication {
    void showReadyButton();
    void startAnimationHideRotateFooterThenShowAddObjFooter();
    void addNewFragment(int ID, Fragment fragment);
}
