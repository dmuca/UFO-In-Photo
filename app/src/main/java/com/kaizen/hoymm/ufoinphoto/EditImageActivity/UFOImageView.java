package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Damian Muca (Kaizen) on 19.12.17
 */

public class UFOImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener{
    private float last_X, last_Y;
    private SelectImage selectImageInterface;
    private AppAnimations appAnimations;

    public UFOImageView(Context context) {
        super(context);
        setOnTouchListener(this);
        selectImageInterface = (SelectImage) context;
        appAnimations = (AppAnimations) context;

        setOnClickListener(v -> {
            selectImageInterface.selectUFOObject(this);
            appAnimations.showHideFooterButtons();
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (!this.isSelected())
            return false;


        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                last_X = X;
                last_Y = Y;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                view.setX(view.getX() + X - last_X);
                view.setY(view.getY() + Y - last_Y);
                last_X = X;
                last_Y = Y;
                break;
        }
        view.invalidate();
        return true;
    }
}
