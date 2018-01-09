package com.example.root.ngabensin.Model;

import java.io.Serializable;

/**
 * Created by root on 08/01/18.
 */

public class FueltripModel implements Serializable {
    public int image;
    public String name;

    public FueltripModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public FueltripModel() {
    }
}
