package method.com.adeveloper.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import method.com.adeveloper.R;


/**
 * Created by liuxuegang1 on 2016/1/30.
 */
public class AlertDialogUtils {

    static boolean isDisappearForBack;
    /**
     * 显示对话框
     *
     * @param title              标题
     * @param message            内容
     * @param cancelText         取消按钮文本
     * @param confirmText        确定按钮文本
     * @param cancelTouchOutside 是否点击外侧区域取消Dialog
     * @param isDisappearForBack 是否点击回退键取消Dialog
     * @param confirmListener    确认按钮点击监听
     * @param cancelListener     取消按钮点击监听
     */
    public static void showAlertDialog(String title, String message, String cancelText, String confirmText,
                                       boolean cancelTouchOutside, boolean isDisappearForBack, View.OnClickListener confirmListener,
                                       View.OnClickListener cancelListener, Activity activity) {
        CustomAlertDialog dialog = new CustomAlertDialog(activity, title, message,
               cancelText, confirmText, cancelTouchOutside,isDisappearForBack, R.style.Theme_Light_Dialog);
        if (confirmListener != null) {
            dialog.setConfirmListener(confirmListener);
        }
        if (cancelListener != null) {
            dialog.setCancelListener(cancelListener);
        }
        dialog.show();
    }

    /**
     * 显示对话框
     *
     * @param title              标题
     * @param message            内容
     * @param cancelText         取消按钮文本
     * @param confirmText        确定按钮文本
     * @param cancelTouchOutside 是否点击外侧区域取消Dialog
     * @param isDisappearForBack 是否点击回退键取消Dialog
     * @param confirmListener    确认按钮点击监听
     * @param cancelListener     取消按钮点击监听
     */
    public static void showAlertDialog(String title, String message, String cancelText, String confirmText,
                                       boolean cancelTouchOutside, boolean isDisappearForBack, View.OnClickListener confirmListener,
                                       View.OnClickListener cancelListener, Activity activity, int style) {
        CustomAlertDialog dialog = new CustomAlertDialog(activity, title, message,
                confirmText, cancelText, cancelTouchOutside,isDisappearForBack,0);
        if (confirmListener != null) {
            dialog.setConfirmListener(confirmListener);
        }
        if (cancelListener != null) {
            dialog.setCancelListener(cancelListener);
        }
        dialog.show();
    }

    public static void showAlertDialog(Activity activity, View view){
        Dialog dialog= returnDialog(activity,view,-1);
        dialog.show();
    }
    public static void showAlertDialog(Activity activity, View view, int theme){
        Dialog dialog= returnDialog(activity,view,theme);
        dialog.show();
    }

    public static Dialog returnDialog(Activity activity, View view, int theme){
        CustomAlertDialog dialog;
        if(theme!=-1){
             dialog = new CustomAlertDialog(activity, view,
                    theme);
        }else{
             dialog = new CustomAlertDialog(activity, view,
                    R.style.Theme_Light_Dialog);
        }
        return dialog;
    }

}
