package method.com.adeveloper.view.autoscroll;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import method.com.adeveloper.BuildConfig;
import method.com.adeveloper.R;
import method.com.adeveloper.utils.AndroidUtils;


/**
 * Created by mac on 15/7/31.
 */
public class AutoScrollViewPagerWithIndicator extends FrameLayout {
    private static final String TAG = AutoScrollViewPagerWithIndicator.class.getSimpleName();


    public AutoScrollViewPagerWithIndicator(Context paramContext) {
        this(paramContext, null);
    }

    LinearLayout indicatorLayout;
    AutoScrollViewPager viewPager;

    public AutoScrollViewPagerWithIndicator(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);


    }

    public AutoScrollViewPagerWithIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setLongClickable(true);
        removeAllViews();
        viewPager = new AutoScrollViewPager(context, attrs);
        addView(viewPager);
        indicatorLayout = new LinearLayout(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, AndroidUtils.dip2px(context, 20));
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        params.setMargins(0, 0, 0, AndroidUtils.dip2px(context, 9));
        indicatorLayout.setLayoutParams(params);
//        indicatorLayout.setBackgroundResource(R.drawable.viewpage_header_ad_container_shape);
        indicatorLayout.setVisibility(View.INVISIBLE);
        addView(indicatorLayout);
    }

    public AutoScrollViewPager getViewPager() {
        return viewPager;
    }

    public void setAdapter(PagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        viewPager.setOnPageChangeListener(listener);
    }

    public LinearLayout getIndicatorLayout() {
        return indicatorLayout;
    }

    public void initIndicator(int count) {
        this.initIndicator(count, 0);

    }

    public void initIndicator(int count, int index) {
        if (BuildConfig.DEBUG) Log.d(TAG, "initIndicator start");
        if (count == 0) {
            return;
        }
        if (count <= index) {
            index = 0;
        }
        indicatorLayout.setVisibility(VISIBLE);
        indicatorLayout.removeAllViews();
        for (int i = 0; i < count; i++) {

            ImageView ivDot = new ImageView(getContext());
            LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(AndroidUtils.dip2px(getContext(), 9), AndroidUtils.dip2px(getContext(), 9));
            ivParams.setMargins(AndroidUtils.dip2px(getContext(), 2.5f), 0, AndroidUtils.dip2px(getContext(), 2.5f), 0);
            ivParams.gravity = Gravity.CENTER;
            ivDot.setLayoutParams(ivParams);
            if (index == i) {
//                ivDot.setImageResource(R.drawable.icon_dots_press);
                ivDot.setImageResource(R.drawable.dot_white);
            } else {
//                ivDot.setImageResource(R.drawable.icon_dots_normal);
                ivDot.setImageResource(R.drawable.dot_gray_c6c6c6);
            }
            indicatorLayout.addView(ivDot);
        }
        if (BuildConfig.DEBUG) Log.d(TAG, "initIndicator end");
    }

    public void selectIndicator(int pos) {
        if (indicatorLayout != null && indicatorLayout.getChildCount() > pos) {
            clearIndicator();
            chooseIndicator(pos);

        }

    }

    private void clearIndicator() {
        for (int i = 0; i < indicatorLayout.getChildCount(); i++) {
            View view = indicatorLayout.getChildAt(i);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(R.drawable.dot_gray_c6c6c6);
            }
        }
        invalidate();

    }

    private void chooseIndicator(int pos) {
        View view = indicatorLayout.getChildAt(pos);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(R.drawable.dot_white);
        }
        invalidate();
    }

    public int getScreenWidth() {
        Context context = getContext();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        return width;
    }


    private float mDownX, mDownY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mDownX = ev.getX();
                mDownY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(ev.getX() - mDownX) > Math.abs(ev.getY() - mDownY)) {
//                    Log.i(TAG, "X>Y");
                    getParent().requestDisallowInterceptTouchEvent(true);

                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
//                    Log.i(TAG, "X<Y");
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    private int stateToSave;

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.stateToSave = this.stateToSave;
        return savedState;
    }

//    : Wrong state class, expecting View State but received class android.support.v4.view.ViewPager$SavedState instead.
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            if (!(state instanceof SavedState)) {
                super.onRestoreInstanceState(state);
                return;
            }

            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.stateToSave = ss.stateToSave;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    static class SavedState extends BaseSavedState {
        int stateToSave;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.stateToSave = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.stateToSave);
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeAllViews();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
