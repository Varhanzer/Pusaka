package id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Mission.CompassDirection;

public class StartAnswerMissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_answer_mission);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, CompassDirection.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
































