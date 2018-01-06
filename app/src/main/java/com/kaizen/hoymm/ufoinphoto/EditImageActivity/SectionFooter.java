package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments.FooterBottomManagementFragment;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments.FooterBottomRotateFragment;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments.FooterTopEffectsFragment;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments.FooterTopTransformFragment;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by Damian Muca (Kaizen) on 13.12.17.
 */

class SectionFooter {
    private EditImageActivity activity;
    FrameLayout footerFrameLayout;
    FooterBottomRotateFragment footerBottomRotateFragment;
    FooterBottomManagementFragment footerBottomManagementFragment;

    FooterTopEffectsFragment footerTopEffectsFragment;
    FooterTopTransformFragment footerTopTransformFragment;

    SectionFooter(EditImageActivity activity) {
        this.activity = activity;

        initFooterFragments();
        addFooterFragments();
        showFooterRotateFragmentAndHideOthers();
        initFooterFrame();
    }

    private void initFooterFragments() {
        footerBottomRotateFragment = new FooterBottomRotateFragment();
        footerBottomManagementFragment = new FooterBottomManagementFragment();

        footerTopEffectsFragment = new FooterTopEffectsFragment();
        footerTopTransformFragment = new FooterTopTransformFragment();
    }

    private void addFooterFragments() {
        addNewFragment(R.id.footerBottomFrameId, footerBottomManagementFragment);
        addNewFragment(R.id.footerBottomFrameId, footerBottomRotateFragment);

        addNewFragment(R.id.footerTopFrameId, footerTopEffectsFragment);
        addNewFragment(R.id.footerTopFrameId, footerTopTransformFragment);
    }

    private void showFooterRotateFragmentAndHideOthers() {
        activity.getSupportFragmentManager().beginTransaction().show(footerBottomRotateFragment).commit();
        activity.getSupportFragmentManager().beginTransaction().hide(footerBottomManagementFragment).commit();
    }

    private void initFooterFrame() {
        footerFrameLayout = (FrameLayout) activity.findViewById(R.id.footerBottomFrameId);
    }

    private void addNewFragment(int ID, Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit();
    }
}
