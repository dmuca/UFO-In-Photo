package com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActivity;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by Damian Muca (Kaizen) on 06.01.18.
 */

public class FooterTopTransformFragment extends Fragment{
    private EditImageActivity editImageActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_footer_transform, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            editImageActivity = (EditImageActivity) context;
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
        }
    }
}
