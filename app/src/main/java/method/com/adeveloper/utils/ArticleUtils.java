package method.com.adeveloper.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by chen on 2016/5/24.
 */
public class ArticleUtils {

    private static final String BASE_PAth = "file:///android_asset/";

    private static final String SUFFIX_HMTL = ".html";

    private static final String PREFIX_ICON = "icon/";
    private static final String SUFFIX_PNG = ".png";

    /**
     * 获得html路径
     *
     * @param fileName   eg: design_factory_1
     * @param parentPath eg: design_lovelion/
     * @return "file:///android_asset/design_lovelion/design_factory_1.html";
     */
    public static String getHtmlPath(String fileName, String parentPath) {
        return getPath(fileName, BASE_PAth + parentPath, SUFFIX_HMTL);
    }

    public static String getIconPath(String fileName, String parentPath) {
        return getPath(fileName, parentPath + PREFIX_ICON, SUFFIX_PNG);
    }

    public static String getPath(String fileName, String parentPath, String suffix) {
        return parentPath + fileName + suffix;
    }

    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            FileInputStream s = new FileInputStream(filePath);
            properties.load(s);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
