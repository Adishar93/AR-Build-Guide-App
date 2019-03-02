package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ImageView mPlace;
RecyclerView mRecyclerView;
    TextView desctv,desc_title_tv;
    TextView project_titltle_tv;
DatabaseReference mDatabase;
String model_key;
String  model_title;
    RecentProjectAdapter adapter;
private ModelKit mi;
    ArrayList<ModelKit> mPlaceList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
desctv=findViewById(R.id.description);
project_titltle_tv= findViewById(R.id.project_title);
mPlace = findViewById(R.id.imageView);
desc_title_tv= findViewById(R.id.description_title);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(),"font/roboto_regular.ttf");
        desctv.setTypeface(typeface2);
        project_titltle_tv.setTypeface(typeface2);
        desc_title_tv.setTypeface(typeface2);
///////////////////////////////Parts/////////////////////////////////////////////////////

//        mRecyclerView = findViewById(R.id.recyclerview);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(InfoActivity.this, 2);
//
//        mRecyclerView.setLayoutManager(mGridLayoutManager);
//        //FirebaseApp.initializeApp(this);
//
//
//        adapter=new RecentProjectAdapter(mPlaceList,getApplicationContext());
//        Bundle mBundle = getIntent().getExtras();
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        StorageReference lsRef;
//        lsRef = FirebaseStorage.getInstance().getReference().child("Parts");
//////////////////////////////////End of Parts///////////////////////////////////////////////




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
                String model_titleset = mi.getName();
                String model_mainimgLink = mi.getLinkMainImg();
desctv.setText(model_desc);
project_titltle_tv.setText(model_titleset);
                Glide.with(getApplicationContext()).load(model_mainimgLink).into(mPlace);

            }

             }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    //////////////////////////////////////// Parts implementation   //////////////////////////////////////////////////////
//        for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()) {
//            mPlaceList.clear();
//
//            ModelKit mi = eventSnapshot.getValue(ModelKit.class);
//
//            mPlaceList.add(mi);
//
//        }
//
        ////////////////////////////////////// End of mera parts ///////////////////////////////////////////////////


    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//            finish();
//            Intent intent = new Intent(InfoActivity.this, MainActivity.class);
//            startActivity(intent);
//
//    }
}
