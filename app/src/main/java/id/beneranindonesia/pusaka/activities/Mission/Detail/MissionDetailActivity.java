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
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion.StartAnswerMissionActivity;
import id.beneranindonesia.pusaka.activities.Mission.MapsActivity;
import id.beneranindonesia.pusaka.api.ApiService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.models.ContentDetail;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private String qrString;
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

        if (getSupportActionBar() != null)
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
                Intent intent = new Intent(this, MapsActivity.class);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "Silahkan scan ulang", Toast.LENGTH_LONG).show();
            }
            else{
                qrString = result.getContents();
                qrSignIn(qrString);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void qrSignIn(String qrString) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userID", Session.getInstance().getUserID());
            jsonObject.put("lang", "id");
            jsonObject.put("qrString", qrString);


            HashMap<String, String> params = new HashMap<>();
            params.put("pikachu", jsonObject.toString());

            ApiService apiService = RetrofitClientInstance.getInstance().create(ApiService.class);
            apiService.qrSignin(params).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200) {
                        Toast.makeText(MissionDetailActivity.this, "Selamat kamu berhasil masuk pos!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MissionDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




























