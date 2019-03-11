package com.asaproject.plezmoarandroid.Model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadProjectDataModel
{
    private ModelKit modelKit;
    private StorageReference storageReference;
    DatabaseReference databaseReference;
    Intent imageIntent;
    Intent arDataIntent1;
    Intent arDataIntent2;
    Context context;

    public UploadProjectDataModel(Context context, ModelKit modelKit, Intent imageIntent,Intent arDataIntent1,Intent arDataIntent2)
    {
        this.modelKit=modelKit;
        this.imageIntent=imageIntent;
        this.context=context;
        this.arDataIntent1=arDataIntent1;
        this.arDataIntent2=arDataIntent2;
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void uploadData()
    {
        final Long randomName = System.currentTimeMillis();

        final StorageReference projectStorageRef = storageReference.child(randomName.toString());

        final StorageReference mainImageStorageRef = projectStorageRef.child("MainImage");
        final StorageReference arDataStorageRef1 = projectStorageRef.child("ARModel");
        final StorageReference arDataStorageRef2 = projectStorageRef.child("ARModel.manifest");


//Uploading Main Image then AR DATA then database in order(nested onsuccess)

        mainImageStorageRef.putFile(imageIntent.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                mainImageStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        modelKit.setLinkMainImg(uri.toString());


                        Toast.makeText(context, "Upload IMAGE successful", Toast.LENGTH_SHORT).show();


                        arDataStorageRef1.putFile(arDataIntent1.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                arDataStorageRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        //ei = new EventItems(title, desc,date, uri.toString(), "img" + randomName,FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                        //String generatedProjectID = databaseReference.push().getKey();
                                        modelKit.setLinkArData1(uri.toString());
                                        modelKit.setId(randomName.toString());

                                        databaseReference.child(randomName.toString()).setValue(modelKit);
                                    }
                                });

                                Toast.makeText(context, "Upload AR Model successful", Toast.LENGTH_SHORT).show();

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        //Nothing is uploaded only toast is displayed
                                        ;
                                        Toast.makeText(context, "Sorry! Upload AR Model unsuccessful", Toast.LENGTH_SHORT).show();

                                    }
                                });

                        arDataStorageRef2.putFile(arDataIntent2.getData()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                arDataStorageRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        //ei = new EventItems(title, desc,date, uri.toString(), "img" + randomName,FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                        //String generatedProjectID = databaseReference.push().getKey();
                                        modelKit.setLinkArData2(uri.toString());
                                        modelKit.setId(randomName.toString());

                                        databaseReference.child(randomName.toString()).setValue(modelKit);
                                    }
                                });

                                Toast.makeText(context, "Upload AR META successful", Toast.LENGTH_SHORT).show();

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        //Nothing is uploaded only toast is displayed
                                        ;
                                        Toast.makeText(context, "Sorry! Upload AR META unsuccessful", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                });

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                //Nothing is uploaded only toast is displayed
                                ;
                                Toast.makeText(context, "Sorry! Upload IMAGE unsuccessful", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }








