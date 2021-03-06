package com.sliit.interviewtask3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText email,password;
    Button signIn;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_acitivity);

        email=(EditText)  findViewById(R.id.email);
        password=(EditText)  findViewById(R.id.password);

        signIn=(Button)  findViewById(R.id.buttonSignIn);
        signUp=(TextView)  findViewById(R.id.buttonSignUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        //setup login
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        //Empty field validation
        if(emailText.isEmpty() || passwordText.isEmpty()) {
            //display an error message
            Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
        } else {
            //search database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Data");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChildren()) {
                        Data data = snapshot.getValue(Data.class);
                        if (data != null && data.getEmail().equals(emailText)) {
                            if (data.getPassword().equals(passwordText)) {
                                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                            }
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"Error loading data!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}