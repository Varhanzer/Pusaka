package id.beneranindonesia.pusaka.models;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameQuiz {
    /*response :
{
"questionText":"type string",
"answerList":["type array string"],
"answerIndex":"type integer",
"getPoint":"type integer"
}
*/

    @SerializedName("questionText")
    private String questionText = "";



    @SerializedName("answerIndex")
    private int answerIndex = 0;

    @SerializedName("getPoint")
    private int getPoint = 0;

    public GameQuiz(JSONObject json) {
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
