package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.asaproject.plezmoarandroid.Model.UploadProjectDataModel;
import com.asaproject.plezmoarandroid.entities.ModelKit;
import com.asaproject.plezmoarandroid.entities.ModelParts;

import java.util.ArrayList;


public class UploadProject extends AppCompatActivity {


    private static final int PICK_IMAGE=1;
    private static final int PICK_FILE=2;
    ImageView mainImage;
    EditText name;
    EditText info;
    Intent imageIntent;
    Intent arDataIntent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_project);
        mainImage=findViewById(R.id.mainImage);
        name=findViewById(R.id.name);
        info=findViewById(R.id.info);
    }

    public void openGallery(View v)
    {

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);


    }

    public void openFileExplorer(View v)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE);
    }

    public void callUploadData(View v)
    {
        String sname=name.getText().toString();
        String sinfo=info.getText().toString();
        ArrayList<ModelParts> modelPartsList=new ArrayList<>();
        modelPartsList.add(new ModelParts("22","Part1","www.halwa.com"));
        modelPartsList.add(new ModelParts("34","Part2","www.halwawapasse.com"));

        UploadProjectDataModel uploadProjectDataModel=new UploadProjectDataModel(this,new ModelKit(null,sname,sinfo,null,null,modelPartsList),imageIntent,arDataIntent);
        uploadProjectDataModel.uploadData();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode==RESULT_OK&&requestCode == PICK_IMAGE)
        {
            //Getting the intent containing image data in a class variable
            imageIntent=data;
            mainImage.setImageURI(data.getData());


        }
        else if(resultCode==RESULT_OK&&requestCode == PICK_FILE)
        {
            arDataIntent=data;
        }
    }

}
