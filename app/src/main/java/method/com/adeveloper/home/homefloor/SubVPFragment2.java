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
import java.util.Properties;

import method.com.adeveloper.R;
import method.com.adeveloper.activities.WebViewActivity;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.adapter.SubVP1Adapter;
import method.com.adeveloper.home.entity.SubVP1Entity;
import method.com.adeveloper.utils.ArticleUtils;
import method.com.adeveloper.utils.AssetsUtils;
import method.com.adeveloper.utils.Constants;

/**
 * Created by chen on 2016/5/12.
 * 首页下方三个tab页
 */
public class SubVPFragment2 extends BaseFragment{

    public static SubVPFragment2 getInstance(){
        return new SubVPFragment2();
    }
    ListView lv_content;

    List<SubVP1Entity> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_sub_vp2, container, false);
        lv_content = (ListView) view.findViewById(R.id.lv_content);
        list = initData(Constants.DIR_UML);
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

    private List<SubVP1Entity> initData(String parentDir){
        Properties prop = AssetsUtils.loadProperties(getActivity(), parentDir + Constants.PROPERTIES_FILE_SUFFIX);
        String itemValue = prop.getProperty(Constants.PROPERTIES_ITEM); //design_factory_1,design_factory_2,design_factory_3,design_factory_4
        List<SubVP1Entity> list = new ArrayList<>();
        String[] items = itemValue.split(Constants.PROPERTIES_ITEM_SEPARATOR);
        for(String item : items){ //item is design_factory_1
            list.add(new SubVP1Entity(prop.getProperty(item), AssetsUtils.getBitmapFromAssets(getActivity(), ArticleUtils.getIconPath(item, parentDir)), ArticleUtils.getHtmlPath(item, parentDir)));
        }
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
