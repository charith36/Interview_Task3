package com.sliit.interviewtask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        } else {
            //search database
        }
    }



}