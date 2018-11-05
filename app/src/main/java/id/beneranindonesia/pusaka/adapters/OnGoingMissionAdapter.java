package id.beneranindonesia.pusaka.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.models.OnGoingMission;

public class OnGoingMissionAdapter extends RecyclerView.Adapter<OnGoingMissionAdapter.OnGoingMissionViewHolder> {

    public class OnGoingMissionViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMissionIssuer;
        private TextView txtMissionName;
        private TextView txtMissionDate;
        private TextView txtMissionPoint;
        private ImageView iv_mission;

        public OnGoingMissionViewHolder(View view) {
            super(view);
            txtMissionIssuer = view.findViewById(R.id.txtMissionIssuer);
            txtMissionName   = view.findViewById(R.id.txtMissionName);
            txtMissionDate   = view.findViewById(R.id.txtMissionDate);
            txtMissionPoint  = view.findViewById(R.id.txtMissionPoint);
            iv_mission       = view.findViewById(R.id.iv_mission);
        }

        public void setDetails(OnGoingMission onGoingMission) {
            txtMissionIssuer.setText(onGoingMission.getOrgName());
            txtMissionName.setText(onGoingMission.getMisName());
            txtMissionDate.setText(onGoingMission.getDayStart());
            txtMissionPoint.setText(onGoingMission.getPointValue());
        }
    }

    private Context context;
    private ArrayList<OnGoingMission> onGoingMissionList;

    public OnGoingMissionAdapter(Context context, ArrayList<OnGoingMission> onGoingMissionList) {
        this.context            = context;
        this.onGoingMissionList = onGoingMissionList;
    }

    public void setItems(ArrayList<OnGoingMission> lists) {
        this.onGoingMissionList = lists;
    }

    @NonNull
    @Override
    public OnGoingMissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_ongoing_mission, parent, false);
        return new OnGoingMissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingMissionViewHolder holder, int position) {
        final OnGoingMission contentList = onGoingMissionList.get(position);
        holder.setDetails(contentList);
    }

    @Override
    public int getItemCount() {
        return onGoingMissionList == null ? 0 : onGoingMissionList.size();
    }

}


























