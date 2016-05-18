package method.com.adeveloper.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.adapter.MineAdapter;
import method.com.adeveloper.home.entity.MineMenuEntity;

/**
 * Created by chen on 2016/5/11.
 */
public class MineFragment extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();

    ListView lv_content;
    List<MineMenuEntity> mineMenuEntityList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        //view.setBackgroundColor(getResources().getColor(R.color.main_black_btn));
        TextView tv_mine = (TextView) view.findViewById(R.id.tv_mine);
        lv_content = (ListView) view.findViewById(R.id.lv_content);
        tv_mine.setBackgroundColor(getResources().getColor(R.color.main_red));
        lv_content.setBackgroundColor(getResources().getColor(R.color.main_background));
        mineMenuEntityList = getData();
        MineAdapter mineAdapter = new MineAdapter(getActivity(), mineMenuEntityList);
        lv_content.setAdapter(mineAdapter);
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mineMenuEntityList.get(position).launch();
            }
        });
        initListViewHeight();
        return view;
    }

    private List<MineMenuEntity> getData(){
        MineMenuEntity about = new MineMenuEntity("关于", null) {
            @Override
            public void launch() {
                Log.i(TAG, "go to about page!");
            }
        };
        MineMenuEntity feedback = new MineMenuEntity("意见反馈", null) {
            @Override
            public void launch() {
                Log.i(TAG, "go to feedback page!");
            }
        };
        MineMenuEntity currentVersion = new MineMenuEntity("当前版本", null) {
            @Override
            public void launch() {
                Log.i(TAG, "go to currentVersion page!");
            }
        };
        MineMenuEntity clearCache = new MineMenuEntity("清理缓存", null) {
            @Override
            public void launch() {
                Log.i(TAG, "go to clearCache page!");
            }
        };
        List<MineMenuEntity> ret = new ArrayList<>();
        ret.add(about);
        ret.add(feedback);
        ret.add(currentVersion);
        ret.add(clearCache);
        return ret;
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
        //((ViewGroup.MarginLayoutParams)params).setMargins(10, 10, 10, 10);
        lv_content.setLayoutParams(params);
    }


}
