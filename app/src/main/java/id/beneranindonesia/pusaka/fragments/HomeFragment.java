package id.beneranindonesia.pusaka.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;

import id.beneranindonesia.pusaka.activities.Mission.AnswerQuestion.StartAnswerMissionActivity;
import id.beneranindonesia.pusaka.activities.Mission.MissionDetailActivity;
import id.beneranindonesia.pusaka.adapters.ContentListAdapter;
import id.beneranindonesia.pusaka.api.ContentListAPI;
import id.beneranindonesia.pusaka.api.GetDataService;
import id.beneranindonesia.pusaka.api.RetrofitClientInstance;
import id.beneranindonesia.pusaka.api.Token_1;
import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;
import id.beneranindonesia.pusaka.utils.TokenManager;
import id.beneranindonesia.pusaka.viewmodels.MissionListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

//    @BindView(R.id.btn_click_me)
//    Button btnClickMe;

    int fragCount;
    private ContentListAdapter adapter;
    private MissionListViewModel missionListViewModel;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private LoadingDialog loadingDialog;

    public static HomeFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }

        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.showDialog();

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        getMissions();

        return view;
    }

    // Swipe Refresh Layout on refresh method
    @Override
    public void onRefresh() {
        getMissions();
    }

    private void getMissions() {

        if(missionListViewModel == null)
            missionListViewModel = ViewModelProviders.of(this).get(MissionListViewModel.class);

        missionListViewModel.getMissions(getContext()).observe(this, new Observer<List<Mission>>() {
            @Override
            public void onChanged(@Nullable List<Mission> missions) {
                adapter = new ContentListAdapter(getContext(), missions);
                adapter.listener = new ContentListAdapter.OnClickListener() {
                    @Override
                    public void selectedItem(Mission mission) {
                        Intent intent = new Intent(getActivity(), MissionDetailActivity.class);
                        intent.putExtra("MISSION_ID", mission.getMissionID());
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                };
                recyclerView.setAdapter(adapter);

                mSwipeRefreshLayout.setRefreshing(false);

                loadingDialog.hideDialog();
            }
        });

    }

}

























