package id.beneranindonesia.pusaka.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.R;

import id.beneranindonesia.pusaka.activities.Mission.Detail.MissionDetailActivity;
import id.beneranindonesia.pusaka.adapters.ContentListAdapter;
import id.beneranindonesia.pusaka.models.Mission;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.viewmodels.MissionListViewModel;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private ContentListAdapter adapter;
    private MissionListViewModel missionListViewModel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.showDialog();

        mSwipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.getItemAnimator().setChangeDuration(0);

        missionListViewModel = ViewModelProviders.of(this).get(MissionListViewModel.class);

        getMissions();

        return view;
    }

    // Swipe Refresh Layout on refresh method
    @Override
    public void onRefresh() {
        getMissions();
    }

    private void getMissions() {
        missionListViewModel.getMissions().observe(this, missions -> {
            adapter = new ContentListAdapter(getContext(), missions);
            adapter.listener = (mission -> {
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), MissionDetailActivity.class);
                    intent.putExtra("MISSION_ID", mission.getMissionID());
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });

            // Set the adapter to the recycler view
            recyclerView.setAdapter(adapter);

            // Disable all loading animation
            loadingDialog.hideDialog();
            mSwipeRefreshLayout.setRefreshing(false);
        });
    }

}

























