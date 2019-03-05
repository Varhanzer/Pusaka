package id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Account.login.view.LoginActivity;
import id.beneranindonesia.pusaka.activities.Account.register.RegisterActivity;

public class AnswerQuestionActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);

    }

    public void onClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        int choice=0;
        switch (view.getId()) {
            case R.id.choice_a:
                if (checked)
                    choice = 1;
                    break;
            case R.id.choice_b:
                if (checked)
                    choice = 2;
                    break;

        }

    }
}
