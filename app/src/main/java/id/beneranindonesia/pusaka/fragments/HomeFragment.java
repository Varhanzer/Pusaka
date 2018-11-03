package id.beneranindonesia.pusaka.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.activities.Mission.MissionDetailActivity;
import id.beneranindonesia.pusaka.adapters.ContentListAdapter;
import id.beneranindonesia.pusaka.api.ContentListAPI;
import id.beneranindonesia.pusaka.models.ContentList;
import id.beneranindonesia.pusaka.utils.LoadingDialog;
import id.beneranindonesia.pusaka.utils.Session;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class HomeFragment extends BaseFragment {

//    @BindView(R.id.btn_click_me)
//    Button btnClickMe;

    int fragCount;
    private RecyclerView recyclerView;
    private ContentListAPI contentListAPI;
    private ContentListAdapter adapter;
    private ArrayList<ContentList> contentLists;

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

        adapter      = new ContentListAdapter(getContext(), contentLists);
        adapter.listener = new ContentListAdapter.OnClickListener() {
            @Override
            public void selectedItem(ContentList contentList) {
                Intent intent = new Intent(getActivity(), MissionDetailActivity.class);
                intent.putExtra("MISSION_ID", contentList.getMissionID());
                startActivity(intent);
            }
        };

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContentList();

//        btnClickMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (mFragmentNavigation != null) {
//                    mFragmentNavigation.pushFragment(HomeFragment.newInstance(fragCount + 1));
//
//                }
//            }
//        });

        ((MainActivity) getActivity()).updateToolbarTitle((fragCount == 0) ? "Home" : "Sub Home " + fragCount);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void getContentList() {
        System.out.println("Getting content list...");
        if (loadingDialog == null) loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.showDialog();

        if (contentListAPI == null) contentListAPI = new ContentListAPI();

        contentListAPI.listener = new ContentListAPI.Listener() {
            @Override
            public void contentListReceived(ArrayList<ContentList> list) {
                if (loadingDialog != null) loadingDialog.hideDialog();
                contentLists = list;
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void contentListDidFailWith(int statusCode, String message) {
                if (loadingDialog != null) loadingDialog.hideDialog();
            }
        };
        contentListAPI.get();
    }
}

























