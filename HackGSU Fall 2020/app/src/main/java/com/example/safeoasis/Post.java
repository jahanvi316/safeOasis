package com.example.safeoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference ref = db.getReference("Post");

    private Button Post;
    private EditText message;
    private CheckBox lgbtq;
    private CheckBox poc;
    private CheckBox women;
    private CheckBox newParents;
    private CheckBox physicalHealth;
    private CheckBox mentalHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Post = (Button) findViewById(R.id.postButton);
        Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
    }



    private void post() {
        Log.e("post","POST WORKS");
        message = (EditText) findViewById(R.id.postMessage);
        final String mess = message.getText().toString();

        lgbtq = (CheckBox) findViewById(R.id.lgbtq);
        poc = (CheckBox) findViewById(R.id.poc);
        women = (CheckBox) findViewById(R.id.women);
        newParents = (CheckBox) findViewById(R.id.newParents);
        physicalHealth = (CheckBox) findViewById(R.id.physicalHealth);
        mentalHealth = (CheckBox) findViewById(R.id.mentalHealth);
        ref.child("All Posts").getRef().push().setValue(mess);
        if (lgbtq.isChecked()) {
            ref.child("LGBTQ").getRef().push().setValue(mess);
        }
        if (poc.isChecked()) {
            ref.child("POC").getRef().push().setValue(mess);
        }
        if (women.isChecked()) {
            ref.child("Women").getRef().push().setValue(mess);
        }
        if (newParents.isChecked()) {
            ref.child("New Parents").getRef().push().setValue(mess);
        }
        if (physicalHealth.isChecked()) {
            ref.child("Physical Health").getRef().push().setValue(mess);
        }
        if (mentalHealth.isChecked()) {
            ref.child("Mental Health").getRef().push().setValue(mess);
        }

    }


}