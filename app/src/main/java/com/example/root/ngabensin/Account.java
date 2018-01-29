package com.example.root.ngabensin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.ngabensin.Model.User;
import com.example.root.ngabensin.Vechile.Featured;
import com.example.root.ngabensin.Vechile.Vechile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link VehicleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Account extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    TextView Username;

    Button btnLogout ;
    public static Account newInstance(int page, String title) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Account fragment = new Account();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(),LoginActivity.class);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference dataUser = database.getReference("user").child(FirebaseAuth.getInstance()
                        .getCurrentUser().getUid());



                dataUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final TextView Username = (TextView) view.findViewById(R.id.txt_username);
                        final TextView Email = (TextView)view.findViewById(R.id.edt_email);
                        final TextView Password = (TextView)view.findViewById(R.id.edt_password);

                        User user = dataSnapshot.getValue(User.class);
                        String username = user.getName();
                        String email = user.getEmail();
                        String password = user.getPassword();


                        Username.setText(String.valueOf(username));
                        Email.setText(String.valueOf(email));
                        Password.setText(String.valueOf(password));



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());


                    }


                });
                startActivity(intent);
                getActivity().finish();


            }

        });

    }



    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //        TextView tvTitle = (TextView) view.findViewById(R.id.vehiclefragment);
        return inflater.inflate(R.layout.fragment_account, container, false);



    }
}