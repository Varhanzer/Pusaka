package id.beneranindonesia.pusaka.activities.MyMission;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.adapters.OnGoingMissionAdapter;
import id.beneranindonesia.pusaka.api.OnGoingMissionAPI;
import id.beneranindonesia.pusaka.models.OnGoingMission;

public class OnGoingMissionFragment extends Fragment implements OnGoingMissionAPI.Listener {

    private RecyclerView recyclerView;
    private OnGoingMissionAdapter adapter;
    private ArrayList<OnGoingMission> onGoingMissionList;

    public OnGoingMissionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_mission_ongoing, container, false);

        adapter = new OnGoingMissionAdapter(getContext(), onGoingMissionList);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        getOnGoingMissionList();

        return view;
    }

    private void getOnGoingMissionList() {
        OnGoingMissionAPI onGoingMissionAPI = new OnGoingMissionAPI();
        onGoingMissionAPI.listener = this;
        onGoingMissionAPI.get();
    }

    @Override
    public void onGoingMissionListReceived(ArrayList<OnGoingMission> list) {
        for(OnGoingMission mission: list) {
            System.out.println(mission.getMisName());
        }
        this.onGoingMissionList = list;
        adapter.setItems(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGoingMissionListDidFailWith(int statusCode, String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
