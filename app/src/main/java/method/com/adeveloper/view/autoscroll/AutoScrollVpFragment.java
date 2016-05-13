package method.com.adeveloper.view.autoscroll;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jd.paipai.BuildConfig;
import com.jd.paipai.R;
import com.jd.paipai.app.BaseFragment;
import com.jd.paipai.entities.HomeBannerEntity;
import com.jd.paipai.module.home.action.HomeBannerAction;
import com.jd.paipai.module.home.action.OnDataEmptyCallback;
import com.jd.paipai.module.home.adapter.ADViewPagerAdapter;
import com.jd.paipai.utils.ADUtils;
import com.jd.paipai.utils.DateUtils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by wangs on 16/1/18 14:30.
 * 头部广告位
 * 单独封装出来
 */
public class AutoScrollVpFragment extends BaseFragment {
    private static String TAG = AutoScrollVpFragment.class.getSimpleName();
    private AutoScrollViewPagerWithIndicator autoScrollViewPager;
    private ADViewPagerAdapter adViewPagerAdapter;
    private List<HomeBannerEntity> adList = new ArrayList<>();

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
        EventBus.getDefault().register(this);
        autoScrollViewPager = (AutoScrollViewPagerWithIndicator) view.findViewById(R.id.header_view_pager);
        adViewPagerAdapter = new ADViewPagerAdapter(getActivity(), adList);
        AutoScrollViewPager viewPager = autoScrollViewPager.getViewPager();
        autoScrollViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                autoScrollViewPager.selectIndicator(position % (adList.size()));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setInterval(2000);
        viewPager.setAutoScrollDurationFactor(3);
        autoScrollViewPager.setAdapter(adViewPagerAdapter);

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

    public void onEventMainThread(HomeBannerAction action) {

        if (BuildConfig.DEBUG) Log.d(TAG, "onEventMainThread start");
        List<HomeBannerEntity> tempAdList = action.getEntityList();
        OnDataEmptyCallback emptyCallback = action.getCallback();

        if (tempAdList.isEmpty()) {
            return;
        }

        List<HomeBannerEntity> retainList = new ArrayList<>();
        for (HomeBannerEntity domain : tempAdList) {
            if (DateUtils.todayAmongDays(domain.getStartTime(), domain.getEndTime())) {
                if (ADUtils.getInstance(getActivity()).ADValidate(domain)) {
                    retainList.add(domain);
                }
            }
        }
        if (retainList.isEmpty()) {

            if (emptyCallback != null) {
                emptyCallback.onDataEmpty(true);
                AutoScrollViewPager viewPager = autoScrollViewPager.getViewPager();
                if (viewPager != null) {
                    viewPager.stopAutoScroll();
                }
            }
            return;
        } else {
            if (emptyCallback != null) {
                emptyCallback.onDataEmpty(false);
            }
        }

        /**
         * 当存在不相同的元素,继续执行
         */
        if (CollectionUtils.isEqualCollection(retainList, adList)) {
            return;
        }
        adList.clear();
        adList.addAll(retainList);
        showAdViewPager();

        if (BuildConfig.DEBUG) Log.d(TAG, "onEventMainThread end");
    }


    private void showAdViewPager() {
        if (BuildConfig.DEBUG) Log.d(TAG, "showAdViewPager start");
        if (!adList.isEmpty()) {
            adViewPagerAdapter.setInfiniteLoop(true);
            autoScrollViewPager.initIndicator(adList.size());
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
        EventBus.getDefault().unregister(this);
    }

}
