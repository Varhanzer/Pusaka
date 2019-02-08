package id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;

public class AnswerQuestionActivity extends AppCompatActivity {

    @BindView(R.id.txtCountdown)
    TextView txtCountdown;

    CountDownTimer cTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    private void startTimer() {
        cTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtCountdown.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

            }
        };
        cTimer.start();
    }

    private void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
    }

}
