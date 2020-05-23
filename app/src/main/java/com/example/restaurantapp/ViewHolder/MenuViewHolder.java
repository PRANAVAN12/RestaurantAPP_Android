package com.example.restaurantapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.Interface.ItemClickListner;
import com.example.restaurantapp.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtMenuName;
    public ImageView imageView;
    private ItemClickListner itemClickListner;
    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMenuName=(TextView) itemView.findViewById(R.id.menu_name);
        imageView=(ImageView)itemView.findViewById(R.id.menu_image);

       itemView.setOnClickListener(this);
    }

    public ItemClickListner getItemClickListner() {
        return itemClickListner;
    }

    @Override
    public void onClick(View view) {
        itemClickListner.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
    }
}
