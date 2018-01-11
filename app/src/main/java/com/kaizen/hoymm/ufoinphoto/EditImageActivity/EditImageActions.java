package com.kaizen.hoymm.ufoinphoto.EditImageActivity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by hoymm on 20.11.17.
 */

public interface EditImageActions {
    void addNewUFOObj(int drawableImg);
    Bitmap getEditedImage();
    void notifyElemenetsListDataChanged();
    void removeCurUFO();
    void removeDashedBorder();
    void setDashedAndSelectedUFOImg();
}
