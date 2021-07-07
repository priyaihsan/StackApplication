package com.example.stackapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahData extends AppCompatActivity {
    //mendeklarasikan variabel
    private DatabaseReference database;
    EditText edJudul,edketerangan,edWaktu,edTanggal;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //menyambungkan variabel  kesetiap id nya
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);
        edJudul = (EditText) findViewById(R.id.editJudul);
        edketerangan = (EditText) findViewById(R.id.editDetailKegiatan);
        edWaktu = (EditText) findViewById(R.id.editTime);
        edTanggal = (EditText) findViewById(R.id.editDate);
        btnSave = (Button) findViewById(R.id.btnTambah);

        database = FirebaseDatabase.getInstance().getReference();

        //membuat fungsi btn save agar bisa menyimpan data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edJudul.getText().toString().isEmpty())&&!(edketerangan.getText().toString().isEmpty())&&!(edWaktu.getText().toString().isEmpty())&&!(edTanggal.getText().toString().isEmpty())){
                    submitData(new DataKegiatan(edJudul.getText().toString(),
                            edketerangan.getText().toString(),
                            edWaktu.getText().toString(),
                            edTanggal.getText().toString()));
                }else{
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edJudul.getWindowToken(),0);
                }
            }
        });
    }
    //method untuk memasukkan data
    public void submitData(DataKegiatan dk) {
        database.child("DataKegiatan").push().setValue(dk).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                edJudul.setText("");
                edketerangan.setText("");
                edWaktu.setText("");
                edTanggal.setText("");
                Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan",Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity act1) {
        return new Intent(act1, TambahData.class);
    }


}