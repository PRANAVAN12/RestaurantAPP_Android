package com.example.restaurantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantapp.Model.User;
import com.example.restaurantapp.common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnSignIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

       edtPassword = findViewById(R.id.edtPassword);
        edtPhone =   findViewById(R.id.edtPhone);
        btnSignIn= (Button)findViewById(R.id.btnSignIn);
        //init database
       final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user =database.getReference("user");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mdialog = new ProgressDialog(SignIn.this);
                mdialog.setMessage("please waiting........!!!");
                mdialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      //check if user not exists
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            //Get user informations
                            mdialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Intent homeintent =new Intent(SignIn.this,Home.class);
                                Common.currentUser=user;
                                startActivity(homeintent);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "SignIn Failure !!!", Toast.LENGTH_SHORT).show();

                            }
                        }else {
                            mdialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exists...!!!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });






    }
}
