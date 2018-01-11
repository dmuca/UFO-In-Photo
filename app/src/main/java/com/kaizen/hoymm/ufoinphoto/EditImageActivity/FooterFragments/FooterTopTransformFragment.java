package com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActions;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by Damian Muca (Kaizen) on 06.01.18.
 */

public class FooterTopTransformFragment extends Fragment{
    private EditImageActions editImageActions;
    private ImageButton moveLeftButton, moveRightButton, moveUpButton, moveDownButtom;
    private ImageButton mirrorHorButton, mirrorVerButton, rotateLeftButton, rotateRightButton, zoomInButton, zoomOutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_footer_transform, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initButtons(view);
        setButtonsBehavior();
    }

    private void initButtons(View view) {
        moveLeftButton = view.findViewById(R.id.move_left_id);
        moveRightButton = view.findViewById(R.id.move_right_id);
        moveUpButton = view.findViewById(R.id.move_up_id);
        moveDownButtom = view.findViewById(R.id.move_down_id);

        mirrorHorButton = view.findViewById(R.id.mirrorhor_id);
        mirrorVerButton = view.findViewById(R.id.mirrorver_id);
        rotateLeftButton = view.findViewById(R.id.rotate_left_id);
        rotateRightButton = view.findViewById(R.id.rotate_right_id);
        zoomInButton = view.findViewById(R.id.zoom_in_id);
        zoomOutButton = view.findViewById(R.id.zoom_out_id);
    }

    private void setButtonsBehavior() {
        moveLeftButton.setOnClickListener(getMoveLeftBehavior());
        moveRightButton.setOnClickListener(getMoveRightBehavior());
        moveUpButton.setOnClickListener(getMoveUpBehavior());
        moveDownButtom.setOnClickListener(getMoveDownBehavior());

        mirrorHorButton.setOnClickListener(getMirrorHorBehavior());
        mirrorVerButton.setOnClickListener(getMirrorVerBehavior());
        rotateLeftButton.setOnClickListener(getRotateLeftBehavior());
        rotateRightButton.setOnClickListener(getRotateRightBehavior());
        zoomInButton.setOnClickListener(getZoomInBehavior());
        zoomOutButton.setOnClickListener(getZoomOutBehavior());
    }

    private View.OnClickListener getMoveLeftBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Move left clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getMoveRightBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Move right clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getMoveUpBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Move up clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getMoveDownBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Move down clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getMirrorHorBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Mirror hor clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getMirrorVerBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Mirror Ver clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getRotateLeftBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Rotate left clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getRotateRightBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Rotate right clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getZoomInBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Zoom in clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getZoomOutBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Zoom out clicked", Toast.LENGTH_SHORT).show();
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            editImageActions = (EditImageActions) context;
        } catch (ClassCastException classCastException) {
            classCastException.printStackTrace();
        }
    }
}
