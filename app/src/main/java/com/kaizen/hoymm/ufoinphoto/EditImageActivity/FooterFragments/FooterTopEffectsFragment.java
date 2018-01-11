package com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterFragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImageActions;
import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by Damian Muca (Kaizen) on 06.01.18.
 */

public class FooterTopEffectsFragment extends Fragment {
    private EditImageActions editImageActions;
    private SeekBar transparentSeekBar;
    private ImageButton transparentButton, hueButton, saturationButton, brightnessButton, contrastButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_footer_effects, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            editImageActions = (EditImageActions) context;
        }
        catch (ClassCastException classCastException){
            classCastException.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        setComponentsBehavior();
    }

    private void initComponents(View view) {
        transparentSeekBar = view.findViewById(R.id.seekbar_transparent_id);

        transparentButton = view.findViewById(R.id.transparent_id);
        hueButton = view.findViewById(R.id.hue_id);
        saturationButton = view.findViewById(R.id.saturation_id);
        brightnessButton = view.findViewById(R.id.brightness_id);
        contrastButton = view.findViewById(R.id.contrast_id);
    }

    private void setComponentsBehavior() {
        setSeekBarBehavior();
        setButtonsBehavior();
    }

    private void setSeekBarBehavior() {
        transparentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getActivity(), "SeekBar setted on " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setButtonsBehavior() {
        transparentButton.setOnClickListener(getTransparentBehavior());
        hueButton.setOnClickListener(getHueBehavior());
        saturationButton.setOnClickListener(getSaturationBehavior());
        brightnessButton.setOnClickListener(getBrightnessBehavior());
        contrastButton.setOnClickListener(getContrastBehavior());
    }

    private View.OnClickListener getTransparentBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Trasparent clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getHueBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Hue clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getSaturationBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Saturation clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getBrightnessBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Brightness clicked", Toast.LENGTH_SHORT).show();
        };
    }

    private View.OnClickListener getContrastBehavior() {
        return v -> {
            Toast.makeText(getActivity(), "Contrast clicked", Toast.LENGTH_SHORT).show();
        };
    }
}


