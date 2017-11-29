package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ChooseUFOActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

public class ChooseUFOActivity extends AppCompatActivity implements ChangeMainImage {
    private int mainImgDrawable = R.drawable.img1;
    private ImageView UFOMainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ufo);
        initUFOMainImageAndSetListener();
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

    private void initUFOMainImageAndSetListener() {
        UFOMainImage = (ImageView) findViewById(R.id.UFOMainImage);
        UFOMainImage.setOnClickListener(getOnMainImageClickListener());
    }

    private View.OnClickListener getOnMainImageClickListener() {
        return v -> {

        };
    }

    @Override
    public void changeMainImage(int imageDrawable) {
        UFOMainImage.setImageResource(imageDrawable);
        mainImgDrawable = imageDrawable;
    }
}
