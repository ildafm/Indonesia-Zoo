package com.ildafm.indonesiazoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvKebunBinatang;
    private ArrayList<ModelKebunBinatang> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvKebunBinatang = findViewById(R.id.rv_kebun_binatang);
        rvKebunBinatang.setHasFixedSize(true);

        data.addAll(DataKebunBinatang.ambilDataKebunBinatang());
        tampilDataCard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_right, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_card:
                tampilDataCard();
                break;
            case R.id.menu_grid:
                tampilDataGrid();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void tampilDataCard(){
        rvKebunBinatang.setLayoutManager(new LinearLayoutManager(this));
        AdapterCard colokanCard = new AdapterCard(data);
        rvKebunBinatang.setAdapter(colokanCard);

        colokanCard.setOnItemClickCallBack(new AdapterGrid.OnItemClickCallBack() {
            @Override
            public void onItemClicked(ModelKebunBinatang data) {
                Intent pindah = new Intent(MainActivity.this, DetailActivity.class);
                pindah.putExtra("xNama", data.getNama());
                pindah.putExtra("xDetail", data.getDetail());
                pindah.putExtra("xFoto", data.getFoto());
                pindah.putExtra("xWebsite", data.getWebsite());
                pindah.putExtra("xTelepon", data.getTelepon());
                startActivity(pindah);
            }
        });
    }

    private void tampilDataGrid(){
        rvKebunBinatang.setLayoutManager((new GridLayoutManager(this, 2)));
        AdapterGrid colokanGrid = new AdapterGrid(data);
        rvKebunBinatang.setAdapter(colokanGrid);

        colokanGrid.setOnItemClickCallBack(new AdapterGrid.OnItemClickCallBack() {
            @Override
            public void onItemClicked(ModelKebunBinatang data) {
                Toast.makeText(MainActivity.this, "Nama Kebun Binatang : " + data.getNama(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}