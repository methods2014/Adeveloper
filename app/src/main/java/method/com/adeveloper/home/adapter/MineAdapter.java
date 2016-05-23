package method.com.adeveloper.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.home.entity.MineMenuEntity;
import method.com.adeveloper.utils.StringUtils;

/**
 * Created by chen on 2016/5/17.
 */
public class MineAdapter extends BaseAdapter {

    List<MineMenuEntity> mList;

    Context mContext;

    public MineAdapter(Context context, List<MineMenuEntity> list) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_mine_menu, null);
            holder.iv_right = (ImageView) convertView.findViewById(R.id.iv_right);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_sub_title = (TextView) convertView.findViewById(R.id.tv_sub_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MineMenuEntity entity = (MineMenuEntity) getItem(position);
        holder.tv_title.setText(entity.title);
        if(StringUtils.isNotBlank(entity.subTitle)) {
            holder.tv_sub_title.setText(entity.subTitle);
            holder.tv_sub_title.setVisibility(View.VISIBLE);
            holder.iv_right.setVisibility(View.GONE);
        }else {
            holder.tv_sub_title.setVisibility(View.GONE);
            holder.iv_right.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    class ViewHolder{
        ImageView iv_right;
        TextView tv_title;
        TextView tv_sub_title;
    }
}
