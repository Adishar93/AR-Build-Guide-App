package com.asaproject.plezmoarandroid.entities;

public class ModelParts
{
    String id;
    String Title;
    String imagelink;

    public ModelParts()
    {

    }

    public ModelParts(String id, String title, String imagelink) {
        this.id = id;
        Title = title;
        this.imagelink = imagelink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
