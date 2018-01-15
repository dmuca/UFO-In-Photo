package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;

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


        mScaleDetector = new ScaleGestureDetector(this.getContext(), new ScaleListener());
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        Log.i("Scalling", "mScaleFactor: " + mScaleFactor);

        this.setScaleX(mScaleFactor);
        this.setScaleY(mScaleFactor);

/*

        this.getLayoutParams().height *= mScaleFactor;
        this.getLayoutParams().width *= mScaleFactor;
*/

        canvas.scale(mScaleFactor, mScaleFactor);
        // onDraw() code goes here
        canvas.restore();
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (!this.isSelected())
            return false;
/*
        view.setScaleX(1.4f);
        view.setScaleY(2.3f);*/

        moveImage(view, event);
        mScaleDetector.onTouchEvent(event);
        return true;
    }

    private PointFloat downPoint = new PointFloat(0f, 0f);
    private boolean allowMoving = true;
    private void moveImage(View view, MotionEvent event) {
        if (event.getPointerCount() > 1)
            allowMoving = false;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (event.getPointerCount() == 1)
                    allowMoving = true;

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
                if (allowMoving) {
                    final int pointerIndex = event.findPointerIndex(mActivePointerId);
                    if (pointerIndex != -1) {
                        final PointFloat curPoint = new PointFloat(event.getX(pointerIndex), event.getY(pointerIndex));

                    /*
                    Log.i("SetX",
                            "view.getX(): " + view.getX()
                            + ", curPoint.X: " + curPoint.X
                            + ", downPoint.X: " + downPoint.X);*/
                        Log.i("SetY",
                                "view.getY(): " + view.getY()
                                        + ", curPoint.Y: " + curPoint.Y
                                        + ", downPoint.Y: " + downPoint.Y);

                        view.setX(view.getX() + curPoint.X - downPoint.X);
                        view.setY(view.getY() + curPoint.Y - downPoint.Y);
                        lastTouch.X = curPoint.X;
                        lastTouch.Y = curPoint.Y;
                    }
                }
                break;
        }
    }
}
