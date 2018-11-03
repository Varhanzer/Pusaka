package id.beneranindonesia.pusaka.activities.Mission;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;
import id.beneranindonesia.pusaka.api.ContentDetailAPI;
import id.beneranindonesia.pusaka.api.TakeMissionAPI;
import id.beneranindonesia.pusaka.models.ContentDetail;

public class MissionDetailActivity extends AppCompatActivity implements View.OnClickListener, TakeMissionAPI.Listener {

    private static final String TAG = "MissionDetailActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private Button btnTakeMission;
    private Dialog takeMissionDialog;
    private ContentDetailAPI contentDetailAPI;

    private TextView txtOrg, txtMissionName, txtMissionDesc, txtMissionAddress, txtParticipant, txtMissionStartDate, txtMissionPlace;

    private String missionID;
    private ContentDetail missionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);

        this.missionID = getIntent().getStringExtra("MISSION_ID");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.violet_trans_80));

        isServiceOK();

        txtOrg              = findViewById(R.id.txtOrg);
        txtMissionName      = findViewById(R.id.txtMissionName);
        txtMissionDesc      = findViewById(R.id.txtMissionDesc);
        txtMissionAddress   = findViewById(R.id.txtMissionAddress);
        txtParticipant      = findViewById(R.id.txtParticipant);
        txtMissionStartDate = findViewById(R.id.txtMissionStartDate);
        txtMissionPlace     = findViewById(R.id.txtMissionPlace);

        btnTakeMission = findViewById(R.id.btnTakeMission);
        btnTakeMission.setOnClickListener(this);

        getMissionDetail();

    }

    @Override
    public void onClick(View v) {
        if (v == btnTakeMission) {
            takeMissionDialog = new Dialog(MissionDetailActivity.this);
            takeMissionDialog.setContentView(R.layout.popup_claim_mission);
            takeMissionDialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_purple);
            takeMissionDialog.show();

            Button btn_yes = takeMissionDialog.findViewById(R.id.btn_yes);
            btn_yes.setOnClickListener(this);
        } else {
            takeMission();
        }
    }

    public boolean isServiceOK() {
        Log.d(TAG, "isServiceOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MissionDetailActivity.this);

        if(available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiceOK: Google Play Services is working");
            return true;
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG, "isServiceOK: an error occured ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MissionDetailActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You cant  make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void updateUI() {
        if (missionDetail == null) return;

        txtOrg.setText(missionDetail.getOrgName());
        txtMissionName.setText(missionDetail.getMisName());
        txtMissionDesc.setText(missionDetail.getMisDesc());
        txtMissionAddress.setText(missionDetail.getMisAddress());
        txtMissionStartDate.setText(missionDetail.getDayStart());
        txtMissionPlace.setText(missionDetail.getMisPlace());

        String participant = getResources().getString(R.string.total_participant, missionDetail.getApplicants(), missionDetail.getMaxParticipant());
        txtParticipant.setText(participant);

        if (missionDetail.getMissionStatus().equals("0")) {
            btnTakeMission.setText(R.string.mission_detail_take_mission);
            btnTakeMission.setTextColor(getResources().getColor(R.color.white));
            btnTakeMission.setBackgroundColor(this.getResources().getColor(R.color.selected_dot));
        } else {
            btnTakeMission.setText(R.string.mission_detail_mission_taken);
            btnTakeMission.setTextColor(getResources().getColor(R.color.secondary_text));
            btnTakeMission.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

    }

    private void getMissionDetail() {
        if (contentDetailAPI == null)
            contentDetailAPI = new ContentDetailAPI();
        contentDetailAPI.listener = new ContentDetailAPI.Listener() {
            @Override
            public void contentDetailReceived(ContentDetail missionDetail) {
                MissionDetailActivity.this.missionDetail = missionDetail;
                updateUI();
            }

            @Override
            public void contentDetailDidFailWith(int statusCode, String message) {

            }
        };
        contentDetailAPI.get(missionID);
    }

    private void takeMission() {
        TakeMissionAPI takeMissionAPI = new TakeMissionAPI();
        takeMissionAPI.listener = this;
        takeMissionAPI.take(missionID);
    }

    @Override
    public void takeSuccess() {

    }

    @Override
    public void takeFailed(int statusCode, String message) {

    }
}


























