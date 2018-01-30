package com.example.root.ngabensin.Model;

import java.io.Serializable;

/**
 * Created by adit on 23/01/18.
 */

public class Kendaraan implements Serializable{
    private String FotoKendaraan;
    private String NamaKendaraan;
    private String JenisKendaraan;

    public Kendaraan() {
    }

    public Kendaraan(String namaKendaraan, String jenisKendaraan,String fotoKendaraan) {
        NamaKendaraan = namaKendaraan;
        JenisKendaraan = jenisKendaraan;
       FotoKendaraan = fotoKendaraan;

    }

    public String getFotoKendaraan() {
        return FotoKendaraan;
    }

    public String getNamaKendaraan() {
        return NamaKendaraan;
    }

    public String getJenisKendaraan() {
        return JenisKendaraan;
    }
}
