package com.example.android.campusseguro.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.android.campusseguro.R;

public class MenuActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ImageView imgFavorite = (ImageView) findViewById(R.id.imgMapa);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



    public void callEstaticas(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", 2);
        startActivity(intent);
    }

    public void callMap(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", 1);
        startActivity(intent);
    }

    public void callConfig(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", 3);
        startActivity(intent);
    }
}
