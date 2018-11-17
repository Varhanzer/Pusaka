package id.beneranindonesia.pusaka.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ContentDetail {
    private int missionTypeID     = 0;
    private String orgName        = "";
    private String misName        = "";
    private String misImage       = "";
    private String misDesc        = "";
    private double latitude       = 0;
    private double longtitude     = 0;
    private String dayStart       = "";
    private String maxParticipant = "";
    private String applicants     = "";
    private String misAddress     = "";
    private String misPlace       = "";
    private String pointValue     = "";
    private String missionStatus  = "";

    public ContentDetail(JSONObject json) {
        try { this.missionTypeID = json.getInt("missionTypeID"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.orgName = json.getString("orgName"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.misName = json.getString("misName"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.misImage = json.getString("misImage"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.misDesc = json.getString("misDesc"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.latitude = json.getDouble("latitude"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.longtitude = json.getDouble("longitude"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.dayStart = json.getString("dayStart"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.maxParticipant = json.getString("maxParticipant"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.applicants = json.getString("applicants"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.misAddress = json.getString("misAddress"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.misPlace = json.getString("misPlace"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.pointValue = json.getString("pointValue"); } catch (JSONException e) { e.printStackTrace(); }
        try { this.missionStatus = json.getString("missionStatus"); } catch (JSONException e) { e.printStackTrace(); }
    }

    public int getMissionTypeID() {
        return missionTypeID;
    }

    public String getOrgName() {
        return orgName;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
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

    public String getMisAddress() {
        return misAddress;
    }

    public void setMisAddress(String misAddress) {
        this.misAddress = misAddress;
    }

    public String getMisPlace() {
        return misPlace;
    }

    public String getPointValue() {
        return pointValue;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

}










































