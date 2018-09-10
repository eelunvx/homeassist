package com.axzae.homeassistant.model.rest;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


public class FCMAndroidResponse {

    @SerializedName("message")
    public String message;

    public String toString() {
        return (new Gson()).toJson(this);
    }

    public String getMessage() { return message;}
}
