package com.example.safeoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button chatButton = (Button) findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Chat.class));
            }
        });
    }


//    @Override
//    protected void onStart(Bundle savedInstanceState) {
//        super.onStart(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        TextView username = (TextView) findViewById(R.id.username);
//        TextView userBio = (TextView) findViewById(R.id.userBio);
//
//
//    }


}