package com.asaproject.plezmoarandroid;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ImageView mPlace;
RecyclerView mRecyclerView;
    TextView desctv,desc_title_tv;
    TextView project_titltle_tv;
DatabaseReference mDatabase;
    StorageReference storageRef ;
    StorageReference islandRef1;
    StorageReference islandRef2;

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

    public void playUnity(View v)
    {
        final FirebaseStorage storage = FirebaseStorage.getInstance();


        storageRef = storage.getReference(model_key);
        islandRef1 = storageRef.child("ARModel");
        islandRef2=storageRef.child("ARModel.manifest");
        // "Model" +intentData.toString()
        File rootpath = new File(Environment.getExternalStorageDirectory(),"Android/data/com.hamaricompany.Vufo/files/AssetBundles");
        if (!rootpath.exists()) {
            rootpath.mkdirs();
        }
        File subroot = new File(Environment.getExternalStorageDirectory(),"Android/data/com.hamaricompany.Vufo/files/AssetBundles");
        if (!subroot.exists()) {
            subroot.mkdirs();
        }

        File localFile1 = new File(subroot, "ARModel" );
        File localFile2=new File(subroot,"ARModel.manifest");


        islandRef1.getFile(localFile1).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("Halwa ho gya ye to!");
            }
        });

        islandRef2.getFile(localFile2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("Halwa ho gya ye to!");
            }
        });



        //Intent intent = new Intent(this, UnityPlayerActivity.class);
        //startActivity(intent);
        PackageManager manager = getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage("com.hamaricompany.Vufo");
            if (i == null) {

                System.out.println("nothing happened");
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(i);

        } catch (ActivityNotFoundException e) {

        }
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
