package com.example.stackapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {
    //mendeklarasikan variabel
    TextView txtjudul,txtdetailKegiatan,txtwaktu,txttanggal;
    String tj,tdk,tw,tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        //menyambungkan variabel  kesetiap id nya
        txtjudul = (TextView) findViewById(R.id.textJudul);
        txtdetailKegiatan = (TextView) findViewById(R.id.textDetailKegiatan);
        txtwaktu = (TextView) findViewById(R.id.textWaktu);
        txttanggal = (TextView) findViewById(R.id.textTanggal);

        //mengambil data dari adapter menggunakan bundle
        Bundle bundle = getIntent().getExtras();
        tj = bundle.getString("kunci1");
        tdk = bundle.getString("kunci3");
        tw = bundle.getString("kunci4");
        tt = bundle.getString("kunci5");

        //menampilkan data ke avtivity detailData
        txtjudul.setText(tj);
        txtdetailKegiatan.setText(tdk);
        txtwaktu.setText(tw);
        txttanggal.setText(tt);

    }
}