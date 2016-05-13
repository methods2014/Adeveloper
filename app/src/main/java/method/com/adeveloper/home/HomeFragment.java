package method.com.adeveloper.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.view.autoscroll.AutoScrollVpFragment;

/**
 * Created by chen on 2016/5/11.
 */
public class HomeFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        initBanner(view);
        return view;
    }

    /**
     * 顶部广告图
     * @param view
     */
    private void initBanner(View view){
        View viewPagerContainer = view.findViewById(R.id.fl_autoscrollvp_container);
        AutoScrollVpFragment.showAtLocation(getChildFragmentManager(), viewPagerContainer.getId(), false);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
