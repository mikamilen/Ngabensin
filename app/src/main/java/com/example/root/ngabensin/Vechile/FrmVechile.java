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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.root.ngabensin.Adapter.ListViewPagerAdapter;
import com.example.root.ngabensin.ChooseFuel;
import com.example.root.ngabensin.ChooseVehicle;
import com.example.root.ngabensin.Model.FueltripModel;
import com.example.root.ngabensin.Model.Kendaraan;
import com.example.root.ngabensin.Model.User;
import com.example.root.ngabensin.R;
import com.example.root.ngabensin.SQLiteHelper;
import com.example.root.ngabensin.SQLiteOperation;
import com.example.root.ngabensin.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.root.ngabensin.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FrmVechile extends AppCompatActivity {

    EditText edtNmKendaraan, edtJnKendaraan;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    private User user;
    private FirebaseDatabase database;
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

        final ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new ListViewPagerAdapter(getSupportFragmentManager(), getImage()));

        init();


        final Bundle b = getIntent().getExtras();

        if (b != null) {
            if (b.getString("action").equals("Edit")) {
                dataOperations.UpdateVechile(vechileItem);
            }
        }
        final VechileItem vechileItem = new VechileItem();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                vechileItem.setNmkendaraan(edtNmKendaraan.getText().toString());
//                vechileItem.setJnkendaraan(edtJnKendaraan.getText().toString());
////                vechileItem.setImage(imageViewToByte(imageView));
////                String Tugas = NmTugas.getText().toString();
////                String pelajaran = Pelajaran.getText().toString();
////                String Guru = NmGuru.getText().toString();
////                String deskripsi = Deskripsi.getText().toString();
////                String kumpul = JenisPengumpulan.getSelectedItem().toString();
////                String tanggal = date.getText().toString();
////                String email = Email.getText().toString();
////                float rating = Rating.getRating();
////                String[] data = {
////                        Tugas,
////                        pelajaran,
////                        Guru,
////                        deskripsi,
////                        kumpul,
////                        tanggal,
////                        email,
////                        String.valueOf(rating)
////
////                };
//
//                dataOperations.addVechile(vechileItem);
//                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(FrmVechile.this, Vechile.class);
//                startActivity(i);
                int position = pager.getCurrentItem();
                Toast.makeText(FrmVechile.this, "" + position + user.getName() , Toast.LENGTH_SHORT).show();

                Kendaraan kendaraan = new Kendaraan(edtNmKendaraan.getText().toString(),edtJnKendaraan.getText().toString());

            DatabaseReference mRef = database.getReference("user").child(FirebaseAuth.getInstance()
                    .getCurrentUser().getUid());
                mRef.child("Kendaran").setValue(kendaraan);


            }
        });
    }

//        btnChoose.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                ActivityCompat.requestPermissions(
//                        FrmVechile.this,
//                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_CODE_GALLERY
//                );
//            }
//        });

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
//    }

//    private byte[] imageViewToByte(ImageView image) {
//        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        return byteArray;
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        if (requestCode == REQUEST_CODE_GALLERY){
//            if (grantResults.length >0  && grantResults[0]== PackageManager.PERMISSION_GRANTED){
//                Intent i = new Intent(Intent.ACTION_PICK);
//                i.setType("image/*");
//                startActivityForResult(i, REQUEST_CODE_GALLERY);
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "You don't have permission to acces file", Toast.LENGTH_SHORT).show();
//            }
//            return;
//        }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
//            Uri uri = data.getData();
//
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                imageView.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void init(){
        edtNmKendaraan = (EditText) findViewById(R.id.NmKendaraan);
        edtJnKendaraan = (EditText) findViewById(R.id.JnKendaraan);
//        btnChoose = (Button) findViewById(R.id.btnChose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
//        imageView = (ImageView) findViewById(R.id.icon);

        database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("user");
//            final String nmkendaraan = edtNmKendaraan.getText().toString();
//            final String jnkendaraan = edtJnKendaraan.getText().toString();

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            user = dataSnapshot.getValue(User.class);
//                User user = new User(edtNmKendaraan.getText().toString(),edtJnKendaraan.getText().toString(),User.class.getName());
//                            table_user.child("user").child(user.getName()).setValue(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }








    private List<FueltripModel> getImage() {
        List<FueltripModel> models = new ArrayList<>();
        models.add(new FueltripModel(R.drawable.motornew, "Hello"));
        models.add(new FueltripModel(R.drawable.motornew, "Hello1"));
        models.add(new FueltripModel(R.drawable.motornew, "Hello2"));
        return models;
    }
}
