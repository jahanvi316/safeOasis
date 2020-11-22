package com.example.safeoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button signin;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = (Button) findViewById(R.id.signUp); //Why is the sign in button saved as sign up? we have two different buttons, one for sign in and one to lead to the sign up? which one is this for?
        signin.setOnClickListener(this);

        signUp = (Button) findViewById(R.id.signIn);
        signUp.setOnClickListener(this);
    }

   // we have to different onclicks so we can't do it like this, we got to name the methods
    //should thos not be log in?
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signIn: //ohhhh i see maybe this works? never done like this
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.signUp:
                break;
        }
    }

}