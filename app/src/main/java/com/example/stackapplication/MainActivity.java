 package com.example.stackapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //declarasikan variabel
    ImageView iconimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengambil id nya
        iconimg = (ImageView) findViewById(R.id.imageIcon);

        //on click pada image icon untuk pergi ke home activity
        iconimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(HomeActivity.getActIntent(MainActivity.this));
            }
        });

    }
}