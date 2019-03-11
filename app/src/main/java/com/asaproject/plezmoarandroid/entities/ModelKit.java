package com.asaproject.plezmoarandroid.entities;

import java.util.ArrayList;

public class ModelKit
{
    String id;
    String name;
    String info;
    String linkMainImg;
    String linkArData1;
    String linkArData2;
    ArrayList<ModelParts> parts;


    public ModelKit()
    {

    }

    public ModelKit(String id, String name, String info, String linkMainImg,String linkArData1,String linkArData2 ,ArrayList<ModelParts> parts) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.linkMainImg = linkMainImg;
        this.parts = parts;
        this.linkArData1=linkArData1;
    }

    public String getLinkArData1() {
        return linkArData1;
    }

    public void setLinkArData1(String linkArData1) {
        this.linkArData1 = linkArData1;
    }

    public String getLinkArData2() {
        return linkArData2;
    }

    public void setLinkArData2(String linkArData2) {
        this.linkArData2 = linkArData2;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLinkMainImg() {
        return linkMainImg;
    }

    public void setLinkMainImg(String linkMainImg) {
        this.linkMainImg = linkMainImg;
    }

    public ArrayList<ModelParts> getParts() {
        return parts;
    }

    public void setParts(ArrayList<ModelParts> parts) {
        this.parts = parts;
    }
}
