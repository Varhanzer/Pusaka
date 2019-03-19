package id.beneranindonesia.pusaka.models;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class GameQuestion {

    /*response: "questionText":"type string",
"answerText":"type string",
"getPoint":"type integer"*/

    @SerializedName("questionText")
    private String questionText = "";

    @SerializedName("answerText")
    private String answerText = "";

    @SerializedName("getPoint")
    private int getPoint = 0;

    public GameQuestion(JSONObject json) {
        try {
            questionText = json.getString("questionText");
        } catch (JSONException e) {
            questionText = "";
        }

        try {
            answerText = json.getString("answerText");
        } catch (JSONException e) {
            answerText = "";
        }

        try {
            getPoint = json.getInt("getPoint");
        } catch (JSONException e) {
            getPoint = 0;
        }
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public int getGetPoint() {
        return getPoint;
    }
}
