package com.example.stackapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLihatData extends RecyclerView.Adapter<AdapterLihatData.ViewHolder> {
    //mendeklarasikan variabel
    private ArrayList<DataKegiatan> dataKegiatan;
    private Context context;
    private DatabaseReference databaseReference;

    public AdapterLihatData(ArrayList<DataKegiatan> daftarBrg, Context ctx){
        dataKegiatan = daftarBrg;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ViewHolder(View v){
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_DataKegiatan);

            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String key,judul,kegiatan,waktu,tanggal;
        key = dataKegiatan.get(position).getJudul();
        judul = dataKegiatan.get(position).getJudul();
        kegiatan = dataKegiatan.get(position).getDetailkegiatan();
        waktu = dataKegiatan.get(position).getTime();
        tanggal = dataKegiatan.get(position).getTanggal();

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mendeclarasikan popup menu
                PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
                popupMenu.inflate(R.menu.menuitem);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.DetailData:
                                //untuk mengambil data menggunakan bundle
                                Bundle bundle = new Bundle();
                                bundle.putString("kunci1",key);
                                bundle.putString("kunci2",judul);
                                bundle.putString("kunci3",kegiatan);
                                bundle.putString("kunci4",waktu);
                                bundle.putString("kunci5",tanggal);
                                //untuk masuk ke activity detailData
                                Intent intent = new Intent(view.getContext(),DetailData.class);
                                intent.putExtras(bundle);
                                view.getContext().startActivity(intent);
                                break;
                            case R.id.editData:
                                Intent inten = new Intent(view.getContext(),EditData.class);
                                view.getContext().startActivity(inten);
                                break;
                            case R.id.deleteData:
                                //membuat alert dialog agar sebelum di hapus di tanya terlebih dahulu apakah sudah yakin ingin
                                // menghapus itu
                                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                                alertDialog.setTitle("Yakin data " + judul + " akan dihapus?");
                                alertDialog.setMessage("Tekan 'Ya' untuk menghapus")
                                        .setCancelable(false)
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                deleteData(judul);
                                                Toast.makeText(view.getContext(), "Data " + judul + " berhasil dihapus", Toast.LENGTH_LONG).show();
                                                Intent intent1 = new Intent(view.getContext(), MainActivity.class);
                                                view.getContext().startActivity(intent1);
                                            }
                                        })
                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });
                                AlertDialog dialog = alertDialog.create();
                                dialog.show();
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                //return true;
            }
        });
        holder.tvTitle.setText(judul);


    }

    public void deleteData(String key) {
        if (databaseReference != null) {
            databaseReference.child("DataKegiatan").child(key).removeValue();
        }
    }

    @Override
    public int getItemCount() {
        return dataKegiatan.size();
    }
}
