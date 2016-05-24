package method.com.adeveloper.home;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
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
import method.com.adeveloper.activities.WebViewActivity;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.adapter.MineAdapter;
import method.com.adeveloper.home.entity.MineMenuEntity;
import method.com.adeveloper.mine.FeedBackActivity;
import method.com.adeveloper.utils.Constants;
import method.com.adeveloper.utils.DataCleanManager;

/**
 * Created by chen on 2016/5/11.
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
public class MineFragment extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();

    ListView lv_content;
    List<MineMenuEntity> mineMenuEntityList;
    MineAdapter mineAdapter;
    MineMenuEntity clearCache;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        //view.setBackgroundColor(getResources().getColor(R.color.main_black_btn));
        TextView tv_mine = (TextView) view.findViewById(R.id.tv_mine);
        lv_content = (ListView) view.findViewById(R.id.lv_content);
        tv_mine.setBackgroundColor(getResources().getColor(R.color.main_red));
        lv_content.setBackgroundColor(getResources().getColor(R.color.main_background));
        mineMenuEntityList = getData();
        mineAdapter = new MineAdapter(getActivity(), mineMenuEntityList);
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
        MineMenuEntity feedback = new MineMenuEntity("意见反馈", null) {
            @Override
            public void launch() {
                FeedBackActivity.launch(getActivity(), 1000);
            }
        };
        MineMenuEntity currentVersion = new MineMenuEntity("当前版本", getVersion()) {
            @Override
            public void launch() {
                Log.i(TAG, "go to currentVersion page!");
            }
        };
        clearCache = new MineMenuEntity("清理缓存", getCacheSize()) {
            @Override
            public void launch() {
                showAlertDialog("", "清理缓存", "取消", "确定", true, true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cleanCache();
                    }
                });
            }
        };
        MineMenuEntity about = new MineMenuEntity("关于", null) {
            @Override
            public void launch() {
                WebViewActivity.launch(getActivity(), Constants.URL_MINE_ABOUT, "关于");
            }
        };
        List<MineMenuEntity> ret = new ArrayList<>();
        ret.add(feedback);
        ret.add(currentVersion);
        ret.add(clearCache);
        ret.add(about);
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

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    private String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return "V"+version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取缓存大小
     *
     * @return 缓存大小
     */
    private String getCacheSize() {
        String result = "0.00M";
        try {
            String cache = DataCleanManager.getTotalCacheSize(getActivity());
            if (!"0.0Byte".equals(cache) && !"0.00M".equals(cache)) {
                result = cache;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 清理缓存
     */
    private void cleanCache() {
        new AsyncTask<Integer, Integer, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Integer... integers) {
                DataCleanManager.cleanApplicationData(getActivity().getApplicationContext());
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearCache.subTitle = "0.00MB";
                mineAdapter.notifyDataSetChanged();
                showToast("清理完成");
            }
        }.execute(0);
    }
}
