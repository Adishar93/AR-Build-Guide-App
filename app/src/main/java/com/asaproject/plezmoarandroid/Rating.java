package com.asaproject.plezmoarandroid;

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
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);
        t=(TextView) findViewById(R.id.rating);
        //Performing action on Button Click
        button.setOnClickListener(new View.OnClickListener(){

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