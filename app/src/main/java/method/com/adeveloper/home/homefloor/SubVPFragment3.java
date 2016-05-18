package method.com.adeveloper.home.homefloor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;

/**
 * Created by chen on 2016/5/12.
 * 首页下方三个tab页
 *
 * 检查更新
 * 清理缓存
 * 意见反馈
 * 关于
 *  优化
 * (分享)
 * (浏览足迹)
 * (主题、夜间模式)
 * (地理位置)
 */
public class SubVPFragment3 extends BaseFragment{

    public static SubVPFragment3 getInstance(){
        return new SubVPFragment3();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sub_vp3, container, false);
        return view;
    }
}
