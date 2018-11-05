package id.beneranindonesia.pusaka.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.models.ContentList;

public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.ContentListViewHolder> {

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

    @NonNull
    @Override
    public ContentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_content_list, parent, false);
        return new ContentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentListViewHolder holder, final int position) {
        final ContentList contentList = contentLists.get(position);
        holder.setDetails(contentList);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { listener.selectedItem(contentList); }
        });
    }

    @Override
    public int getItemCount() {
        return contentLists == null ? 0 : contentLists.size();
    }
}





















































