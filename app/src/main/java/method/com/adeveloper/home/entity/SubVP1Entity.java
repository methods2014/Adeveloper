package method.com.adeveloper.home.entity;

import method.com.adeveloper.base.BaseEntity;

/**
 * Created by chen on 2016/5/13.
 */
public class SubVP1Entity extends BaseEntity {
    public String desc;
    public int iconResId;
    public SubVP1Entity(String desc, int iconResId){
        this.desc = desc;
        this.iconResId = iconResId;
    }
}
