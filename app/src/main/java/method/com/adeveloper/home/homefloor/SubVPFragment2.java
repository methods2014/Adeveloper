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
 */
public class SubVPFragment2 extends BaseFragment{

    public static SubVPFragment2 getInstance(){
        return new SubVPFragment2();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sub_vp1, container, false);
        return view;
    }
}
