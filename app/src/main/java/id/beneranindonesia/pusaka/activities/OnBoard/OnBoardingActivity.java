package id.beneranindonesia.pusaka.activities.OnBoard;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.beneranindonesia.pusaka.R;
import id.beneranindonesia.pusaka.activities.Account.LoginActivity;
import id.beneranindonesia.pusaka.activities.Account.RegisterActivity;

public class OnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPager onboard_pager;
    private OnBoardAdapter mAdapter;
    private Button btn_get_started;
    private TextView to_login_activity;
    int previous_pos = 0;
    int[] buttonColor = {R.color.pink, R.color.java_blue, R.color.bittersweet, R.color.amber};

    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        btn_get_started   = findViewById(R.id.btn_get_started);
        onboard_pager     = findViewById(R.id.pager_introduction);
        pager_indicator   = findViewById(R.id.viewPagerCountDots);
        to_login_activity = findViewById(R.id.txtToLoginActivity);
        loadData();

        mAdapter = new OnBoardAdapter(this, onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // Change the current position intimation
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));

                int pos = position + 1;

                previous_pos = pos;

                //Get Current Color
                int currentColor = getResources().getColor(buttonColor[position]);

                //Change Button Color
                LayerDrawable layerDrawable = (LayerDrawable) btn_get_started.getBackground();
                GradientDrawable shape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.drawable_button);
                shape.setColor(currentColor);

                //Change Go To Login Color
                to_login_activity.setTextColor(currentColor);

                //Change Dot Pager Color
                GradientDrawable gradientDrawable = (GradientDrawable) dots[position].getDrawable();
                gradientDrawable.setColor(currentColor);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, RegisterActivity.class));
                //finish();
            }
        });

        to_login_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
                //finish();
            }
        });

        setUiPageViewController();

    }

    // Load data into the viewpager

    public void loadData() {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3, R.string.ob_header4};
        int[] imageId = {R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3, R.drawable.onboarding4};

        for (int i = 0; i < imageId.length; i++) {
            OnBoardItem item = new OnBoardItem();
            item.setImageID(imageId[i]);
            item.setTitle(getResources().getString(header[i]));

            onBoardItems.add(item);
        }
    }

    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(12, 0, 12, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));
    }

    @Override
    public void onClick(View view) {
        if (view == to_login_activity) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


}
























