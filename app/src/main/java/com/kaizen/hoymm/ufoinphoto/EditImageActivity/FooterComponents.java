package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

/**
 * Created by Damian Muca (Kaizen) on 11.01.18.
 */

public interface FooterComponents {
    FrameLayout getFooterBottomFrameLayout();
    FrameLayout getFooterTopFrameLayout();
    Fragment getManagementFooterFragment();
    Fragment getRotateFooterFragment();
    Fragment getEffectFooterFragment();
    Fragment getTransformFooterFragment();
}
