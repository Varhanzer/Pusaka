package id.beneranindonesia.pusaka.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userID")
    private String userID;

    @SerializedName("imageFile")
    private String imageFile;

    @SerializedName("nickName")
    private String nickName;

    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public String getImageFile() {
        return imageFile;
    }

    public String getNickName() {
        return nickName;
    }
}
