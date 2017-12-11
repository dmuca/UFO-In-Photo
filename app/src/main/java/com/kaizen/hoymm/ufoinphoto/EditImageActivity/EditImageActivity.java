package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.kaizen.hoymm.ufoinphoto.R;

public class EditImageActivity extends AppCompatActivity implements EditImageCommunication {
    public static final int ANIMATIONS_DURATION = 300;
    private HeaderButtonsSection headerButtonsSection;
    private CenterImgSection centerImgSection;

    // FOOTER (bottom) SECTION
    private FrameLayout footerFrameLayout;
    private FooterRotateFragment footerRotateFragment;
    private FooterManagementFragment footerManagementFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        hideStatusBar();

        initHeaderButtonsSection();
        initCenterImgSection();


        initFooterFragments();
        addFooterFragments();
        showFooterRotateFragmentAndHideOthers();
        initFooterFrame();
    }

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void initHeaderButtonsSection() {
        headerButtonsSection = new HeaderButtonsSection(this);
    }

    private void initCenterImgSection() {
        centerImgSection = new CenterImgSection(this);
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
        getSupportFragmentManager().beginTransaction().show(footerRotateFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(footerManagementFragment).commit();
    }

    private void initFooterFrame() {
        footerFrameLayout = (FrameLayout) findViewById(R.id.footerFrameId);
    }

    private void addNewFragment(int ID, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showManagementFooterFragmentAndHideRotate() {
        getSupportFragmentManager().beginTransaction().hide(footerRotateFragment).commit();
        getSupportFragmentManager().beginTransaction().show(footerManagementFragment).commit();
    }

    @Override
    public void showReadyButton() {
        headerButtonsSection.showReadyButton();
    }

    @Override
    public void hideReadyButton() {
        headerButtonsSection.hideReadyButton();
    }

    @Override
    public void showHideFooterButtonsAnimation() {
        boolean buttonsToShow [] = new boolean [5];
        buttonsToShow[0] = true;
        buttonsToShow[1] = buttonsToShow[2] = buttonsToShow[3] = centerImgSection.getSelectedItemIndex() != -1;
        buttonsToShow[4] = centerImgSection.howManyUFOObjectsCurrently() > 0;

        footerManagementFragment.showOrHideFooterPanelButtonsAnimation(buttonsToShow);
    }

    @Override
    public void addNewUFOObj(int drawableImg) {
        centerImgSection.addUFOToMainPhoto(drawableImg);
        centerImgSection.addUFOToElementsList(drawableImg);
    }

    @Override
    public void removeCurrentlySelectedUFOObj() {
        centerImgSection.removeCurUFOObj();
    }

    @Override
    public void changeFooterPanelFromRotateToManagementFragmentUsingAnimation() {
        SwapRotateToManagementFooterPanelAnimation.showFooterLayout(this);
    }

    @Override
    public FrameLayout getFooterFrameLayout() {
        return footerFrameLayout;
    }

    @Override
    public FooterManagementFragment getManagementFooterFragment() {
        return footerManagementFragment;
    }

    @Override
    public Fragment getRotateFooterFragment() {
        return footerRotateFragment;
    }

    @Override
    public Bitmap getEditedImage() {
        RelativeLayout editImageFrameRL = (RelativeLayout) findViewById(R.id.editImageFrameId);
        editImageFrameRL.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(editImageFrameRL.getDrawingCache());
        editImageFrameRL.setDrawingCacheEnabled(false);
        return bitmap;
    }

    @Override
    public void notifyElemenetsListDataChanged() {
        centerImgSection.notifyDataSetChanged();
    }

    @Override
    public void hideShowUFOElementsPanel() {
        centerImgSection.hideOrShowUFOElementsPanel();
    }

    @Override
    public void onBackPressed() {
        if (centerImgSection.isElementsListShown())
            hideShowUFOElementsPanel();
        else
            super.onBackPressed();
    }
}
