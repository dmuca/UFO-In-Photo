package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.ANIMATIONS_DURATION;

/**
 * Created by Damian Muca (Kaizen) on 06.12.17.
 */

class ElementsListViewRecycler {

    private LinearLayout viewBoxOfRecyclerView;
    private RecyclerView recyclerView;
    private boolean anyAnimationWorking = false;

    private TranslateAnimation showElementsListPanel;
    private TranslateAnimation hideElementsListPanel;

    private ElementsListViewAdapter elementsRecyclerViewAdapter;
    private EditImageActivity activity;

    ElementsListViewRecycler(EditImageActivity appCompatActivity) {
        this.activity = appCompatActivity;
        initXMLObjects();
        initAndSetAdapterForElementsListPanel();
    }

    private void initXMLObjects() {
        viewBoxOfRecyclerView = (LinearLayout) activity.findViewById(R.id.box_for_recycler_view_id);
        recyclerView = (RecyclerView) activity.findViewById(R.id.elements_list_recycler_view_id);
    }

    private void initAndSetAdapterForElementsListPanel() {
        RecyclerView recyclerElementsListView = (RecyclerView) activity.findViewById(R.id.elements_list_recycler_view_id);
        recyclerElementsListView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerElementsListView.setLayoutManager(layoutManager);

        elementsRecyclerViewAdapter = new ElementsListViewAdapter(activity);
        recyclerElementsListView.setAdapter(elementsRecyclerViewAdapter);

    }

    private void initShowAnimation() {
        showElementsListPanel = new TranslateAnimation(0, 0, recyclerView.getHeight(), 0);
        showElementsListPanel.setDuration(ANIMATIONS_DURATION*2);
        showElementsListPanel.setInterpolator(new FastAccelerateInterpolator());
        showElementsListPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                recyclerView.setVisibility(View.VISIBLE);
                anyAnimationWorking = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                anyAnimationWorking = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void initHideAnimation() {
        hideElementsListPanel = new TranslateAnimation(0, 0, 0, recyclerView.getHeight());
        hideElementsListPanel.setDuration(ANIMATIONS_DURATION*2);
        hideElementsListPanel.setInterpolator(new SlowAccelerateInterpolator());
        hideElementsListPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                anyAnimationWorking = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                recyclerView.setVisibility(View.INVISIBLE);
                anyAnimationWorking = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private class FastAccelerateInterpolator implements Interpolator {
        public float getInterpolation(float t) {
            return (float)(1-Math.pow((1-t),2*1.5f));
        }
    }

    private class SlowAccelerateInterpolator implements Interpolator {
        public float getInterpolation(float t) {
            return (float)(Math.pow(t, 1.3));
        }
    }

    void hideOrShowUFOElementsPanel() {
        if (!anyAnimationWorking)
            if (recyclerView.getVisibility() == View.INVISIBLE) {
                initShowAnimation();
                recyclerView.startAnimation(showElementsListPanel);
            }
            else {
                initHideAnimation();
                recyclerView.startAnimation(hideElementsListPanel);
            }
    }

    void notifyDataSetChanged() {
        elementsRecyclerViewAdapter.notifyDataSetChanged();
    }

    int getSelectedItemIndex() {
        return elementsRecyclerViewAdapter.getSelectedIndex();
    }

    void setSelectedItemIndex(int newIndex) {
        elementsRecyclerViewAdapter.setSelectedIndex(newIndex);
    }

    void removeCurrentUFOObjAndSetToNegativeOne(){
        elementsRecyclerViewAdapter.removeCurrentUFOObjAndSetToNegativeOne();
    }

    void addItem(int drawableId){
        elementsRecyclerViewAdapter.addItem(drawableId);
        elementsRecyclerViewAdapter.notifyDataSetChanged();
    }

    boolean isElementListShown(){
        return recyclerView.getVisibility() == View.VISIBLE;
    }
}
