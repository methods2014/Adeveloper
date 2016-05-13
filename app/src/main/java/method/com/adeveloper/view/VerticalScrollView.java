package method.com.adeveloper.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import method.com.adeveloper.R;
import method.com.adeveloper.home.entity.HomeTreasureMessageEntity;

/**
 * 竖直滚动的广告条
 */
public class VerticalScrollView extends FrameLayout {

    private static final String TAG = VerticalScrollView.class.getSimpleName();
    private Context mContext;
    private ViewSwitcher switcher;

    private TextView text_header;
    private LinearLayout ll_footer;
    private TextView text_footer_tag;
    private TextView text_footer;

    private List<HomeTreasureMessageEntity> dataList;
    private int size;
    private int index = 0;
    private boolean stopScrollWhenTouch = false;
    private boolean isAutoScroll = true;
    private boolean isStopByTouch = false;
    private static final int SCROLL_NEXT_ACTION = 0;
    //动画时间
    public int animDuration = 1000;
    //滚动间隔
    public int scrollDuration = 5000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCROLL_NEXT_ACTION:
                    next();
                    handler.sendEmptyMessageDelayed(SCROLL_NEXT_ACTION, scrollDuration);
                    break;
            }
        }
    };

    public VerticalScrollView(Context context) {
        super(context);
        initView(context);
    }


    public VerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public VerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
        mContext = context;
        View view = View.inflate(mContext, R.layout.view_verticalbanner, null);
        addView(view);
        switcher = (ViewSwitcher) findViewById(R.id.switcher);
        Log.d(TAG, "initView: " + (switcher == null));
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return View.inflate(mContext, R.layout.view_verticalbanner_item, null);
            }
        });
        Animation in = new TranslateAnimation(0, 0, 150, 0);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, -150);
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);
        dataList = new ArrayList<>();
        initData(dataList);
        if (dataList != null) {
            size = dataList.size();
        }
        showInitView();
        startAutoScroll();
    }

    private void initData(List<HomeTreasureMessageEntity> dataList){
        HomeTreasureMessageEntity entity1 = new HomeTreasureMessageEntity();
        entity1.setDbdSpecTag("新闻");
        entity1.setDbdSpecTitle("android 7.0 将于今年发布!");
        entity1.setDbdSpecslogin("感受黑客技术");
        HomeTreasureMessageEntity entity2 = new HomeTreasureMessageEntity();
        entity2.setDbdSpecTag("新鲜事");
        entity2.setDbdSpecslogin("android developer have updater");
        entity2.setDbdSpecTitle("fast more fast");
        HomeTreasureMessageEntity entity3 = new HomeTreasureMessageEntity();
        entity3.setDbdSpecTag("信息速递");
        entity3.setDbdSpecTitle("百度魏则西事件背后, develper应该了解的三件事");
        entity3.setDbdSpecslogin("公关有多么的重要");
        dataList.add(entity1);
        dataList.add(entity2);
        dataList.add(entity3);
    }

    /**
     * 初始化第一次的数据
     */

    private void showInitView() {
        initItemView(switcher.getCurrentView());
    }

    private void startAutoScroll() {
        stopAutoScroll();
        next();
        handler.sendEmptyMessageDelayed(SCROLL_NEXT_ACTION, scrollDuration);
    }

    private void stopAutoScroll() {
        handler.removeMessages(SCROLL_NEXT_ACTION);

    }

    public void next() {
        if (dataList != null && !dataList.isEmpty()) {
            if (index < size - 1) {
                index++;
            } else {
                index = 0;
            }
            initItemView(switcher.getNextView());
            switcher.showNext();
        }
    }

    private void initItemView(View view) {
        if (dataList.size() < index) {
            return;
        }
        final HomeTreasureMessageEntity entity = dataList.get(index);
        text_header = (TextView) view.findViewById(R.id.text_header);
        text_header.setText(entity.getDbdSpecTitle());
        ll_footer = (LinearLayout) view.findViewById(R.id.ll_footer);
        text_footer_tag = (TextView) view.findViewById(R.id.text_footer_tag);
        text_footer_tag.setText(entity.getDbdSpecTag());
        text_footer = (TextView) view.findViewById(R.id.text_footer);
        text_footer.setText(entity.getDbdSpecslogin());

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //HtmlActivity.launch((Activity) mContext, entity.getDbdSpecUrl(), entity.getDbdSpecTitle(), 0);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //加入触摸停止滚动功能
        if (stopScrollWhenTouch) {
            if ((action == MotionEvent.ACTION_DOWN) && isAutoScroll) {
                isStopByTouch = true;
                stopAutoScroll();
            } else if ((action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) && isStopByTouch) {
                startAutoScroll();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isStopScrollWhenTouch() {
        return stopScrollWhenTouch;
    }

    public void setStopScrollWhenTouch(boolean stopScrollWhenTouch) {
        this.stopScrollWhenTouch = stopScrollWhenTouch;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoScroll();
    }
}
