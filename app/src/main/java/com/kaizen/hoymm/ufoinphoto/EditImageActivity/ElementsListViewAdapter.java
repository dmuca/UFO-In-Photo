package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaizen.hoymm.ufoinphoto.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Damian Muca (Kaizen) on 06.12.17.
 */

class ElementsListViewAdapter extends RecyclerView.Adapter <ElementsListViewAdapter.ViewHolder> {
    private int selectedIndex = 4;
    private List<Integer> imagesList =
            //new ArrayList<>();
            Arrays.asList(R.drawable.img1
                    , R.drawable.img2
                    , R.drawable.img3
                    , R.drawable.img4
                    , R.drawable.img5
                    , R.drawable.img6
                    , R.drawable.img7
                    , R.drawable.img8
                    , R.drawable.img9
                    , R.drawable.img1
                    , R.drawable.img2
            );
    private EditImageCommunication editImageCommunication;

    ElementsListViewAdapter(EditImageCommunication editImageCommunication) {
        this.editImageCommunication = editImageCommunication;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout constraintLayout;
        private ImageView imageOfUFO;
        private TextView idNumbText;
        ViewHolder(ConstraintLayout constraintLayout){
            super(constraintLayout);
            this.constraintLayout = constraintLayout;
            imageOfUFO = constraintLayout.findViewById(R.id.item_elements_list_iv_id);
            idNumbText = constraintLayout.findViewById(R.id.element_text_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_elements_by_images, parent, false);
        return new ElementsListViewAdapter.ViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.constraintLayout.setSelected(position == selectedIndex);
        holder.imageOfUFO.setImageResource(imagesList.get(position));
        holder.idNumbText.setText(String.valueOf(position));

        holder.constraintLayout.setOnClickListener(v -> {
            selectedIndex = position;
            this.notifyDataSetChanged();
            editImageCommunication.selectImageAndCloseThisWindow(position);
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    void removeItem(int index){
        imagesList.remove(index);
    }

    void addItem(int index){
        imagesList.add(index);
    }
}