package com.example.root.ngabensin.Model;

/**
 * Created by adit on 18/01/18.
 */

public class detilBensin {

    private int Harga;
    private int Tipe ;


    public detilBensin() {

    }

    public detilBensin(int harga, int tipe) {
        Harga = harga;
        Tipe = tipe;
    }

    public int getHarga() {
        return Harga;
    }

    public void setHarga(int harga) {
        Harga = harga;
    }

    public int getTipe() {
        return Tipe;
    }

    public void setTipe(int tipe) {
        Tipe = tipe;
    }

}
