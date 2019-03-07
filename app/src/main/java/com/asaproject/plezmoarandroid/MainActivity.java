package com.asaproject.plezmoarandroid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_main);
        checkPermissionDialog();
        ImageView settings= (ImageView) findViewById(R.id.settings);
        ImageView mascot= (ImageView) findViewById(R.id.mascot);
        Button recent_btn = (Button) findViewById(R.id.recent);
        Button scanqr_btn = (Button) findViewById(R.id.qrcode);
        Button tut_btn = (Button) findViewById(R.id.tutorial);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
        recent_btn.setTypeface(typeface);
        scanqr_btn.setTypeface(typeface);
        tut_btn.setTypeface(typeface);
    }

    public void onrecent(View view) {
        Intent i = new Intent(this,RecentProjectsActivity.class);
        startActivity(i);
    }

    public void openSettings(View v){
        Intent i=new Intent(this,SettingsActivity.class);
        startActivity(i);
    }

    public void onMascotClick(View v){
        ImageView im=(ImageView) findViewById(R.id.bubble);
        int i=im.getVisibility();
        if(i==4)
        im.setVisibility(ImageView.VISIBLE);
        else
            im.setVisibility(ImageView.INVISIBLE);
    }

    public void openqr(View v)
    {

        startActivity(new Intent(this, QRScanActivity.class));
    }

    public void openUploadActivity(View v)
    {
        startActivity(new Intent(this,UploadProject.class));
    }


    public boolean checkPermissionDialog(){

        if( (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) )



        {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))  ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)))
        {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission necessary");
                alertBuilder.setMessage("Storage and Camera Permission required!");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);}});


                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS);

            }
            return false;}
        else {
            return true;
        }
    }
 public void ontutorial(View view) {

        startActivity(new Intent(this, CardMainActivity.class));
    }

}
