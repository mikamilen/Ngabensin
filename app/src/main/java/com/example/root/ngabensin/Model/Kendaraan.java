package com.example.root.ngabensin.Model;

/**
 * Created by adit on 23/01/18.
 */

public class Kendaraan {
    public String FotoKendaraan;
    public String NamaKendaraan;
    public String JenisKendaraan;

    public Kendaraan() {
    }

    public Kendaraan(String namaKendaraan, String jenisKendaraan,String fotoKendaraan) {
        NamaKendaraan = namaKendaraan;
        JenisKendaraan = jenisKendaraan;
       FotoKendaraan = fotoKendaraan;

    }

    public String getNamaKendaraan() {
        return NamaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        NamaKendaraan = namaKendaraan;
    }

    public String getJenisKendaraan() {
        return JenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        JenisKendaraan = jenisKendaraan;
    }

    public String getFotoKendaraan() {
        return FotoKendaraan;
    }

    public void setFotoKendaraan(String fotoKendaraan) {
        FotoKendaraan = fotoKendaraan;
    }

}
