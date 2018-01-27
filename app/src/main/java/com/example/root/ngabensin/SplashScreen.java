package com.example.root.ngabensin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
final FirebaseAuth firebaseUser = FirebaseAuth.getInstance();
        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firebaseUser.getCurrentUser() == null) {
                Intent Start = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(Start);
            }
            else
                {
                    Intent Start = new Intent(SplashScreen.this, MainMenu.class);
                    startActivity(Start);
                    finish();
                }

            }
        });

    }
}
