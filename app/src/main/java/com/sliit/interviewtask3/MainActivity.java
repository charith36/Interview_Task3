package com.sliit.interviewtask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText email,password,confirm_password;
    Button signUp;
    TextView signIn;
    Data data;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)  findViewById(R.id.email);
        password=(EditText)  findViewById(R.id.password);
        confirm_password=(EditText)  findViewById(R.id.confirm_password);

        signUp=(Button)  findViewById(R.id.buttonSignUp);
        signIn=(TextView)  findViewById(R.id.buttonSignIn);

        data = new Data();



        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                registerUser();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        // setup user registration
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPasswordText = confirm_password.getText().toString();
        //validate empty fields
        if(emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
            // display and error message
            Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
        } else {
            //validate email
            String emailPattern = "^(.+)@(.+)$";
            if(Pattern.compile(emailPattern).matcher(email.getText().toString()).matches()) {
                //validate password
                if(!passwordText.equals(confirmPasswordText)) {
                    //display an error message
                    Toast.makeText(getApplicationContext(),"Password and Confirm Password does not match!",Toast.LENGTH_SHORT).show();
                } else {
                    //enter to the database;
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Data");

                    //Add data to the Data object
                    data.setEmail(emailText);
                    data.setPassword(passwordText);

                    //Insert to the database
                    databaseReference.push().setValue(data);
                    Toast.makeText(getApplicationContext(),"Signup success",Toast.LENGTH_SHORT).show();
                }
            } else {
                //Email validation failed
                Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            }
        }
    }
}