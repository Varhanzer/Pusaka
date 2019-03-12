package id.beneranindonesia.pusaka.activities.Mission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MyMission.PendingMissionFragment;

public class PendingMission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_mission);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PendingMissionFragment.newInstance())
                    .commitNow();
        }
    }
}
