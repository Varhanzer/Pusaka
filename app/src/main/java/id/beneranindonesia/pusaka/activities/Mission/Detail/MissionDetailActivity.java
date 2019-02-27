package id.beneranindonesia.pusaka.activities.Mission.Detail;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion.StartAnswerMissionActivity;
import id.beneranindonesia.pusaka.models.ContentDetail;
import id.beneranindonesia.pusaka.utils.LoadingDialog;

public class MissionDetailActivity extends AppCompatActivity {

    @BindView(R.id.txtOrg)
    TextView txtOrg;

    @BindView(R.id.txtMissionName)
    TextView txtMissionName;

    @BindView(R.id.txtMissionDesc)
    TextView txtMissionDesc;

    @BindView(R.id.txtMissionAddress)
    TextView txtMissionAddress;

    @BindView(R.id.txtParticipant)
    TextView txtParticipant;

    @BindView(R.id.txtMissionStartDate)
    TextView txtMissionStartDate;

    @BindView(R.id.txtMissionPlace)
    TextView txtMissionPlace;

    @BindView(R.id.btnTakeMission)
    Button btnTakeMission;

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private Dialog takeMissionDialog;
    private IntentIntegrator qrScan;

    private String missionID;
    private ContentDetail missionDetail;
    private LoadingDialog loadingDialog;

    MissionDetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.violet_trans_80));

        loadingDialog = new LoadingDialog(this);
        loadingDialog.showDialog();

        missionID = getIntent().getStringExtra("MISSION_ID");
        viewModel = ViewModelProviders.of(this).get(MissionDetailViewModel.class);
        viewModel.getMissionDetail(missionID).observe(this, missionDetail -> {
            if (loadingDialog != null) loadingDialog.hideDialog();
            if (missionDetail == null) return;
            this.missionDetail = missionDetail;
            this.updateUI(missionDetail);
        });

        btnTakeMission.setOnClickListener(v -> {
            if (missionDetail.getMissionStatus().equals("1")) {
//                qrScan.initiateScan();
                Intent intent = new Intent(this, StartAnswerMissionActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                if (takeMissionDialog == null) {
                    takeMissionDialog = new Dialog(MissionDetailActivity.this);
                }
                takeMissionDialog.setContentView(R.layout.popup_claim_mission);
                takeMissionDialog.getWindow().setBackgroundDrawableResource(android.R.color.holo_purple);
                takeMissionDialog.show();

                Button btnYes = takeMissionDialog.findViewById(R.id.btn_yes);
                btnYes.setOnClickListener(view -> {
                    takeMissionDialog.hide();
//                    takeMission();
                });
            }
        });

    }

    private void updateUI(ContentDetail missionDetail) {
        txtOrg.setText(missionDetail.getOrgName());
        txtMissionName.setText(missionDetail.getMisName());
        txtMissionDesc.setText(missionDetail.getMisDesc());
        txtMissionAddress.setText(missionDetail.getMisAddress());
        txtMissionStartDate.setText(missionDetail.getDayStart());
        txtMissionPlace.setText(missionDetail.getMisPlace());

        String participant = getResources().getString(R.string.total_participant, missionDetail.getApplicants(), missionDetail.getMaxParticipant());
        txtParticipant.setText(participant);

//        if (missionDetail.getMissionStatus().equals("0")) {
////            btnTakeMission.setText(R.string.mission_detail_take_mission);
////            btnTakeMission.setText(R.string.mission_detail_take_mission);
////            btnTakeMission.setTextColor(getResources().getColor(R.color.white));
//            btnTakeMission.setBackgroundColor(this.getResources().getColor(R.color.selected_dot));
//        } else if (missionDetail.getMissionStatus().equals("1")){
////            btnTakeMission.setText(R.string.mission_detail_scan_attendance);
////            btnTakeMission.setText(R.string.mission_detail_mission_taken);
////            btnTakeMission.setTextColor(getResources().getColor(R.color.secondary_text));
//            btnTakeMission.setBackgroundColor(this.getResources().getColor(R.color.colorPrimaryDark));
//        }

    }

//    private void takeMission() {
//        TakeMissionAPI takeMissionAPI = new TakeMissionAPI();
//        takeMissionAPI.listener = this;
//        takeMissionAPI.take(missionID);
//    }
//
//    @Override
//    public void takeSuccess() {
//        getMissionDetail();
//    }
//
//    @Override
//    public void takeFailed(int statusCode, String message) {
//
//    }

}



























