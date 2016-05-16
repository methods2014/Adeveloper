package method.com.adeveloper.home.homefloor;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseFragment;
import method.com.adeveloper.home.ResizeViewPager;
import method.com.adeveloper.home.event.OnPageChangeEvent;

/**
 * Created by chen on 2016/5/12.
 * 首页下面那个viewPager切换页
 * ViewPager自适应，要重新实现viewPager中的onMeasure()方法
 * 其中子类LinerLayout也要有具体高度
 *
 */
public class HomeViewPagerFragment extends BaseFragment{

    private ResizeViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1,textView2,textView3;
    private List<Fragment> fragmentList;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    SubVPFragment1 fragment1;
    SubVPFragment2 fragment2;
    SubVPFragment3 fragment3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_home_bottom_viewpager, container, false);
        initViewPager(view);
        initTextView(view);
        initImageView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initViewPager(View view) {
        viewPager= (ResizeViewPager) view.findViewById(R.id.vPager);
        fragment1 = SubVPFragment1.getInstance();
        fragment2 = SubVPFragment2.getInstance();
        fragment3 = SubVPFragment3.getInstance();

        fragmentList = new ArrayList<>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        FragmentManager fm = getChildFragmentManager();
        //FragmentManager fm = getActivity().getSupportFragmentManager();
        viewPager.setAdapter(new SubViewPagerAdapter(fm, fragmentList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    /**
     *  初始化头标
     */
    private void initTextView(View view) {
        textView1 = (TextView) view.findViewById(R.id.text1);
        textView2 = (TextView) view.findViewById(R.id.text2);
        textView3 = (TextView) view.findViewById(R.id.text3);

        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 初始化动画
     */
    private void initImageView(View view) {
        imageView= (ImageView) view.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.horizontal_line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int arg0) {
            Animation animation = new TranslateAnimation(bmpW*currIndex, bmpW*arg0, 0, 0);
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);
            EventBus.getDefault().post(new OnPageChangeEvent(bmpW*currIndex, bmpW*arg0));
        }

    }
}
