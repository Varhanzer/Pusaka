package id.beneranindonesia.pusaka.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentList {

    private String missionID;
    private String missionTypeID;
    private String misName;
    private String misImage;
    private String misDesc;
    private String reviewCount;
    private String point;

    public ContentList(JSONObject json) {
        try { missionID = json.getString("missionID"); } catch (JSONException e) { missionID = ""; }
        try { missionTypeID = json.getString("missionTypeID"); } catch (JSONException e) { missionTypeID = ""; }
        try { misName = json.getString("misName"); } catch (JSONException e) { misName = ""; }
        try { misImage = json.getString("misImage"); } catch (JSONException e) { misImage = ""; }
        try { misDesc = json.getString("misDesc"); } catch (JSONException e) { misDesc = ""; }
        try { reviewCount = json.getString("reviewCount"); } catch (JSONException e) { reviewCount = ""; }
        try { point = json.getString("point"); } catch (JSONException e) { point = ""; }
    }

    public String getMissionID() {
        return missionID;
    }

    public void setMissionID(String missionID) {
        this.missionID = missionID;
    }

    public String getMissionTypeID() {
        return missionTypeID;
    }

    public void setMissionTypeID(String missionTypeID) {
        this.missionTypeID = missionTypeID;
    }

    public String getMisName() {
        return misName;
    }

    public void setMisName(String misName) {
        this.misName = misName;
    }

    public String getMisImage() {
        return misImage;
    }

    public void setMisImage(String misImage) {
        this.misImage = misImage;
    }

    public String getMisDesc() {
        return misDesc;
    }

    public void setMisDesc(String misDesc) {
        this.misDesc = misDesc;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}



































