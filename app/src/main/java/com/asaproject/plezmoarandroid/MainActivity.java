package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton settings= (ImageButton) findViewById(R.id.settings);
    }
    public void openSettings(View v){
        Intent i=new Intent(this,SettingsActivity.class);
        startActivity(i);
    }
}
