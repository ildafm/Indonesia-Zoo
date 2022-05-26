package com.ildafm.indonesiazoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class SplashActivity extends AppCompatActivity {

    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Menghilangkan notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        memuatGambarGif();
        
        //handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));//pindah activity
                finish();//menghilangkan activity
            }
        }, 3000);//3 detik
    }

    public void memuatGambarGif(){
        gambar = (ImageView)findViewById(R.id.iv_logo);

        Glide.with(SplashActivity.this)
                // LOAD URL DARI LOKAL DRAWABLE
                .load(R.drawable.indonesia_zoo)

                //PENGATURAN CACHE
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(gambar);
    }
}