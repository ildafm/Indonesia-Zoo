package com.ildafm.indonesiazoo;

import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.CardViewHolder> {

    private ArrayList<ModelKebunBinatang> dataKebunBinatang;

    public AdapterCard(ArrayList<ModelKebunBinatang> dataKebunBinatang){
        this.dataKebunBinatang = dataKebunBinatang;
    }

    public interface OnItemClickCallBack{
        void onItemClicked(ModelKebunBinatang data);
    }

    private AdapterGrid.OnItemClickCallBack callBack;
    public void setOnItemClickCallBack(AdapterGrid.OnItemClickCallBack callBack){
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ModelKebunBinatang kebunBinatang = dataKebunBinatang.get(position);

        holder.tvNama.setText(kebunBinatang.getNama());
        holder.tvDetail.setText(kebunBinatang.getDetail());

        Glide
                .with(holder.itemView.getContext())
                .load(kebunBinatang.getFoto())
                .into(holder.ivFoto);

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

    public class CardViewHolder extends RecyclerView.ViewHolder{

        ImageView ivFoto;
        TextView tvNama, tvDetail;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.iv_foto);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvDetail = itemView.findViewById(R.id.tv_detail);
        }
    }
}
