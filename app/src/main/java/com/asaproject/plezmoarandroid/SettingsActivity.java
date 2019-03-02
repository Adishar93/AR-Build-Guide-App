package com.asaproject.plezmoarandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    String[] address={"info@plezmo.com"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingspage);
        Button about_btn = (Button)findViewById(R.id.about);
        Button feedback_btn = (Button)findViewById(R.id.feedback);
        Button support_btn = (Button)findViewById(R.id.support);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
        about_btn.setTypeface(typeface);
        support_btn.setTypeface(typeface);
        feedback_btn.setTypeface(typeface);

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
