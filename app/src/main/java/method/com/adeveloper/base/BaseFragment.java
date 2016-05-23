package method.com.adeveloper.base;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import method.com.adeveloper.utils.AlertDialogUtils;

/**
 * Created by chen on 2016/5/11.
 */
public class BaseFragment extends Fragment {

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName()); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }
    Toast toast;
    public void showToast(@NonNull String msg) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
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
    public void showAlertDialog(String title, String message, String cancelText, String confirmText,
                                boolean cancelTouchOutside, boolean isDisappearForBack,
                                View.OnClickListener cancelListener, View.OnClickListener confirmListener) {
        AlertDialogUtils.showAlertDialog(title, message, cancelText, confirmText, cancelTouchOutside, isDisappearForBack, cancelListener, confirmListener, getActivity());
    }
}
