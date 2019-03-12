package id.beneranindonesia.pusaka.activities.MyMission;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.viewmodels.PendingMissionViewModel;

public class PendingMissionFragment extends Fragment {

    private PendingMissionViewModel mViewModel;

    public static PendingMissionFragment newInstance() {
        return new PendingMissionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending_mission, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PendingMissionViewModel.class);
        // TODO: Use the ViewModel
    }

}
