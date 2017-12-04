package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import java.util.ArrayList;

public class EditImageActivity extends AppCompatActivity implements EditImageCommunication {
    public static final String URI_OF_PICKED_IMAGE_KEY =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_PICKED_IMAGE_KEY";
    public static final String BITMAP_OF_PHOTO_CAPTURED =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.BITMAP_OF_PHOTO_CAPTURED";

    private HeaderButtons headerButtons;
    private FrameLayout footerFrameLayout;
    private FooterAddPhotoFragment footerManagementFragment;
    private FooterRotateFragment footerRotateFragment;

    private ArrayList<ImageView> myUFOObjects;
    private int indexOfSelectedUFO = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        myUFOObjects = new ArrayList<>();
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
        Bitmap bitmapPhoto = getIntent().getParcelableExtra(BITMAP_OF_PHOTO_CAPTURED);
        if (imageUri != null) {
            setImageUsingUri(imageUri);
            Log.e("GetPassedPhoto", "photo catched by bitmap.");
        }
        else if (bitmapPhoto != null) {
            setImageUsingBitmap(bitmapPhoto);
            Log.e("GetPassedPhoto", "photo catched by bitmap.");
        }
        else
            Log.e("GetPassedPhoto", "problem with catching and showing photo.");

    }

    private void initFragments() {
        footerRotateFragment = new FooterRotateFragment();
        footerManagementFragment = new FooterAddPhotoFragment();
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
            Log.e("EditImageActivity", " unable to load image from URI: " + imageUri.toString());
        }
    }

    public void setImageUsingBitmap(Bitmap photo) {
        try {
            ImageView imageToEditImageView = (ImageView) findViewById(R.id.imageToEditId);
            imageToEditImageView.setImageBitmap(photo);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("EditImageActivity", " unable to load bitmap: " + photo);
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
    public void hideReadyButton() {
        headerButtons.hideReadyButton();
    }

    @Override
    public void showHideFooterButtonsAnimation() {
        boolean buttonsToShow [] = new boolean [5];
        buttonsToShow[0] = true;
        buttonsToShow[1] = buttonsToShow[2] = buttonsToShow[3] = indexOfSelectedUFO != -1;
        buttonsToShow[4] = myUFOObjects.size() > 0;

        footerManagementFragment.showOrHideFooterPanelButtonsAnimation(buttonsToShow);
    }

    @Override
    public void addNewUFOObj(int drawableImg) {
        RelativeLayout editImageFrameRL = (RelativeLayout) findViewById(R.id.editImageFrameId);
        ImageView imgUFO = new ImageView(this);
        imgUFO.setImageResource(drawableImg);
        imgUFO.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // set params etc.
        editImageFrameRL.addView(imgUFO, editImageFrameRL.getWidth(), editImageFrameRL.getHeight());
        myUFOObjects.add(imgUFO);
        selectNewUfoObj(myUFOObjects.size()-1);

    }

    private void selectNewUfoObj(int index){
        indexOfSelectedUFO = index;
        Log.i("Index", "set to " + indexOfSelectedUFO);
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
    public FooterAddPhotoFragment getManagementFooterFragment() {
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
}
