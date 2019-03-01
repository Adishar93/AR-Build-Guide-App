package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {
    String[] address={"info@plezmo.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingspage);

    }
    public void onAboutClicked(View v) {
        String url = "https://www.plezmo.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void onSupportClicked(View v) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.setPackage("com.google.android.gm");
        i.putExtra(Intent.EXTRA_EMAIL, address);
        startActivity(i);
    }

    public void onFeedbackClicked(View v) {
        Intent i = new Intent(this,Rating.class);
        startActivity(i);

    }
}
