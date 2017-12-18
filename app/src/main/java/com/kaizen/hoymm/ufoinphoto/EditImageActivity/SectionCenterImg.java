package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import java.util.ArrayList;

import static com.kaizen.hoymm.ufoinphoto.MainActivity.URI_OF_ORIGINAL_IMAGE_KEY;

/**
 * Created by Damian Muca (Kaizen) on 11.12.17.
 */

class SectionCenterImg {
    private EditImageActivity activity;
    private RelativeLayout areWithImages;

    private ElementsListViewRecycler elementsListViewRecycler;
    private ImageView imageToEditImageView = null;
    private static final String URI_OF_EDITED_IMAGE_KEY =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.URI_OF_EDITED_IMAGE_KEY";
    private static final String BITMAP_OF_PHOTO_CAPTURED =
            "com.kaizen.hoymm.ufoinphoto.EditImageActivity.BITMAP_OF_PHOTO_CAPTURED";
    private ArrayList<ImageView> myUFOObjects = new ArrayList<>();

    SectionCenterImg(EditImageActivity activity) {
        this.activity = activity;
        initReciveAndSetOnClickListenerForMainImg();
        elementsListViewRecycler = new ElementsListViewRecycler(activity);
    }

    private void initReciveAndSetOnClickListenerForMainImg() {
        imageToEditImageView = (ImageView) activity.findViewById(R.id.imageToEditId);
        recieveAnImageAndSetItsPreview();
        setMainImgClickListener();
    }

    void addUFOToElementsList(int drawableImg) {
        elementsListViewRecycler.addItem(drawableImg);
        elementsListViewRecycler.notifyDataSetChanged();
    }

    void removeCurUFOObj() {
        removeObjectFromImage();
        elementsListViewRecycler.removeCurUFOObj();
        elementsListViewRecycler.notifyDataSetChanged();
    }

    private void removeObjectFromImage() {
        areWithImages.removeView(myUFOObjects.get(getSelectedItemIndex()));
        myUFOObjects.remove(getSelectedItemIndex());
    }

    private void recieveAnImageAndSetItsPreview() {
        Uri imageUri = activity.getIntent().getParcelableExtra(URI_OF_ORIGINAL_IMAGE_KEY);
        Bitmap bitmapPhoto = activity.getIntent().getParcelableExtra(BITMAP_OF_PHOTO_CAPTURED);
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

    private void setImageUsingUri(Uri imageUri) {
        try {
            imageToEditImageView.setImageURI(imageUri);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("EditImageActivity", " unable to load image from URI: " + imageUri.toString());
        }
    }

    private void setImageUsingBitmap(Bitmap photo) {
        try {
            imageToEditImageView.setImageBitmap(photo);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.e("EditImageActivity", " unable to load bitmap: " + photo);
        }
    }

    private void setMainImgClickListener() {
        imageToEditImageView.setOnClickListener(v -> {
            if (elementsListViewRecycler.isElementsListShown())
                elementsListViewRecycler.hideOrShowUFOElementsPanel();
        });
    }

    void addUFOToMainPhoto(int drawableImg) {
        areWithImages = (RelativeLayout) activity.findViewById(R.id.editImageFrameId);
        ImageView imgUFO = new ImageView(activity);
        imgUFO.setImageResource(drawableImg);
        imgUFO.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // set params etc.
        areWithImages.addView(imgUFO, areWithImages.getWidth(), areWithImages.getHeight());
        myUFOObjects.add(imgUFO);
        setSelectedItemIndex(myUFOObjects.size()-1);
    }

    private void setSelectedItemIndex(int newIndex) {
        elementsListViewRecycler.setSelectedItemIndex(newIndex);
    }

    void notifyDataSetChanged() {
        elementsListViewRecycler.notifyDataSetChanged();
    }


    void hideOrShowUFOElementsPanel() {
        elementsListViewRecycler.hideOrShowUFOElementsPanel();
    }

    boolean isElementsListShown() {
        return elementsListViewRecycler.isElementsListShown();
    }

    int getSelectedItemIndex() {
        return elementsListViewRecycler.getSelectedItemIndex();
    }

    int howManyUFOObjectsCurrently() {
        return myUFOObjects.size();
    }
}
