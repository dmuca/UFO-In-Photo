package com.kaizen.hoymm.ufoinphoto.EditImageActivity.ChooseUFOActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaizen.hoymm.ufoinphoto.R;

/**
 * Created by hoymm on 28.11.17.
 */

class ChooseUFOFooterAdapter extends RecyclerView.Adapter<ChooseUFOFooterAdapter.ViewHolder> {
    private static final int miniShipIcons[] = {
            R.drawable.img1mini,
            R.drawable.img2mini,
            R.drawable.img3mini,
            R.drawable.img4mini,
            R.drawable.img5mini,
            R.drawable.img6mini,
            R.drawable.img7mini,
            R.drawable.img8mini,
            R.drawable.img9mini,
            R.drawable.img10mini,
            R.drawable.img11mini,
            R.drawable.img12mini,
            R.drawable.img13mini,
            R.drawable.img14mini,
            R.drawable.img15mini
    };

    private static final int normalShipIcons[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15
    };

    private ChangeMainImage changeMainImage;

    ChooseUFOFooterAdapter(ChangeMainImage changeMainImage) {
        this.changeMainImage = changeMainImage;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageOfUFO;
        ViewHolder(ImageView view){
            super(view);
            imageOfUFO = view;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view_ufo_to_choose, parent, false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageOfUFO.setImageResource(miniShipIcons[position]);
        holder.imageOfUFO.setOnClickListener(v -> changeMainImage.changeMainImage(normalShipIcons[position]));
    }

    @Override
    public int getItemCount() {
        return miniShipIcons.length;
    }
}
