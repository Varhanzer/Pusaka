package id.beneranindonesia.pusaka.models;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentList {


    @SerializedName("missionID")
    private String missionID = "";

    @SerializedName("missionTypeID")
    private String missionTypeID = "";

    @SerializedName("misName")
    private String misName = "";

    @SerializedName("misImage")
    private String misImage = "";

    @SerializedName("misDesc")
    private String misDesc = "";

    @SerializedName("point")
    private String point = "";

    @SerializedName("dayStart")
    private String dayStart = "";

    @SerializedName("maxParticipant")
    private String maxParticipant = "";

    @SerializedName("applicants")
    private String applicants = "";

    @SerializedName("reviewCount")
    private String reviewCount = "";

    public ContentList(JSONObject json) {
        try {
            missionID = json.getString("missionID");
        } catch (JSONException e) {
            missionID = "";
        }
        try {
            missionTypeID = json.getString("missionTypeID");
        } catch (JSONException e) {
            missionTypeID = "";
        }
        try {
            misName = json.getString("misName");
        } catch (JSONException e) {
            misName = "";
        }
        try {
            misImage = json.getString("misImage");
        } catch (JSONException e) {
            misImage = "";
        }
        try {
            misDesc = json.getString("misDesc");
        } catch (JSONException e) {
            misDesc = "";
        }
        try {
            reviewCount = json.getString("reviewCount");
        } catch (JSONException e) {
            reviewCount = "";
        }
        try {
            point = json.getString("point");
        } catch (JSONException e) {
            point = "";
        }
        try {
            dayStart = json.getString("dayStart");
        } catch (JSONException e) {
            dayStart = "";
        }
        try {
            maxParticipant = json.getString("maxParticipant");
        } catch (JSONException e) {
            maxParticipant = "";
        }
        try {
            applicants = json.getString("applicants");
        } catch (JSONException e) {
            applicants = "";
        }

    }

    public String getMissionID() {
        return missionID;
    }

    public String getMissionTypeID() {
        return missionTypeID;
    }

    public String getMisName() {
        return misName;
    }

    public String getMisImage() {
        return misImage;
    }

    public String getMisDesc() {
        return misDesc;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public String getPoint() { return point;  }

    public String getDayStart() {
        return dayStart;
    }

    public String getMaxParticipant() { return maxParticipant; }

    public String getApplicants() {
        return applicants;
    }
}