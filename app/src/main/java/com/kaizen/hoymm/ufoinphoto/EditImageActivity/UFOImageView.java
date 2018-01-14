package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * Created by Damian Muca (Kaizen) on 19.12.17
 */

public class UFOImageView extends android.support.v7.widget.AppCompatImageView implements View.OnTouchListener{
    private int mActivePointerId = INVALID_POINTER_ID;
    private PointFloat lastTouch = new PointFloat(0f, 0f);
    private SelectImage selectImageInterface;
    private AppAnimations appAnimations;

    private class PointFloat {
        float X,Y;
        PointFloat(float x, float y) {
            X = x;
            Y = y;
        }
    }


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


        moveImage(view, event);
        changeImgSizeOrRotate(view, event);
        return true;
    }

    PointFloat downPoint = new PointFloat(0f, 0f);
    private void moveImage(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(0);
                downPoint = new PointFloat(event.getX(), event.getY());
                lastTouch.X = event.getX();
                lastTouch.Y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                mActivePointerId = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP: {

                final int oldPointerIndex = event.getActionIndex();
                final int pointerId = event.getPointerId(oldPointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = oldPointerIndex == 0 ? 1 : 0;
                    lastTouch.X = event.getX(newPointerIndex);
                    lastTouch.Y = event.getY(newPointerIndex);
                    mActivePointerId = event.getPointerId(newPointerIndex);
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER_ID;
                break;
            case MotionEvent.ACTION_MOVE:
                final int pointerIndex = event.findPointerIndex(mActivePointerId);
                final PointFloat curPoint = new PointFloat(event.getX(pointerIndex), event.getY(pointerIndex));
                view.setX(view.getX() + curPoint.X - downPoint.X);
                view.setY(view.getY() + curPoint.Y - downPoint.Y);
                lastTouch.X = curPoint.X;
                lastTouch.Y = curPoint.Y;
                break;
        }
    }

    private void changeImgSizeOrRotate(View view, MotionEvent event) {

    }
}
