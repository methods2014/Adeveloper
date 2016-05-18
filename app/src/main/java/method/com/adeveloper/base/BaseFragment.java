package method.com.adeveloper.base;


import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by chen on 2016/5/11.
 */
public class BaseFragment extends Fragment {

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }
}
