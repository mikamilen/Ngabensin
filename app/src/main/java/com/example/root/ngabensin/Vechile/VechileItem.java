package com.example.root.ngabensin.Vechile;

/**
 * Created by sep on 13/01/18.
 */

public class VechileItem {
    private long id;
    private String nmkendaraan;
    private String jnkendaraan;
    private byte[] image;

    public VechileItem() {
    }

    ;

    public VechileItem(long id, String nmkendaraan, String jnkendaraan, byte[] image) {
        this.id = id;
        this.nmkendaraan = nmkendaraan;
        this.jnkendaraan = jnkendaraan;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNmkendaraan() {
        return nmkendaraan;
    }

    public void setNmkendaraan(String nmkendaraan) {
        this.nmkendaraan = nmkendaraan;
    }

    public String getJnkendaraan() {
        return jnkendaraan;
    }

    public void setJnkendaraan(String jnkendaraan) {
        this.jnkendaraan = jnkendaraan;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}