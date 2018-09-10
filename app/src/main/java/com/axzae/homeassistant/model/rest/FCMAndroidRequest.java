package com.axzae.homeassistant.model.rest;

import com.google.gson.annotations.SerializedName;

public class FCMAndroidRequest {

    public FCMAndroidRequest(String token){ this.token = token; }

    @SerializedName("token")
    public String token;

    @SerializedName("action")
    public String action;

    @SerializedName("type")
    public String type;

    public FCMAndroidRequest setAction(String value){
        this.action = value;
        return this;
    }

    public FCMAndroidRequest setType(String value){
        this.type = value;
        return this;
    }

}
