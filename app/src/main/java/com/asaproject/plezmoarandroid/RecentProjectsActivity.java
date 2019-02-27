package com.asaproject.plezmoarandroid;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;


import com.asaproject.plezmoarandroid.Model.DownloadTask;
import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.asaproject.plezmoarandroid.entities.ModelParts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class RecentProjectsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecentProjectAdapter adapter;
    ModelKit mi;

    StorageReference storageRef ;
    StorageReference islandRef ;



    DatabaseReference arRef;
    Context context;
   // int[] mPlaceList;
   ArrayList<ModelKit> mPlaceList=new ArrayList<>();
    ArrayList<ModelKit> mPlaceList2=new ArrayList<>();
    Set<String> idArray ;
    SharedPreferences settings;
//1550845209560
//             1550845530422
//             1550845632530


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_projects);

        idArray= new HashSet<String>();
        //Loading id of scanned projects for displaying in recent projects
        settings=getSharedPreferences("ScannedProjects",MODE_PRIVATE);
        for(Integer i=0;i<settings.getAll().size();i++)
        {
            idArray.add(settings.getString(i.toString(),"000000000"));
        }


        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(RecentProjectsActivity.this, 2);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        //FirebaseApp.initializeApp(this);

        arRef= FirebaseDatabase.getInstance().getReference();
        //adapter=new RecentProjectAdapter(mPlaceList,getApplicationContext());

        adapter=new RecentProjectAdapter(mPlaceList2,getApplicationContext());
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {

        super.onStart();
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final int[] x = {0};





        arRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPlaceList.clear();
                mPlaceList2.clear();

                for(DataSnapshot eventSnapshot:dataSnapshot.getChildren()) {


                    ModelKit mi = eventSnapshot.getValue(ModelKit.class);

                    mPlaceList.add(mi);

                        //eventsList.add(new KeyForEvents(ei,eventSnapshot.getKey()));
                    for(int j = 0; j<idArray.size();j++) {


                        if (mi.getId().equals(idArray.toArray()[j])) {
                            mPlaceList2.add(mi);

//                            if (isConnectingToInternet())
//                                new DownloadTask(RecentProjectsActivity.this,mi.getLinkArData());
//                            else
//                                Toast.makeText(RecentProjectsActivity.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();
//                            break;


                        }



                    }





                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    //Check if internet is present or not
    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

}



