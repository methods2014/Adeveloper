package method.com.adeveloper.home.homefloor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chen on 2016/5/12.
 */
public class SubViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mList;

    public SubViewPagerAdapter(FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return  mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

}
