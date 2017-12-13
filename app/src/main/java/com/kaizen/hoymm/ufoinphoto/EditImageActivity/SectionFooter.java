package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by Damian Muca (Kaizen) on 13.12.17.
 */

class SectionFooter {
    private EditImageActivity activity;
    FrameLayout footerFrameLayout;
    FooterRotateFragment footerRotateFragment;
    FooterManagementFragment footerManagementFragment;


    SectionFooter(EditImageActivity activity) {
        this.activity = activity;

        initFooterFragments();
        addFooterFragments();
        showFooterRotateFragmentAndHideOthers();
        initFooterFrame();
    }

    private void initFooterFragments() {
        footerRotateFragment = new FooterRotateFragment();
        footerManagementFragment = new FooterManagementFragment();
    }

    private void addFooterFragments() {
        addNewFragment(R.id.footerFrameId , footerManagementFragment);
        addNewFragment(R.id.footerFrameId , footerRotateFragment);
    }

    private void showFooterRotateFragmentAndHideOthers() {
        activity.getSupportFragmentManager().beginTransaction().show(footerRotateFragment).commit();
        activity.getSupportFragmentManager().beginTransaction().hide(footerManagementFragment).commit();
    }

    private void initFooterFrame() {
        footerFrameLayout = (FrameLayout) activity.findViewById(R.id.footerFrameId);
    }

    private void addNewFragment(int ID, Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit();
    }
}
