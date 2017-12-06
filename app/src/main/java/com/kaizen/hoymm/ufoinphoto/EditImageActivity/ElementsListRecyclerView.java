package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.kaizen.hoymm.ufoinphoto.R;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity.ANIMATIONS_DURATION;

/**
 * Created by Damian Muca (Kaizen) on 06.12.17.
 */

class ElementsListRecyclerView {

    private LinearLayout viewBoxOfRecyclerView;
    private RecyclerView recyclerView;

    private TranslateAnimation showElementsListPanel;
    private TranslateAnimation hideElementsListPanel;

    private int indexOfSelectedUFO = -1;
    private ElementsListViewAdapter elementsRecyclerViewAdapter;
    private EditImageActivity activity;

    ElementsListRecyclerView(EditImageActivity appCompatActivity) {
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
        showElementsListPanel = new TranslateAnimation(
                recyclerView.getX(), recyclerView.getX(), recyclerView.getY() + viewBoxOfRecyclerView.getHeight(), recyclerView.getY());
        showElementsListPanel.setDuration(ANIMATIONS_DURATION*2);
        showElementsListPanel.setInterpolator(new AccelerateDecelerateInterpolator());
        showElementsListPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void initHideAnimation() {
        hideElementsListPanel = new TranslateAnimation(
                recyclerView.getX(), recyclerView.getX(), recyclerView.getY(), recyclerView.getY() + recyclerView.getHeight());
        hideElementsListPanel.setDuration(ANIMATIONS_DURATION*2);
        hideElementsListPanel.setInterpolator(new AccelerateDecelerateInterpolator());
        hideElementsListPanel.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                recyclerView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    void hideOrShowUFOElementsPanel() {
        if (!recyclerView.isAnimating())
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
        return indexOfSelectedUFO;
    }

    void setSelectedItemIndex(int newIndex) {
        indexOfSelectedUFO = newIndex;
    }
}
