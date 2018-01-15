package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments.FooterBottomManagementFragment;
import com.kaizen.hoymm.ufoinphoto.R;

public class EditImageActivity extends AppCompatActivity
        implements EditImageActions, SelectImage, FooterComponents, AppAnimations {
    public static final int ANIMATIONS_DURATION = 460;
    private SectionHeaderButtons sectionHeaderButtons;
    private SectionCenterImg sectionCenterImg;
    private SectionFooter sectionFooter;

    @Override
    protected void onResume() {
        setDashedAndSelectedUFOImg();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        hideStatusBar();
        initSections();
    }

    private void initSections() {
        sectionHeaderButtons = new SectionHeaderButtons(this);
        sectionCenterImg = new SectionCenterImg(this);
        sectionFooter = new SectionFooter(this);
    }

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }


    @Override
    public void showManagementFragmentAndHideRotate() {
        getSupportFragmentManager().beginTransaction().hide(sectionFooter.footerBottomRotateFragment).commit();
        getSupportFragmentManager().beginTransaction().show(sectionFooter.footerBottomManagementFragment).commit();

        // TODO REMOVE CODE
        sectionFooter.getEXAMPLEobject();
        //...
    }

    @Override
    public void showReadyButton() {
        sectionHeaderButtons.showReadyButton();
    }

    @Override
    public void hideReadyButton() {
        sectionHeaderButtons.hideReadyButton();
    }

    @Override
    public void showHideFooterButtons() {
        showOrHideBottomFooterPanel();
    }

    private void showOrHideBottomFooterPanel() {
        boolean buttonsToShow [] = new boolean [5];
        buttonsToShow[0] = true;
        buttonsToShow[1] = buttonsToShow[2] = buttonsToShow[3] = sectionCenterImg.getSelectedItemIndex() != -1;
        buttonsToShow[4] = sectionCenterImg.howManyUFOObjectsCurrently() > 0;
        sectionFooter.footerBottomManagementFragment.showOrHideBottomFooterPanelButtonsAnimation(buttonsToShow);
    }

    @Override
    public void addNewUFOObj(int drawableImg) {
        sectionCenterImg.addUFOToPhoto(drawableImg);
        sectionCenterImg.addUFOToElementsList(drawableImg);
    }

    @Override
    public void selectLastUFOObject() {
        sectionCenterImg.selectLastUFO();
    }

    @Override
    public void selectUFOObjectByIndex(int index) {
        sectionCenterImg.selectUFOByIndex(index);
    }

    @Override
    public void selectUFOObject(UFOImageView ufoImageView) {
        sectionCenterImg.selectUFO(ufoImageView);
    }

    @Override
    public void removeCurUFO() {
        sectionCenterImg.removeCurUFOObj();
    }

    @Override
    public void removeDashedBorder() {
        sectionCenterImg.clearDashedBoard();
    }

    @Override
    public void setDashedAndSelectedUFOImg() {
        if (sectionCenterImg != null) {
            sectionCenterImg.setDashedBorderIfAnySelected();
        }
    }

    @Override
    public void selectCurrent() {
        sectionCenterImg.setSelected(true);
    }

    @Override
    public void deselectCurrent() {
        sectionCenterImg.setSelected(false);
    }

    @Override
    public void swapRotatePanelToManagement() {
        FooterAnimation.swapRotatePanelToManagement(this);
    }

    @Override
    public void showHideEffectsPanel() {
        FooterAnimation.showHideEffectsPanel(this);
    }

    @Override
    public void showHideTransformPanel() {
        FooterAnimation.showHideTransformPanel(this);

    }

    @Override
    public FrameLayout getFooterBottomFrameLayout() {
        return sectionFooter.footerBottomFrameLayout;
    }

    @Override
    public FrameLayout getFooterTopFrameLayout() {
        return sectionFooter.footerTopFrameLayout;
    }

    @Override
    public FooterBottomManagementFragment getManagementFooterFragment() {
        return sectionFooter.footerBottomManagementFragment;
    }

    @Override
    public Fragment getRotateFooterFragment() {
        return sectionFooter.footerBottomRotateFragment;
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
        sectionCenterImg.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (sectionCenterImg.isElementsListShown())
            showHideUFOElementsPanel();
        else
            super.onBackPressed();
    }

    @Override
    public void showHideUFOElementsPanel() {
        sectionCenterImg.hideOrShowUFOElementsPanel();
    }

    @Override
    public Fragment getEffectFooterFragment() {
        return sectionFooter.footerTopEffectsFragment;
    }

    @Override
    public Fragment getTransformFooterFragment() {
        return sectionFooter.footerTopTransformFragment;
    }
}
