package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ChooseUFOActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

public class ChooseUFOActivity extends AppCompatActivity implements ChangeMainImage {
    private int mainImgDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ufo);
        initAndSetAdapterForChoosingUFOImg();
    }

    private void initAndSetAdapterForChoosingUFOImg() {
        RecyclerView recyclerViewUFOImages = (RecyclerView) findViewById(R.id.UFOToChooseId);
        recyclerViewUFOImages.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUFOImages.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new ChooseUFOFooterAdapter(this);
        recyclerViewUFOImages.setAdapter(mAdapter);
    }

    @Override
    public void changeMainImage(int imageDrawable) {
        ImageView UFOMainImage = (ImageView) findViewById(R.id.UFOMainImage);
        UFOMainImage.setImageResource(imageDrawable);
        mainImgDrawable = imageDrawable;
    }
}
