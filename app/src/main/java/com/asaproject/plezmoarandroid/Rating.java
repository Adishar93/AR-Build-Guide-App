package com.asaproject.plezmoarandroid;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Rating extends AppCompatActivity {
    RatingBar ratingbar;
    Button button;
    TextView t,t1;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        addListenerOnButtonClick();
        submit=(Button)findViewById(R.id.feedback_submit_button);
        t=(TextView) findViewById(R.id.rating);
        t1=(TextView) findViewById(R.id.text);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
        Typeface typeface2 = Typeface.createFromAsset(getAssets(),"font/roboto_regular.ttf");
       submit.setTypeface(typeface);
       t.setTypeface(typeface2);
        t1.setTypeface(typeface2);

    }
    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);


        //Performing action on Button Click
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                float r=ratingbar.getRating();

                if(r<2){
                    t.setText("Rating : "+r+ "\nIs it that worse?");
                }
                else if(r<=3 && r>=2){
                    t.setText("Rating : "+r+ "\nWe'll try to be better!");
                }
                else if(r<=4 && r>3){
                    t.setText("Rating : "+r+ "\nThat means you're having a good time here!");
                }
                else if(r>4){
                    t.setText("Rating : "+r+ "\nWow! We'll keep up the good work!");
                }
            }
        });
    }
}