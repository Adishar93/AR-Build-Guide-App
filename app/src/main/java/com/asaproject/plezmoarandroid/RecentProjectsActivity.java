package com.asaproject.plezmoarandroid;

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
    String [] idArray = { "1550845209560","1550845530422","1550845632530"} ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_projects);

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



        final String filename = "myfile";
        final String[] fileUrl = {" "};
        //final String[] fileContents = new String[1];
        FileOutputStream outputStream;

        arRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPlaceList.clear();
                mPlaceList2.clear();

                for(DataSnapshot eventSnapshot:dataSnapshot.getChildren())
                {


                    ModelKit mi=eventSnapshot.getValue(ModelKit.class);

                    mPlaceList.add(mi);
                        //eventsList.add(new KeyForEvents(ei,eventSnapshot.getKey()));
                    for(int j = 0; j<idArray.length;j++) {
                        if (mi.getId().equals(idArray[j])) {
                            mPlaceList2.add(mi);
                            storageRef = storage.getReference("1550845530422");
                            islandRef = storageRef.child("ARData");
                            File rootpath = new File(Environment.getRootDirectory(),"ARapp");
                            if(!rootpath.exists())
                            {
                                rootpath.mkdirs();
                            }

                            //File localFile = null;
                            //try {
                               final  File localFile = new File(rootpath,"image.fbh");
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                System.out.println("file hi nahi ban rahi!");
//                            }

                            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    System.out.println("Halwa ho gya ye to!");
                                }
                            });
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
}



