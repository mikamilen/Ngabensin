package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
Button btnMyFuel , btnFuelCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnMyFuel = (Button)findViewById(R.id.btnMyFuel);
        btnMyFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this,ChooseVehicle.class);
                startActivity(intent);
            }
        });

//        btnFuelCost = (Button)findViewById(R.id.btnFuelCost);
//        btnFuelCost.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(MainMenu.this,ChooseVehicle.class);
////                startActivity(intent);
////            }
//        });
    }
}
