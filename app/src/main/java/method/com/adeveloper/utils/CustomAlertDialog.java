package method.com.adeveloper.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import method.com.adeveloper.R;

/**
 * Created by liuxuegang1 on 2016/1/31.
 */
public class CustomAlertDialog extends Dialog {


    View.OnClickListener dialogLeftBtnListener;
    View.OnClickListener dialogRightBtnListener;
    /**
     * 标题，内容，左边的按钮，右边的按钮
     */
    String title,content,cancelText,confirmText;
    TextView tv_alertDialog_title,tv_alertDialog_content,alertDialog_btn_cancel,alertDialog_btn_confirm;
    View line;
    Context mContext;
    View view;
    int screenWidth,screenHeight;
    boolean cancelTouchOutside = false;
    boolean isDisappearForBack=false;

    public CustomAlertDialog(Context context) {
        super(context);
        this.mContext=context;
    }

    public CustomAlertDialog(Context context, int theme) {
        super(context, theme);
        this.mContext=context;
    }

    public CustomAlertDialog(Context context, View view, int theme) {
        this(context,theme);
        this.mContext=context;
        this.view=view;
    }

    protected CustomAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext=context;
    }

    public CustomAlertDialog(Context context, String title, String message, String cancelText, String confirmText, boolean cancelTouchOutside, boolean isDisappearForBack, int theme) {
        this(context,theme);
        this.title = title;
        this.content = message;
        this.cancelText = cancelText;
        this.confirmText = confirmText;
        this.cancelTouchOutside = cancelTouchOutside;
        this.mContext=context;
        this.isDisappearForBack=isDisappearForBack;
    }

    public void setConfirmListener(View.OnClickListener dialogLeftBtnListener){
             this.dialogLeftBtnListener=dialogLeftBtnListener;
    }
    public void setCancelListener(View.OnClickListener dialogRightBtnListener){
        this.dialogRightBtnListener=dialogRightBtnListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenWidth=mContext.getResources().getDisplayMetrics().widthPixels;
        screenHeight=mContext.getResources().getDisplayMetrics().widthPixels;
        setCanceledOnTouchOutside(cancelTouchOutside);
        if(view==null){
           view=  LayoutInflater.from(mContext).inflate(R.layout.alert_dialog,null);
           initView(view);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(screenWidth/1.3), ViewGroup.LayoutParams.MATCH_PARENT);
            setContentView(view, params);
       }else{
            setContentView(view);
        }
        setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                return isDisappearForBack; //默认返回 false，这里false不能屏蔽返回键，改成true就可以了
            }
        });
    }
    private void initView(View view){
        tv_alertDialog_title=(TextView)view.findViewById(R.id.tv_alertDialog_title);
        tv_alertDialog_content=(TextView)view.findViewById(R.id.tv_alertDialog_content);
        alertDialog_btn_cancel=(TextView)view.findViewById(R.id.alertDialog_btn_cancel);
        alertDialog_btn_confirm=(TextView)view.findViewById(R.id.alertDialog_btn_confirm);
        line=(View)view.findViewById(R.id.line);
        initData();
    }

    private void initData(){
        if(StringUtils.isEmpty(title)){
            tv_alertDialog_title.setVisibility(View.GONE);
        }else{
            tv_alertDialog_title.setVisibility(View.VISIBLE);
            tv_alertDialog_title.setText(title.toString().trim());
        }
        if(StringUtils.isEmpty(content)){
            tv_alertDialog_content.setVisibility(View.GONE);
        }else{
            tv_alertDialog_content.setVisibility(View.VISIBLE);
            tv_alertDialog_content.setText(content.toString().trim());
        }
        if(StringUtils.isEmpty(cancelText)){
            alertDialog_btn_cancel.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else{
            alertDialog_btn_cancel.setVisibility(View.VISIBLE);
            alertDialog_btn_cancel.setText(cancelText.toString().trim());
            line.setVisibility(View.VISIBLE);
        }
        if(StringUtils.isEmpty(confirmText)){
            alertDialog_btn_confirm.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        }else{
            alertDialog_btn_confirm.setVisibility(View.VISIBLE);
            alertDialog_btn_confirm.setText(confirmText.toString().trim());
        }

        alertDialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(dialogLeftBtnListener!=null){
                         dialogLeftBtnListener.onClick(v);
                  }
                      if(isShowing()){
                          dismiss();
                      }

            }
        });
        alertDialog_btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogRightBtnListener!=null){
                    dialogRightBtnListener.onClick(v);
                }
                    if(isShowing()){
                        dismiss();
                    }

            }
        });
    }
}
