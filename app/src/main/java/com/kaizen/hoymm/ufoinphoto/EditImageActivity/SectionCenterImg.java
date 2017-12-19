package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import java.util.ArrayList;

import static com.kaizen.hoymm.ufoinphoto.MainActivity.URI_OF_ORIGINAL_IMAGE_KEY;

/**
 * Created by Damian Muca (Kaizen) on 11.12.17.
 */

class SectionCenterImg {
    private EditImageActivity activity;
    private RelativeLayout areaWithImages;

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
        areaWithImages.removeView(myUFOObjects.get(getSelectedItemIndex()));
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

    void addUFOToPhoto(int drawableImg) {
        areaWithImages = (RelativeLayout) activity.findViewById(R.id.editImageFrameId);
        ImageView imgUFO = new ImageView(activity);
        imgUFO.setImageResource(drawableImg);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(areaWithImages.getWidth()/2, areaWithImages.getHeight()/2);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        areaWithImages.addView(imgUFO, params);
        myUFOObjects.add(imgUFO);
    }

    void selectLastUFO() {
        elementsListViewRecycler.selectUFO(myUFOObjects.size()-1);
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

    void clearDashedBoard() {
        myUFOObjects.get(getSelectedItemIndex()).setBackground(null);
    }

    void setDashedBoard() {
        myUFOObjects.get(getSelectedItemIndex()).setBackground(
                ContextCompat.getDrawable(activity.getBaseContext(),R.drawable.dashed_background));
    }
}
