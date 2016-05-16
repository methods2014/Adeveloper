package method.com.adeveloper.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;

/**
 * Created by chen on 2016/5/11.
 */
public class MineFragment extends BaseFragment {
    private String TAG = this.getClass().getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null, false);
        Log.i(TAG, "|" + view.getWidth() + "|" + view.getHeight());
        /*Log.i(TAG, "11|" + container.getWidth() + "|" + container.getHeight());
        container.measure(0, 0);
        Log.i(TAG, "22|" + container.getWidth() + "|" + container.getHeight());*/
        return view;
    }


}
