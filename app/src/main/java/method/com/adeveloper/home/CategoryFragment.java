package method.com.adeveloper.home;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseActivity;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.adapter.SubVP1Adapter;
import method.com.adeveloper.home.entity.CategoryEntity;
import method.com.adeveloper.home.entity.SubVP1Entity;
import method.com.adeveloper.utils.AndroidUtils;

/**
 * Created by chen on 2016/5/11.
 */
public class CategoryFragment extends BaseFragment {

    ListView left_list;
    ListView right_List;

    BaseActivity thisActivity;

    private ArrayList<CategoryEntity> leftTextList = new ArrayList<CategoryEntity>();

    @Override
    public void onAttach(Context context) {
        thisActivity = (BaseActivity) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        view.findViewById(R.id.tv_title).setBackgroundColor(getResources().getColor(R.color.main_red));
        left_list = (ListView) view.findViewById(R.id.left_list);
        right_List = (ListView) view.findViewById(R.id.right_List);
        leftTextList.add(new CategoryEntity("设计模式"));
        leftTextList.add(new CategoryEntity("UML"));
        leftTextList.add(new CategoryEntity("分层架构"));
        LeftListAdapter leftAdapter = new LeftListAdapter();
        left_list.setAdapter(leftAdapter);
        right_List.setAdapter(new SubVP1Adapter(getActivity(), initData()));
        initListViewHeight(left_list);
        getPanelHeight(right_List);
        return view;
    }

    private List<SubVP1Entity> initData(){
        String desc1 = "android 中MVC模式的运用方式";
        String desc2 = "MVP设计架构";
        String desc3 = "MVVM是Model-View-ViewModel的简写。";
        List<SubVP1Entity> list = new ArrayList<>();
        list.add(new SubVP1Entity(desc1, R.mipmap.mvc));
        list.add(new SubVP1Entity(desc2, R.mipmap.mvp));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        list.add(new SubVP1Entity(desc3, R.mipmap.mvvm));
        return list;
    }

    private void getPanelHeight(ListView listView){
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int height_full = display.getHeight();
        Rect frame = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        //总高度 - 状态栏高度 - 标题栏高度 - 菜单栏高度 - 10dp
        int panelHeight = height_full - statusBarHeight - AndroidUtils.dip2px(getActivity(), 55) - AndroidUtils.dip2px(getActivity(), 48) - AndroidUtils.dip2px(getActivity(), 10);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = panelHeight;
        //((ViewGroup.MarginLayoutParams)params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);

    }

    //一级分类的adapter
    class LeftListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return leftTextList.size();
        }

        @Override
        public Object getItem(int position) {
            return leftTextList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LeftListViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(thisActivity).inflate(R.layout.item_category_left, null);
                viewHolder = new LeftListViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (LeftListViewHolder) convertView.getTag();
            }
           /*     if (position == currentItem) {
                    lastClickView = convertView;
                    convertView.setBackgroundResource(R.drawable.category_new_left_facous);
                    viewHolder.textView.setTextColor(getFragmentTextColor(R.color.category_new_red_font));
                } else {
                    convertView.setBackgroundResource(R.drawable.category_new_left_normal);
                    viewHolder.textView.setTextColor(getFragmentTextColor(R.color.category_new__dark_font));
                }*/
            convertView.setBackgroundResource(R.drawable.category_new_left_normal);
            viewHolder.textView.setTextColor(getResources().getColor(R.color.category_new_dark_font));
            viewHolder.textView.setText(leftTextList.get(position).title);
            return convertView;
        }

    }

    class LeftListViewHolder {
        public TextView textView;
    }

    private void initListViewHeight(ListView listView){
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //((ViewGroup.MarginLayoutParams)params).setMargins(10, 10, 10, 10);
        listView.setLayoutParams(params);
    }

}
