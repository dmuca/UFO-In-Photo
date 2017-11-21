package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;
public class EditImage extends AppCompatActivity implements EditImageCommunication {
    public static final String URI_OF_PICKED_IMAGE_KEY =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_PICKED_IMAGE_KEY";

    private HeaderButtons headerButtons;
    private FrameLayout footerFrameLayout;
    private FooterRotateFragment footerRotateFragment;
    private FooterManagementFragment footerManagementFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);
        hideStatusBar();
        recieveImage();
        initHeaderButtons();
        initFooterFrame();
        initFragments();
        addNewFragment(R.id.footerFrameId , footerRotateFragment);
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

    private void initHeaderButtons() {
        headerButtons = new HeaderButtons(this);
        headerButtons.hideReadyButton();
    }

    private void initFooterFrame() {
        footerFrameLayout = (FrameLayout) findViewById(R.id.footerFrameId);
    }

    private void initFragments() {
        footerRotateFragment = new FooterRotateFragment();
        footerManagementFragment = new FooterManagementFragment();
    }

    private void addNewFragment(int ID, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(ID, fragment);
        fragmentTransaction.commit();
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

    @Override
    public void showReadyButton() {
        headerButtons.showReadyButton();
    }

    @Override
    public void showManagementFragment() {

        final TranslateAnimation getOutAnimation =
                new TranslateAnimation(0, 0, 0, footerFrameLayout.getHeight());

        final TranslateAnimation getInAnimation =
                new TranslateAnimation(0, 0, footerFrameLayout.getHeight(), 0);

        getOutAnimation.setDuration(300);

        getInAnimation.setDuration(300);
        //getInAnimation.setFillAfter(true);

        footerFrameLayout.setAnimation(getOutAnimation);
        getOutAnimation.start();

        getOutAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){}

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {
                footerFrameLayout.setAnimation(getInAnimation);
                getInAnimation.start();
            }
        });

        getInAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation){
                addNewFragment(R.id.footerFrameId, footerManagementFragment);
            }

            @Override
            public void onAnimationRepeat(Animation animation){}

            @Override
            public void onAnimationEnd(Animation animation)
            {}
        });
    }
}
