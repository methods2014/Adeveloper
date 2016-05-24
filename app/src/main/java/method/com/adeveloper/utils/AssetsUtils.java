package method.com.adeveloper.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chen on 2016/5/24.
 */
public class AssetsUtils {


    /**
     * 从Assets中读取图片
     * bgimg0 = getImageFromAssetsFile("Cat_Blink/cat_blink0000.png");
     * AssetsUtils.getBitmapFromAssets(mContext, "")
     */
    public static Bitmap getBitmapFromAssets(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Properties loadProperties(Context context, String filePath) {
        Properties properties = new Properties();
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(filePath);
            properties.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
