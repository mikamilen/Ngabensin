package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.root.ngabensin.fuelgauge.FuelGaugeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.example.root.ngabensin.nearbyplaces.MapsActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    @InjectView(R.id.seekBar) SeekBar mSeekbar;
    @InjectView(R.id.fuelGaugeView) FuelGaugeView mFuelGaugeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mSeekbar.setProgress(Math.round(mFuelGaugeView.getFuelLevel() * 100));
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    float newLevel = (float) progress / 100f;
                    mFuelGaugeView.setFuelLevel(newLevel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button = (Button)findViewById(R.id.btnNext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }


}
