package id.beneranindonesia.pusaka.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contents {

    @SerializedName("profile")
    private Profile profile;

    @SerializedName("listContent")
    private List<Mission> listContent;

    public Contents(Profile profile, List<Mission> missions) {
//        this.profile = profile;
//        this.listContent = missions;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Mission> getListContent() {
        return listContent;
    }
}
