package id.beneranindonesia.pusaka.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Base.BaseActivity;
import id.beneranindonesia.pusaka.activities.MyMission.MyMissionFragment;
import id.beneranindonesia.pusaka.adapters.ViewPagerAdapter;
import id.beneranindonesia.pusaka.fragments.BaseFragment;
import id.beneranindonesia.pusaka.fragments.HomeFragment;
import id.beneranindonesia.pusaka.fragments.NewsFragment;
import id.beneranindonesia.pusaka.fragments.ProfileFragment;
import id.beneranindonesia.pusaka.fragments.SearchFragment;
import id.beneranindonesia.pusaka.fragments.ShareFragment;
import id.beneranindonesia.pusaka.utils.FragmentHistory;
import id.beneranindonesia.pusaka.utils.Session;
import id.beneranindonesia.pusaka.utils.Utils;
import id.beneranindonesia.pusaka.views.FragNavController;

import butterknife.BindArray;
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

    private MenuItem prevMenuItem;

//    @BindView(R.id.viewPager)
    private ViewPager viewPager;
    private BottomNavigationView navigation;

//    @BindView(R.id.content_frame)
//    FrameLayout contentFrame;

    private int[] mTabIconsSelected = {
            R.drawable.tab_home,
            R.drawable.tab_search,
            R.drawable.tab_share,
            R.drawable.tab_news,
            R.drawable.tab_profile};


//    @BindArray(R.array.tab_name)
//    String[] TABS;
//
//    @BindView(R.id.bottom_tab_layout)
//    TabLayout bottomTabLayout;

    private FragNavController mNavController;

    private FragmentHistory fragmentHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        replaceFragment(homeFragment);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "");
        adapter.addFragment(new MyMissionFragment(), "");

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
//        viewPager.setPageTransformer(false, new FadePageTransformer());

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
//                    if(homeFragment == null) {
//                        homeFragment = new HomeFragment();
//                    }
//                    replaceFragment(homeFragment);
                    return true;
                case R.id.tab_search  :
                    viewPager.setCurrentItem(1, false);
//                    if(myMissionFragment == null) {
//                        myMissionFragment = new MyMissionFragment();
//                    }
//                    replaceFragment(myMissionFragment);
                    return true;
                case R.id.tab_share   : return true;
                case R.id.tab_news    : return true;
                case R.id.tab_profile : return true;
            }
            return false;
        }
    };

    private void replaceFragment(@NonNull Fragment fragment) {
//        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment, fragment.getTag()).commit();
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_bottom, null);
        ImageView icon = view.findViewById(R.id.tab_icon);
        icon.setImageDrawable(Utils.setDrawableSelector(MainActivity.this, mTabIconsSelected[position], mTabIconsSelected[position]));
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
//      updateToolbarTitle(position);
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
//
//        if (!mNavController.isRootFragment()) {
//            mNavController.popFragment();
//        } else {
//
//            if (fragmentHistory.isEmpty()) {
//                super.onBackPressed();
//            } else {
//
//                if (fragmentHistory.getStackSize() > 1) {
//
//                    int position = fragmentHistory.popPrevious();
//
//                    switchTab(position);
//
//                    updateTabSelection(position);
//
//                } else {
//
//                    switchTab(0);
//
//                    updateTabSelection(0);
//
//                    fragmentHistory.emptyStack();
//                }
//            }
//
//        }
    }

//    private void updateTabSelection(int currentTab) {
//
//        for (int i = 0; i < TABS.length; i++) {
//            TabLayout.Tab selectedTab = bottomTabLayout.getTabAt(i);
//            if (currentTab != i) {
//                selectedTab.getCustomView().setSelected(false);
//            } else {
//                selectedTab.getCustomView().setSelected(true);
//            }
//        }
//    }

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
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {

//            updateToolbar();

        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {

            case FragNavController.TAB1:
                return new HomeFragment();
            case FragNavController.TAB2:
                return new MyMissionFragment();
//                return new SearchFragment();
            case FragNavController.TAB3:
                return new ShareFragment();
            case FragNavController.TAB4:
                return new NewsFragment();
            case FragNavController.TAB5:
                return new ProfileFragment();

        }
        throw new IllegalStateException("Need to send an index that we know");
    }


//    private void updateToolbarTitle(int position){
//
//
//        getSupportActionBar().setTitle(TABS[position]);
//
//    }

    public void updateToolbarTitle(String title) {

//        getSupportActionBar().setTitle(title);

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
 class FadePageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View view, float position) {
        if(position <= -1.0F || position >= 1.0F) {
            view.setTranslationX(view.getWidth() * position);
            view.setAlpha(0.0F);
        } else if( position == 0.0F ) {
            view.setTranslationX(view.getWidth() * position);
            view.setAlpha(1.0F);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            view.setTranslationX(view.getWidth() * -position);
            view.setAlpha(1.0F - Math.abs(position));
        }
    }

}



















