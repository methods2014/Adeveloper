package method.com.adeveloper.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by chen on 2016/5/13.
 */
public class ResizeViewPager extends ViewPager{
    public ResizeViewPager(Context context) {
        super(context);
    }

    public ResizeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST;

        final View tab = getChildAt(0);
        int width = getMeasuredWidth();
        int tabHeight = tab.getMeasuredHeight();

        if (wrapHeight) {
            // Keep the current measured width.
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }

        int fragmentHeight = measureFragment(((Fragment) getAdapter().instantiateItem(this, getCurrentItem())).getView());
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(tabHeight + fragmentHeight + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()), MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int measureFragment(View view) {
        if (view == null)
            return 0;

        view.measure(0, 0);
        return view.getMeasuredHeight();
    }
}
