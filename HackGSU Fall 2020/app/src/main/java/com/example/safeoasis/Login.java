package com.example.safeoasis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.util.Patterns;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button signin;
    private Button signUp;
    private EditText EditEmail, EditPassword;
    public FirebaseUser user;


    private FirebaseAuth mAuth;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = (Button) findViewById(R.id.signIn); //Why is the sign in button saved as sign up? we have two different buttons, one for sign in and one to lead to the sign up? which one is this for?
        signin.setOnClickListener(this);

        signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(this);

        EditEmail = (EditText) findViewById(R.id.email);
        EditPassword = (EditText) findViewById(R.id.password);

    }

    // we have to different onclicks so we can't do it like this, we got to name the methods
    //should thos not be log in?
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signIn: //ohhhh i see maybe this works? never done like this
                userLogin();
                break;

            case R.id.signUp:
                startActivity(new Intent(this, SignUp.class));

                break;
        }
    }

    private void userLogin() {
        String Email1 = EditEmail.getText().toString().trim();
        String Password1 = EditPassword.getText().toString().trim();

        if (Email1.isEmpty()) {
            EditEmail.setError("Username is required!");
            EditPassword.requestFocus();
            return;

        }

        if (Password1.length() < 6) {
            EditPassword.setError("Password length should be 6 characters!");
            EditPassword.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(Email1).matches()) {
            EditEmail.setError("Provide Email!");
            EditPassword.requestFocus();
            return;

        }


        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(Email1, Password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //user = mAuth.getCurrentUser();

                   // Log.e("user id: ", user.getUid());
                   // Log.e("user token: ", "SUCCESSFUL: " +String.valueOf(user.getIdToken(true)));
                    Intent FeedIntent = new Intent(Login.this, Feed.class);
                    startActivity(FeedIntent);
                    Toast.makeText(Login.this, "Great!", Toast.LENGTH_LONG).show();

                    //         db.getReference("Users").child(username1).child(password1);
                    //       db.getReference("Users").child(username1).child(email1);

                } else {
                  //  Log.e("user token: ", "FAILED: " + String.valueOf(user.getIdToken(true)));

                    Toast.makeText(Login.this, "Failed. Check Credentials!", Toast.LENGTH_LONG).show();

                }


            }
        });

    }

}