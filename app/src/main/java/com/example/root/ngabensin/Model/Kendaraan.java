package com.example.root.ngabensin.Model;

/**
 * Created by adit on 23/01/18.
 */

public class Kendaraan {
    public String fotoKendaraan;
    public String namaKendaraan;
    public String jenisKendaraan;

    public Kendaraan() {
    }

    public Kendaraan(String namaKendaraan, String jenisKendaraan,String fotoKendaraan) {
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.fotoKendaraan = fotoKendaraan;

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

    public String getFotoKendaraan() {
        return fotoKendaraan;
    }

    public void setFotoKendaraan(String fotoKendaraan) {
        this.fotoKendaraan = fotoKendaraan;
    }

}
