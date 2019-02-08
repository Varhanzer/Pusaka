package id.beneranindonesia.pusaka.models;

import android.arch.lifecycle.ViewModel;

import com.google.gson.annotations.SerializedName;

public class Mission extends ViewModel {

    @SerializedName("missionID")
    private String missionID;

    @SerializedName("missionTypeID")
    private String missionTypeID;

    @SerializedName("misName")
    private String misName;

    @SerializedName("misImage")
    private String misImage;

    @SerializedName("misDesc")
    private String misDesc;

    @SerializedName("reviewCount")
    private String reviewCount;

    @SerializedName("point")
    private String point;

    @SerializedName("dayStart")
    private String dayStart;

    @SerializedName("maxParticipant")
    private String maxParticipant;

    @SerializedName("applicants")
    private String applicants;

    public Mission(String missionID, String missionTypeID, String misName, String misImage, String misDesc, String reviewCount, String point, String dayStart, String maxParticipant, String applicants) {
        this.missionID = missionID;
        this.missionTypeID = missionTypeID;
        this.misName = misName;
        this.misImage = misImage;
        this.misDesc = misDesc;
        this.reviewCount = reviewCount;
        this.point = point;
        this.dayStart = dayStart;
        this.maxParticipant = maxParticipant;
        this.applicants = applicants;
    }

    public String getMissionID() {
        return missionID;
    }

    public String getMissionTypeID() {
        return missionTypeID;
    }

    public String getMissionName() {
        return misName;
    }

    public String getMissionImage() {
        return misImage;
    }

    public String getMissionDesc() {
        return misDesc;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public String getPoint() {
        return point;
    }

    public String getDayStart() {
        return dayStart;
    }

    public String getMaxParticipant() {
        return maxParticipant;
    }

    public String getApplicants() {
        return applicants;
    }
}









































