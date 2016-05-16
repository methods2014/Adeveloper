package method.com.adeveloper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by chen on 2016/5/16.
 */
public class ScrollViewCanListen extends ScrollView {

    public ScrollViewCanListen(Context context) {
        super(context);
    }

    public ScrollViewCanListen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewCanListen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private ScrollViewListener scrollViewListener = null;

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
