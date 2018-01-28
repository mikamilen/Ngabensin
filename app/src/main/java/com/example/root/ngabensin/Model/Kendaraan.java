package com.example.root.ngabensin.Model;

/**
 * Created by adit on 23/01/18.
 */

public class Kendaraan {
    public String namaKendaraan;
    public String jenisKendaraan;

    public Kendaraan() {
    }

    public Kendaraan(String namaKendaraan, String jenisKendaraan) {
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }
}
