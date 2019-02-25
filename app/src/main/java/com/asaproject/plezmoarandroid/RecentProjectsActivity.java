package com.asaproject.plezmoarandroid;

import android.content.SharedPreferences;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class RecentProjectsActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecentProjectAdapter adapter;
    ModelKit mi;
    DatabaseReference arRef;
   // int[] mPlaceList;
   ArrayList<ModelKit> mPlaceList=new ArrayList<>();
    ArrayList<ModelKit> mPlaceList2=new ArrayList<>();
    Set<String> idArray ;
    SharedPreferences settings;


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
                    for(int j = 0; j<idArray.size();j++)

                    if(mi.getId() .equals(idArray.toArray()[j]))
                    {
                        mPlaceList2.add(mi);
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



