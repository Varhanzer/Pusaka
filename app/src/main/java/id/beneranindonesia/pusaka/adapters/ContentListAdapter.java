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

    public class ContentListViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMissionName;
        private TextView txtMissionDesc;
        private TextView txtMissionPoint;

        public ContentListViewHolder(View view) {
            super(view);
            txtMissionName  = view.findViewById(R.id.txtMissionTitle);
            txtMissionDesc  = view.findViewById(R.id.txtMissionDesc);
            txtMissionPoint = view.findViewById(R.id.txtMissionPoint);
        }

        public void setDetails(ContentList contentList) {
            txtMissionName.setText(contentList.getMisName());
            txtMissionDesc.setText(contentList.getMisDesc());
            txtMissionPoint.setText(contentList.getPoint() + "Points");
        }
    }

    private Context context;
    private ArrayList<ContentList> contentLists;

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
    public void onBindViewHolder(@NonNull ContentListViewHolder holder, int position) {
        ContentList contentList = contentLists.get(position);
        holder.setDetails(contentList);
    }

    @Override
    public int getItemCount() {
        return contentLists == null ? 0 : contentLists.size();
    }
}





















































