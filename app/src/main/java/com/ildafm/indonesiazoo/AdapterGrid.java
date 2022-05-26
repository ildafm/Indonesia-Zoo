package com.ildafm.indonesiazoo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.GridViewHolder> {

    private ArrayList<ModelKebunBinatang> dataKebunBinatang;

    public AdapterGrid(ArrayList<ModelKebunBinatang> dataKebunBinatang){
        this.dataKebunBinatang = dataKebunBinatang;
    }

    public interface OnItemClickCallBack{
        void onItemClicked(ModelKebunBinatang data);
    }

    private OnItemClickCallBack callBack;
    public void setOnItemClickCallBack(OnItemClickCallBack callBack){
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        ModelKebunBinatang kebunBinatang = dataKebunBinatang.get(position);

        Glide
                .with(holder.itemView.getContext())
                .load(kebunBinatang.getFoto())
                .into(holder.ivGridKebunBinatang);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onItemClicked(dataKebunBinatang.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataKebunBinatang.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{

        ImageView ivGridKebunBinatang;

        public GridViewHolder(@NonNull View itemView){
            super(itemView);
            ivGridKebunBinatang = itemView.findViewById(R.id.iv_grid);
        }

    }
}
