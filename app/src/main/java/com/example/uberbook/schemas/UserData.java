package com.example.uberbook.schemas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @Expose
    @SerializedName("id")
    Integer id;

    @Expose
    @SerializedName("username")
    String username;

    @Expose
    @SerializedName("email")
    String email;
}