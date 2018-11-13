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
import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.utils.Session;

public class ContentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnClickListener {
        void selectedItem(ContentList contentList);
    }

    class ContentListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMissionName;
        private TextView txtMissionDesc;
        private TextView txtMissionPoint;
        private TextView txtPerticipant;

        ContentListViewHolder(View view) {
            super(view);
            txtMissionName  = view.findViewById(R.id.txtMissionTitle);
            txtMissionDesc  = view.findViewById(R.id.txtMissionDesc);
            txtMissionPoint = view.findViewById(R.id.txtMissionPoint);
            txtPerticipant  = view.findViewById(R.id.txtPerticipant);
        }

        void setDetails(ContentList contentList) {
            txtMissionName.setText(contentList.getMisName());
//            txtMissionDesc.setText(contentList.getMisDesc());
            txtMissionPoint.setText(contentList.getPoint());
            String participant = context.getResources().getString(R.string.total_participant, contentList.getApplicants(), contentList.getMaxParticipant());
            txtPerticipant.setText(participant);
            txtMissionDesc.setText("Beneran Indonesia");
        }
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUsername;
        private TextView txtFullName;
        private TextView txtSchool;
        private ImageView imgUser;

        ProfileViewHolder(View view) {
            super(view);
            txtUsername = view.findViewById(R.id.textUsername);
            txtFullName = view.findViewById(R.id.textFullName);
            txtSchool   = view.findViewById(R.id.textSchool);
            imgUser     = view.findViewById(R.id.userImageView);
        }

        void setDetails() {
            txtUsername.setText(Session.getInstance().getNickname());
            txtFullName.setText(Session.getInstance().getNickname());
            txtSchool.setText(Session.getInstance().getNickname());
        }
    }

    private Context context;
    private ArrayList<ContentList> contentLists;
    public OnClickListener listener;

    public ContentListAdapter(Context context, ArrayList<ContentList> contentLists) {
        this.context      = context;
        this.contentLists = contentLists;
    }

    public void setItems(ArrayList<ContentList> lists) {
        this.contentLists = lists;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 1 : 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_home_user_profile, parent, false);
            return new ProfileViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.row_content_list, parent, false);
            return new ContentListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == 1) {
            ProfileViewHolder profileViewHolder = (ProfileViewHolder) holder;
            profileViewHolder.setDetails();
        } else {
            ContentListViewHolder contentListViewHolder = (ContentListViewHolder) holder;
            final ContentList contentList = contentLists.get(position - 1);
            contentListViewHolder.setDetails(contentList);
            contentListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { listener.selectedItem(contentList); }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contentLists == null ? 0 : contentLists.size() + 1;
    }
}





















































