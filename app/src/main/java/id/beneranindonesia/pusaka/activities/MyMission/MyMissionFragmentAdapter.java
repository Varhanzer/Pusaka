package id.beneranindonesia.pusaka.activities.MyMission;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyMissionFragmentAdapter extends FragmentStatePagerAdapter {

    int numberOfTabs;

    public MyMissionFragmentAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new OnGoingMissionFragment();
            case 1: return new CompletedMissionFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}
