package com.example.safeoasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity{
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    private TextView TextUser, TextPassword;
    private EditText EditUser, EditPassword, EditEmail;
    private Button SignUp, SignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditUser = (EditText) findViewById(R.id.emaill);
        EditPassword = (EditText) findViewById(R.id.password);
        EditEmail = (EditText) findViewById(R.id.email);


        mAuth = FirebaseAuth.getInstance();
        SignUp = (Button) findViewById(R.id.signIn);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        SignIn = (Button) findViewById(R.id.signUp);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });



    }


    private void registerUser() {
        final String username1 = EditUser.getText().toString().trim();
        final String password1 = EditPassword.getText().toString().trim();
        final String email1 = EditEmail.getText().toString().trim();


        if (password1.isEmpty()) {
            EditPassword.setError("Password is required!");
            EditPassword.requestFocus();
            return;

        }

        if (password1.length() < 6) {
            EditPassword.setError("Password length should be 6 characters!");
            EditPassword.requestFocus();
            return;
        }

        if (email1.isEmpty()) {
            EditEmail.setError("Email is required!");
            EditPassword.requestFocus();
            return;

        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            EditEmail.setError("Provide Email!");
            EditPassword.requestFocus();
            return;

        }


        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(username1, email1);

           //         db.getReference("Users").child(username1).child(password1);
             //       db.getReference("Users").child(username1).child(email1);
                    db.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(com.example.safeoasis.SignUp.this, "Registration Successful!", Toast.LENGTH_LONG).show();
                            } else { //redirect to login
                                Toast.makeText(com.example.safeoasis.SignUp.this, "Registration Failed! 1", Toast.LENGTH_LONG).show();

                            }
                        }

                    });
                } else {
                    Toast.makeText(com.example.safeoasis.SignUp.this, "Registration Failed! 2", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

}