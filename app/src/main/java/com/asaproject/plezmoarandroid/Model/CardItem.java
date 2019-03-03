package com.asaproject.plezmoarandroid.Model;


import pl.droidsonroids.gif.GifImageView;

public class CardItem {

    private String mTextResource;
    private String mTitleResource;
    int mGif;

    public CardItem(String title, String text,int  gif) {
        mTitleResource = title;
        mTextResource = text;
        mGif=gif;
    }

    public String
    getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }

    public int getmGif() {
        return mGif;
    }
}
