package com.example.stackapplication;


import java.io.Serializable;

public class DataKegiatan implements Serializable {
    private String judul,detailkegiatan,time,tanggal;

    public DataKegiatan() {
    }

    @Override
    public String toString() {
        return  " " + judul + '\n' +
                " " + detailkegiatan + '\n' +
                " " +  time + '\n' +
                " " + tanggal;

    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String jl) {
        this.judul = jl;
    }

    public String getDetailkegiatan() {
        return detailkegiatan;
    }

    public void setDetailkegiatan(String dk) {
        this.detailkegiatan = dk;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String tm) {
        this.time = tm;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tg) {
        this.tanggal = tg;
    }


    public DataKegiatan(String jl, String dk , String tm , String tg) {
        this.judul = jl;
        this.detailkegiatan = dk;
        this.time = tm;
        this.tanggal = tg;
    }
}
