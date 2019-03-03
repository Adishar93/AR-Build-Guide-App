package com.asaproject.plezmoarandroid.Model;


public class CardItem {

    private String mTextResource;
    private String mTitleResource;
    private int mGif;

    public CardItem(String title, String text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public String
    getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }



}
