package method.com.adeveloper.home.event;

/**
 * Created by chen on 2016/5/16.
 * eventbus 用 当首页下方的viewPager切换的时候，同事处理首页顶部的tab切换
 */
public class OnPageChangeEvent {

    public float fromXDelta;
    public float toXDelta;
    public OnPageChangeEvent(float fromXDelta, float toXDelta){
        this.fromXDelta = fromXDelta;
        this.toXDelta = toXDelta;
    }
}
