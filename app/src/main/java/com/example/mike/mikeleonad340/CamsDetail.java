package com.example.mike.mikeleonad340;

public class CamsDetail {
    private String id;
    private String description;
    private String img;
    private String type;

    CamsDetail(String id, String description, String type, String imageURL) {
        this.id = id;
        this.description = description;
        this.img = imageURL;
        this.type = type;
    }
    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImageURL() {
        return this.img;
    }

    public String getType() {
        return this.type;
    }
}
