package method.com.adeveloper.view.autoscroll;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.BuildConfig;
import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;

/**
 * Created by wangs on 16/1/18 14:30.
 * 头部广告位
 * 单独封装出来
 */
public class AutoScrollVpFragment extends BaseFragment {
    private static String TAG = AutoScrollVpFragment.class.getSimpleName();
    private AutoScrollViewPagerWithIndicator autoScrollViewPager;
    private ImagePagerAdapter adViewPagerAdapter;
    private List<Integer> imageIdList;

    public static void showAtLocation(FragmentManager fm, int conRes, boolean isAdd) {
        AutoScrollVpFragment scrollVpFragment = new AutoScrollVpFragment();
        FragmentTransaction ft = fm.beginTransaction();
        if (isAdd) {
            ft.add(conRes, scrollVpFragment, TAG).addToBackStack(null).commit();
        } else {
            ft.replace(conRes, scrollVpFragment, TAG).addToBackStack(null).commit();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_head_viewpager, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autoScrollViewPager = (AutoScrollViewPagerWithIndicator) view.findViewById(R.id.header_view_pager);
        imageIdList = new ArrayList<>();
        imageIdList.add(R.mipmap.banner_0);
        imageIdList.add(R.mipmap.banner_1);
        imageIdList.add(R.mipmap.banner_2);
        imageIdList.add(R.mipmap.banner_3);
        adViewPagerAdapter = new ImagePagerAdapter(getActivity(), imageIdList);
        AutoScrollViewPager viewPager = autoScrollViewPager.getViewPager();
        autoScrollViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                autoScrollViewPager.selectIndicator(position % (imageIdList.size()));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setInterval(2000);
        viewPager.setAutoScrollDurationFactor(3);
        autoScrollViewPager.setAdapter(adViewPagerAdapter);
        showAdViewPager();
    }

    @Override
    public void onResume() {
        super.onResume();
        autoScroll(true);

        if (BuildConfig.DEBUG) Log.d(TAG, "onResume");
    }

    private void autoScroll(boolean autoScroll) {
        AutoScrollViewPager viewPager = autoScrollViewPager.getViewPager();
        if (viewPager != null) {
            if (autoScroll) {
                viewPager.startAutoScroll(500);
            } else {
                viewPager.stopAutoScroll();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        autoScroll(true);
        if (BuildConfig.DEBUG) Log.d(TAG, "onPause");
    }

    private void showAdViewPager() {
        if (BuildConfig.DEBUG) Log.d(TAG, "showAdViewPager start");
        if (!imageIdList.isEmpty()) {
            adViewPagerAdapter.setInfiniteLoop(true);
            autoScrollViewPager.initIndicator(imageIdList.size());
            AutoScrollViewPager viewPager = autoScrollViewPager.getViewPager();
            adViewPagerAdapter.notifyDataSetChanged();
            viewPager.stopAutoScroll();
            viewPager.startAutoScroll(500);

        }
        if (BuildConfig.DEBUG) Log.d(TAG, "showAdViewPager end");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
