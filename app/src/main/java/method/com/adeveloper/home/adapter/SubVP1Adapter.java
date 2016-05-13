package method.com.adeveloper.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.home.entity.SubVP1Entity;

/**
 * Created by chen on 2016/5/13.
 */
public class SubVP1Adapter extends BaseAdapter {

    List<SubVP1Entity> mList;
    Context mContext;

    public SubVP1Adapter(Context context, List<SubVP1Entity> list){
        this.mList = list;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null == mList ? null : mList.get(position);
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
            convertView = View.inflate(mContext, R.layout.item_subvp1, null);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SubVP1Entity entity = (SubVP1Entity) getItem(position);
        holder.iv_icon.setImageResource(entity.iconResId);
        holder.tv_desc.setText(entity.desc);
        return convertView;
    }

    class ViewHolder{
        ImageView iv_icon;
        TextView tv_desc;
    }

}
