package id.beneranindonesia.pusaka.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Base.BaseActivity;
import id.beneranindonesia.pusaka.activities.MyMission.MyMissionFragment;
import id.beneranindonesia.pusaka.adapters.ViewPagerAdapter;
import id.beneranindonesia.pusaka.fragments.BaseFragment;
import id.beneranindonesia.pusaka.fragments.HomeFragment;
import id.beneranindonesia.pusaka.fragments.ProfileFragment;
import id.beneranindonesia.pusaka.fragments.SettingsFragment;
import id.beneranindonesia.pusaka.fragments.ShareFragment;
import id.beneranindonesia.pusaka.utils.FragmentHistory;
import id.beneranindonesia.pusaka.views.FragNavController;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yoshua Andrew on 06/06/18.
 */

public class MainActivity extends BaseActivity implements
        BaseFragment.FragmentNavigation,
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener {

    private HomeFragment homeFragment;
    private MyMissionFragment myMissionFragment;
    private FragmentManager fragmentManager;
    private SettingsFragment fragmentSettings;

    private MenuItem prevMenuItem;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

//    @BindView(R.id.content_frame)
//    FrameLayout contentFrame;

    private FragNavController mNavController;

    private FragmentHistory fragmentHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "");
        adapter.addFragment(new MyMissionFragment(), "");
        adapter.addFragment(new ShareFragment(), "");
        adapter.addFragment(new SettingsFragment(), "");
        adapter.addFragment(new ProfileFragment(), "");


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }

                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_missions:
                    viewPager.setCurrentItem(0, false);
                    return true;
                case R.id.tab_search  :
                    viewPager.setCurrentItem(1, false);
                    return true;
                case R.id.tab_share   :
                    viewPager.setCurrentItem(2, false);
                    return true;
                case R.id.tab_settings    :
                    viewPager.setCurrentItem(3, false);
                    return true;
                case R.id.tab_profile :
                    viewPager.setCurrentItem(4, false);
                    return true;
            }
            return false;
        }
    };

    private View getTabView(int position) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_bottom, null);
        ImageView icon = view.findViewById(R.id.tab_icon);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private void switchTab(int position) {
        mNavController.switchTab(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {

//            updateToolbar();

        }
    }

    private void updateToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        getSupportActionBar().setDisplayShowHomeEnabled(!mNavController.isRootFragment());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        if (getSupportActionBar() != null && mNavController != null) {


        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {

            case FragNavController.TAB1:
                return new HomeFragment();
            case FragNavController.TAB2:
                return new MyMissionFragment();
            case FragNavController.TAB3:
                return new ShareFragment();
            case FragNavController.TAB4:
                return new SettingsFragment();
            case FragNavController.TAB5:
                return new ProfileFragment();

        }
        throw new IllegalStateException("Need to send an index that we know");
    }


    public void updateToolbarTitle(String title) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Main activity", "onActivityResult");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result not found", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();

            }
        }
    }
}



















