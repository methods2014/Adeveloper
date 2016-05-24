package method.com.adeveloper.home.entity;

import android.graphics.Bitmap;

import method.com.adeveloper.base.BaseEntity;
import method.com.adeveloper.utils.Constants;

/**
 * Created by chen on 2016/5/13.
 */
public class SubVP1Entity extends BaseEntity {
    public String desc;
    public Bitmap iconBitmap;
    public String url;

    public SubVP1Entity(String desc, Bitmap iconBitmap) {
        this.desc = desc;
        this.iconBitmap = iconBitmap;
        this.url = Constants.URL_DESIGN_DEFAULT;
    }

    public SubVP1Entity(String desc, Bitmap iconBitmap, String url) {
        this.desc = desc;
        this.iconBitmap = iconBitmap;
        this.url = url;
    }
}
