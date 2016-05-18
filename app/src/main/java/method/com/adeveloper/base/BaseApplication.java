package method.com.adeveloper.base;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by chen on 2016/5/17.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initStatistics();
    }

    private void initStatistics(){
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //首先，需要在程序入口处，调用 MobclickAgent.openActivityDurationTrack(false)  禁止默认的页面统计方式，这样将不会再自动统计Activity。
        MobclickAgent.openActivityDurationTrack(false);
    }
}
