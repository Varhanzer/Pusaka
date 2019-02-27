package id.beneranindonesia.pusaka.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.beneranindonesia.pusaka.activities.OnBoard.OnBoardingActivity;
import id.beneranindonesia.pusaka.utils.Session;

import static id.beneranindonesia.pusaka.fragments.BaseFragment.ARGS_INSTANCE;


public class NewsFragment extends BaseFragment {


   int fragCount;

    @BindView(R.id.btnAboutUs)
    Button btnAbout;
    @BindView(R.id.btnRateUs)
    Button btnRate;
    @BindView(R.id.btnShare)
    Button btnShare;
    @BindView(R.id.btnLogout)
    Button btnLogout;



    public static NewsFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;

   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            fragCount = args.getInt(ARGS_INSTANCE);
        }

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        btnAbout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.beneranindonesia.id/")));
            }
        });


        btnShare.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody="Ayo temukan Pusaka Indonesia dan kumpulkan poinnya di sini\nhttps://play.google.com/store/apps/details?id=id.beneranindonesia.pusaka";
                String shareSubject="Pusaka App";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(shareIntent,"Share Using"));
            }
        });


        btnRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=id.beneranindonesia.pusaka")));
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Session.getInstance().signOut(getActivity());
                Intent intent = new Intent(getActivity(), OnBoardingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        ((MainActivity) getActivity()).updateToolbarTitle((fragCount == 0) ? "News" : "Sub News " + fragCount);
    }


}
