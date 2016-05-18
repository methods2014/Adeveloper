package method.com.adeveloper.home.entity;

import method.com.adeveloper.base.BaseEntity;

/**
 * Created by chen on 2016/5/17.
 */
public abstract class MineMenuEntity  extends BaseEntity {

    public String title;
    public String subTitle;
    public MineMenuEntity(String title, String subTitle){
        this.title = title;
        this.subTitle = subTitle;
    }
    //启动回调
    public abstract void launch();
}
