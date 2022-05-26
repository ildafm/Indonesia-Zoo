package com.ildafm.indonesiazoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView tvNama, tvDetail;
    private ImageView ivFoto;
    private String yNama, yDetail, yFoto, yWebsite, yTelepon;
    private Button btnLokasi, btnWebsite, btnTelepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();

        Intent terima = getIntent();
        yNama = terima.getStringExtra("xNama");
        yDetail = terima.getStringExtra("xDetail");
        yFoto = terima.getStringExtra("xFoto");
        yWebsite = terima.getStringExtra("xWebsite");
        yTelepon = terima.getStringExtra("xTelepon");

        setData();
        fungsiButton();
    }

    private void initView(){
        tvNama = findViewById(R.id.tv_nama);
        tvDetail = findViewById(R.id.tv_detail);

        ivFoto = findViewById(R.id.iv_foto);

        btnLokasi = findViewById(R.id.btn_lokasi);
        btnWebsite = findViewById(R.id.btn_website);
        btnTelepon = findViewById(R.id.btn_telepon);
    }

    private void setData(){
        tvNama.setText(yNama);
        tvDetail.setText(yDetail);

        Glide.with(DetailActivity.this)
                .load(yFoto)
                .into(ivFoto);

    }

    private void fungsiButton(){

        //button website
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriLink = Uri.parse(yWebsite);

                Intent bukaWebsite = new Intent(Intent.ACTION_VIEW, uriLink);
                startActivity(bukaWebsite);
            }
        });

        //button Telepon
        btnTelepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bukaTelepon = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",yTelepon, null));
                startActivity(bukaTelepon);
            }
        });

        //btnLokasi
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriLokasi = Uri.parse("geo:0,0?q="+yNama);

                Intent bukaLokasi = new Intent(Intent.ACTION_VIEW, uriLokasi);
                startActivity(bukaLokasi);
            }
        });
    }
}