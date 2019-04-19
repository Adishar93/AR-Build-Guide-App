package com.asaproject.plezmoarandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.asaproject.plezmoarandroid.Model.CardFragmentPagerAdapter;
import com.asaproject.plezmoarandroid.Model.CardItem;
import com.asaproject.plezmoarandroid.Model.CardPagerAdapter;
import com.asaproject.plezmoarandroid.Model.ShadowTransformer;

public class CardMainActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener
        {

    private Button mButton;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    TextView t1,t2;
    private boolean mShowingFragments = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_main);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//        t1=findViewById(R.id.tutorial_title);
//        t2=findViewById(R.id.tutorial_desc);
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/orange_juice2.ttf");
//        Typeface typeface2 = Typeface.createFromAsset(getAssets(),"font/roboto_regular.ttf");
//        t1.setTypeface(typeface2);
//        t2.setTypeface(typeface2);
//        mButton.setTypeface(typeface);
        //mButton.setOnClickListener(this);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem("DIGITAL ASSISTANT","Tap on Plezmo man for any help" ,R.drawable.plezmoman));
        mCardAdapter.addCardItem(new CardItem("Scan QR CODE","Here you can scan QR CODE" ,R.drawable.qrcode));
        mCardAdapter.addCardItem(new CardItem("ASSEMBLE","ASSEMBLY INSTRUCTIONS" ,R.drawable.assemble1));

        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }

            public void onExitClick(View view) {
        Intent i2 = new Intent(this,MainActivity.class);
        startActivity(i2);
            }

            public void onBackClickCards(View v)
            {
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);

            }

        }
