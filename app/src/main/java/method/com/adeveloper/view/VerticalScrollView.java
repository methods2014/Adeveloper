package method.com.adeveloper.view;

import android.app.Activity;
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

import com.jd.paipai.R;
import com.jd.paipai.entities.HomeTreasureMessageEntity;
import com.jd.paipai.module.home.action.HomeTreasureMessageAction;
import com.jd.paipai.module.home.action.OnDataEmptyCallback;
import com.jd.paipai.module.launch.HtmlActivity;
import com.jd.paipai.utils.DateUtils;
import com.jd.paipai.utils.StatisticsUtils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by wangs on 16/1/20 11:39.
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
    //    private static final int SCROLL_STOP_ACTION = 1;
    //动画时间
    public int animDuration = 1000;
    //滚动间隔
    public int scrollDuration = 5000;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            Log.d("VerticalScrollView", "handleMessage: " + msg.what);
            switch (msg.what) {
                case SCROLL_NEXT_ACTION:
                    next();
                    handler.sendEmptyMessageDelayed(SCROLL_NEXT_ACTION, scrollDuration);
                    break;
//                case SCROLL_STOP_ACTION:
//                    handler.removeMessages(SCROLL_NEXT_ACTION);
//                    break;

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

        EventBus.getDefault().register(this);
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


    }



    public void onEventMainThread(HomeTreasureMessageAction action) {

        List<HomeTreasureMessageEntity> list = action.getEntityList();
        List<HomeTreasureMessageEntity> tempList = new ArrayList<>();
        OnDataEmptyCallback callback = action.getCallback();
        Log.d(TAG, "setData: " + list);

        if (dataList == null) {
            dataList = new ArrayList<>();
        }


        for (HomeTreasureMessageEntity entity : list) {
            if (DateUtils.todayAmongDays(entity.getStartTime(), entity.getEndTime())) {
                try {
                    if (entity.isAdValidate()) {
                        tempList.add(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        if (dataList.isEmpty() && tempList.isEmpty()) {
            callback.onDataEmpty(true);
            return;
        } else {
            callback.onDataEmpty(false);
        }
        if (CollectionUtils.isEqualCollection(dataList, tempList)) {
            return;
        } else {
            dataList.clear();
            dataList.addAll(tempList);
        }

        if (dataList != null) {
            size = dataList.size();
        }

        showInitView();

        startAutoScroll();

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
                StatisticsUtils.sendClickData(entity.getClickTag());
                HtmlActivity.launch((Activity) mContext, entity.getDbdSpecUrl(), entity.getDbdSpecTitle(), 0);
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
        EventBus.getDefault().unregister(this);

    }
}
