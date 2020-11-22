package com.example.safeoasis;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class Feed extends AppCompatActivity {
    Button post;
    Button userProfile;
    List<String> filters;
    FirebaseDatabase database ;

    EditText Post;

    Button PostButton;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<FeedHelper> adapter;


    //start showing
    ListView FeedInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseReference databasePost = database.getInstance().getReference().child("Post").child("All Posts");
        FeedInformation = (ListView) findViewById(R.id.listView);
        FeedInformation.setAdapter(adapter);

        databasePost.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                arrayList.add(value);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Post.class));
            }
        });

        userProfile = (Button) findViewById(R.id.profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });





        /*
        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("Post").child("All Posts");

//Get all the values
                String Post1 = post.getText().toString();
                FeedHelper feed = new FeedHelper(Post1);

                //reference3.push().setValue(rem);

            }
        });

         */
    }




//    public List<String> filtersApplied(){
//            CheckBox lgbtq = (CheckBox) findViewById(R.id.lgbtq);
//            lgbtq.setOnCheckedChangeListener(new OnCheckedChangedListener() {
//
//            });
//    }
}