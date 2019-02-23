package com.asaproject.plezmoarandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoActivity extends AppCompatActivity {

    ImageView mPlace;

    TextView desctv;
DatabaseReference mDatabase;
String model_key;
private ModelKit mi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
desctv=findViewById(R.id.description);
        mPlace = findViewById(R.id.imageView);
        Bundle mBundle = getIntent().getExtras();
        mDatabase = FirebaseDatabase.getInstance().getReference();



    }


    @Override
    protected void onStart() {
        super.onStart();

        model_key = getIntent().getStringExtra("ModelId");
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange( DataSnapshot dataSnapshot) {
            mi = null;
            mi = dataSnapshot.child(model_key).getValue(ModelKit.class);
            if (mi != null) {
                String model_desc = mi.getInfo();
                String model_mainimgLink = mi.getLinkMainImg();
desctv.setText(model_desc);
                Glide.with(getApplicationContext()).load(model_mainimgLink).into(mPlace);

            }



        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }
}
