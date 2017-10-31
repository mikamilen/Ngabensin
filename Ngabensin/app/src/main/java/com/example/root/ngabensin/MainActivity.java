package com.example.root.ngabensin;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.root.ngabensin.databinding.ActivityMainBinding;
import com.xw.repo.BubbleSeekBar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.txtView.setText("");
//        BubbleSeekBar bubbleSeekBar = (BubbleSeekBar)findViewById(R.id.seekBar);

//        SpeedView speedometer = (SpeedView) findViewById(R.id.speedView);
//
//// move to 50 Km/s
//        speedometer.speedTo(50);
//
//        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
//            @Override
//            public void onProgressChanged(int progress, float progressFloat) {
//                textView.setText(String.format("On Change %d", progress));
//
//            }
//
//            @Override
//            public void getProgressOnActionUp(int progress, float progressFloat) {
//
//            }
//
//            @Override
//            public void getProgressOnFinally(int progress, float progressFloat) {
//
//            }
//        });
    }
}
