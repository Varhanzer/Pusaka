package id.beneranindonesia.pusaka.models;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("nextMission")
    private String nextMission;

    @SerializedName("schoolName")
    private String schoolName;

    @SerializedName("point")
    private String point;

    @SerializedName("imageFile")
    private String imageFile;

    public Profile(String nickName, String fullName, String nextMission, String schoolName, String point, String imageFile) {
        this.nickName = nickName;
        this.fullName = fullName;
        this.nextMission = nextMission;
        this.schoolName = schoolName;
        this.point = point;
        this.imageFile = imageFile;
    }

    public String getNickName() {
        return nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNextMission() {
        return nextMission;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getPoint() {
        return point;
    }

    public String getImageFile() {
        return imageFile;
    }
}
