package method.com.adeveloper.home.homefloor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.activities.WebViewActivity;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.adapter.SubVP1Adapter;
import method.com.adeveloper.home.entity.SubVP1Entity;

/**
 * Created by chen on 2016/5/12.
 * 首页下方三个tab页
 */
public class SubVPFragment1 extends BaseFragment{

    public static SubVPFragment1 getInstance(){
        return new SubVPFragment1();
    }
    ListView lv_content;

    List<SubVP1Entity> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sub_vp1, container, false);
        lv_content = (ListView) view.findViewById(R.id.lv_content);
        list = initData();
        lv_content.setAdapter(new SubVP1Adapter(getActivity(), list));
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WebViewActivity.launch(getActivity(), list.get(position).url, "详情页");
            }
        });
        initListViewHeight();
        return view;
    }

    private List<SubVP1Entity> initData(){
        String desc1 = "android 中MVC模式的运用方式";
        String desc2 = "MVP设计架构";
        String desc3 = "MVVM是Model-View-ViewModel的简写。";
        List<SubVP1Entity> list = new ArrayList<>();
/*        list.add(new SubVP1Entity(desc1, R.mipmap.mvc, Constants.URL_DESIGN_COMMAND));
        list.add(new SubVP1Entity(desc2, R.mipmap.mvp, Constants.URL_DESIGN_FACTORY));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm, Constants.URL_DESIGN_CHAINOFRESPONSIBILITY_1));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));*/
        return list;
    }

    private void initListViewHeight(){
        ListAdapter listAdapter = lv_content.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lv_content);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lv_content.getLayoutParams();
        params.height = totalHeight + (lv_content.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams)params).setMargins(10, 10, 10, 10);
        lv_content.setLayoutParams(params);
    }
}
