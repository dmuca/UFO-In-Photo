package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

public class EditImage extends AppCompatActivity implements EditImageCommunication {
    public static final String URI_OF_PICKED_IMAGE_KEY =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_PICKED_IMAGE_KEY";

    private HeaderButtons headerButtons;
    private FrameLayout footerFrameLayout;
    private Fragment footerManagementFragment, footerRotateFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        hideStatusBar();
        recieveImage();
        initFragments();
        addFragments();
        showFooterRotateFragmentAndHideOthers();
        initHeaderButtons();
        initFooterFrame();
    }

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void recieveImage() {
        Uri imageUri = getIntent().getParcelableExtra(URI_OF_PICKED_IMAGE_KEY);
        setImageUsingUri(imageUri);
    }

    private void initFragments() {
        footerRotateFragment = new FooterRotateFragment();
        footerManagementFragment = new FooterManagementFragment();
    }

    private void addFragments() {
        addNewFragment(R.id.footerFrameId , footerManagementFragment);
        addNewFragment(R.id.footerFrameId , footerRotateFragment);
    }

    private void showFooterRotateFragmentAndHideOthers() {
        getSupportFragmentManager().beginTransaction().show(footerRotateFragment).commit();
        getSupportFragmentManager().beginTransaction().hide(footerManagementFragment).commit();
    }

    private void initHeaderButtons() {
        headerButtons = new HeaderButtons(this);
        headerButtons.hideReadyButton();
    }

    private void initFooterFrame() {
        footerFrameLayout = (FrameLayout) findViewById(R.id.footerFrameId);
    }

    public void setImageUsingUri(Uri imageUri) {
        try {
            ImageView imageToEditImageView = (ImageView) findViewById(R.id.imageToEditId);
            imageToEditImageView.setImageURI(imageUri);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("EditImage", " unable to load image from URI: " + imageUri.toString());
        }
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
        headerButtons.showReadyButton();
    }

    @Override
    public void changeFooterPanelFromRotateToManagementFragmentUsingAnimation() {
        FooterComponentChanges.showFooterLayoutWithSelectedButtons(this);
    }

    @Override
    public FrameLayout getFooterFrameLayout() {
        return footerFrameLayout;
    }

    @Override
    public Fragment getManagementFooterFragment() {
        return footerManagementFragment;
    }

    @Override
    public Fragment getRotateFooterFragment() {
        return footerRotateFragment;
    }
}
