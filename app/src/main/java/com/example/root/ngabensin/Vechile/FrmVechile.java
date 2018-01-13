package com.example.root.ngabensin.Vechile;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.root.ngabensin.R;
import com.example.root.ngabensin.SQLiteHelper;
import com.example.root.ngabensin.SQLiteOperation;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FrmVechile extends AppCompatActivity {

    EditText edtNmKendaraan, edtJnKendaraan;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    VechileItem vechileItem;
    DatePickerDialog datePickerDialog;

    SQLiteOperation dataOperations;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_vechile);

        dataOperations = new SQLiteOperation(this);
        dataOperations.open();


        init();

        final Bundle b = getIntent().getExtras();

        if (b!=null){
            if (b.getString("action").equals("Edit")){
                dataOperations.UpdateVechile(vechileItem);
            }
        }
        final VechileItem vechileItem = new VechileItem();


        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                vechileItem.setNmkendaraan(edtNmKendaraan.getText().toString());
                vechileItem.setJnkendaraan(edtJnKendaraan.getText().toString());
                vechileItem.setImage(imageViewToByte(imageView));
//                String Tugas = NmTugas.getText().toString();
//                String pelajaran = Pelajaran.getText().toString();
//                String Guru = NmGuru.getText().toString();
//                String deskripsi = Deskripsi.getText().toString();
//                String kumpul = JenisPengumpulan.getSelectedItem().toString();
//                String tanggal = date.getText().toString();
//                String email = Email.getText().toString();
//                float rating = Rating.getRating();
//                String[] data = {
//                        Tugas,
//                        pelajaran,
//                        Guru,
//                        deskripsi,
//                        kumpul,
//                        tanggal,
//                        email,
//                        String.valueOf(rating)
//
//                };

                dataOperations.addVechile(vechileItem);
                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(FrmVechile.this, Vechile.class);
                startActivity(i);
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        FrmVechile.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    sqLiteHelper.insertData(
//                            edtNmKendaraan.getText().toString().trim(),
//                            edtJnKendaraan.getText().toString().trim(),
//                            imageViewToByte(imageView)
//                    );
//                    Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
//                    edtNmKendaraan.setText("");
//                    edtJnKendaraan.setText("");
//                    imageView.setImageResource(R.mipmap.ic_launcher_round);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });

//        btnList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(frmVechile.this, Vechile.class);
//                startActivity(i);
//            }
//        });
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length >0  && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to acces file", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtNmKendaraan = (EditText) findViewById(R.id.NmKendaraan);
        edtJnKendaraan = (EditText) findViewById(R.id.JnKendaraan);
        btnChoose = (Button) findViewById(R.id.btnChose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        imageView = (ImageView) findViewById(R.id.icon);
    }
}
