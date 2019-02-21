package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton settings= (ImageButton) findViewById(R.id.settings);
        ImageButton mascot= (ImageButton) findViewById(R.id.mascot);

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
}
