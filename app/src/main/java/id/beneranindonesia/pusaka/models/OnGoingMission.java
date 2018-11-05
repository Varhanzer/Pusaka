package id.beneranindonesia.pusaka.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class OnGoingMission {

    private int missionID        = 0;
    private String misName       = "";
    private String orgName       = "";
    private String dayStart      = "";
    private String pointID       = "";
    private String missionStatus = "";
    private String pointValue    = "";

    private String TAG = "OnGoingMissionModel";

    public OnGoingMission(JSONObject json){
        try { this.missionID = json.getInt("missionID"); } catch (JSONException e) { Log.e(TAG, "missionID"); }
        try { this.misName = json.getString("misName"); } catch (JSONException e) { Log.e(TAG, "misName"); }
        try { this.orgName = json.getString("orgName"); } catch (JSONException e) { Log.e(TAG, "orgName"); }
        try { this.dayStart = json.getString("dayStart"); } catch (JSONException e) { Log.e(TAG, "dayStart"); }
        try { this.pointID = json.getString("pointID"); } catch (JSONException e) { Log.e(TAG, "pointID"); }
        try { this.missionStatus = json.getString("missionStatus"); } catch (JSONException e) { Log.e(TAG, "missionStatus"); }
        try { this.pointValue = json.getString("pointValue"); } catch (JSONException e) { Log.e(TAG, "pointValue"); }
    }

    public int getMissionID() {
        return missionID;
    }

    public String getMisName() {
        return misName;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getDayStart() {
        return dayStart;
    }

    public String getPointID() {
        return pointID;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

    public String getPointValue() {
        return pointValue;
    }

}






















































