package com.sliit.interviewtask3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText email,password,confirm_password;
    Button signUp;
    TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)  findViewById(R.id.email);
        password=(EditText)  findViewById(R.id.password);
        confirm_password=(EditText)  findViewById(R.id.confirm_password);

        signUp=(Button)  findViewById(R.id.buttonSignUp);
        signIn=(TextView)  findViewById(R.id.buttonSignIn);



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
                }
            } else {
                //Email validation failed
                Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            }
        }
    }
}