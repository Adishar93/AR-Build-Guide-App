package com.asaproject.plezmoarandroid.entities;

public class ModelKit
{
    String id;
    String name;
    String info;
    String linkMainImg;
    ModelParts[] parts;

    public void ModelKit()
    {

    }

    public ModelKit(String id, String name, String info, String linkMainImg, ModelParts[] parts) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.linkMainImg = linkMainImg;
        this.parts = parts;
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

    public ModelParts[] getParts() {
        return parts;
    }

    public void setParts(ModelParts[] parts) {
        this.parts = parts;
    }
}
