package method.com.adeveloper.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseActivity;
import method.com.adeveloper.home.event.OnPageChangeEvent;
import method.com.adeveloper.utils.AndroidUtils;
import method.com.adeveloper.view.ScrollViewCanListen;
import method.com.adeveloper.view.ScrollViewListener;
import method.com.adeveloper.view.VerticalScrollView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    RelativeLayout rl_home_container;
    LinearLayout ll_tab;
    RadioButton rb_home;
    RadioButton rb_category;
    RadioButton rb_member;
    FrameLayout fl_treasuer;
    final int HOME_FRAGMENT = 1;
    final int CATEGORY_FRAGMENT = 2;
    final int MINE_FRAGMENT = 3;
    int currentFragment = HOME_FRAGMENT; //当前显示的Fragment

    ImageView cursor;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        fragmentManager = getSupportFragmentManager();
        initView();
        showFragment(HOME_FRAGMENT);
    }

    private void initView(){
        rl_home_container = (android.widget.RelativeLayout)findViewById(R.id.rl_home_container);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_category = (RadioButton) findViewById(R.id.rb_category);
        rb_member = (RadioButton) findViewById(R.id.rb_member);
        fl_treasuer = (FrameLayout) findViewById(R.id.fl_treasuer);
        initTreasureMessage();
        initScrollView();
        ll_tab = (LinearLayout) findViewById(R.id.ll_tab);
        ll_tab.setVisibility(View.INVISIBLE);
        FrameLayout.LayoutParams  layoutParams = new FrameLayout.LayoutParams(ll_tab.getLayoutParams());
        layoutParams.setMargins(0, AndroidUtils.dip2px(this, 55), 0, 0);
        ll_tab.setLayoutParams(layoutParams);
        cursor = (ImageView) findViewById(R.id.cursor);
    }

    /**
     * 头条
     */
    private void initTreasureMessage() {
        VerticalScrollView verticalScrollView = new VerticalScrollView(this);
        fl_treasuer.addView(verticalScrollView);
    }

    private void initScrollView(){
        fl_treasuer.getBackground().setAlpha(0);
        ScrollViewCanListen sv_scrollView = (ScrollViewCanListen) findViewById(R.id.sv_scrollView);
        sv_scrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollViewCanListen scrollView, int x, int y, int oldx, int oldy) {
                //Log.i(TAG, "x:" + x + " y:" + y + " oldx:" + oldx + " oldy:" + oldy);
                //Log.i(TAG, "x:" + x + " y:" + y + " oldx:" + oldx + " oldy:" + oldy);
                if(currentFragment != HOME_FRAGMENT){ //其他页不处理
                    return;
                }
                int height = 390; //头条高度110 + 广告图
                if(y <= 0){
                    fl_treasuer.getBackground().setAlpha(0);
                    if(ll_tab.getVisibility() == View.VISIBLE){
                        ll_tab.setVisibility(View.INVISIBLE);
                    }
                }else if(y < height) {
                    fl_treasuer.getBackground().setAlpha(y * 255/height);
                    if(ll_tab.getVisibility() == View.VISIBLE){
                        ll_tab.setVisibility(View.INVISIBLE);
                    }
                }else if(y >= height){
                    fl_treasuer.getBackground().setAlpha(255);
                    if(ll_tab.getVisibility() != View.VISIBLE){
                        ll_tab.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    private void hiddenFragment(FragmentTransaction fragmentTransaction, int toFragment){
        if (toFragment != HOME_FRAGMENT && homeFragment != null && !homeFragment.isHidden()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (toFragment != CATEGORY_FRAGMENT && categoryFragment != null && !categoryFragment.isHidden()) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (toFragment != MINE_FRAGMENT && mineFragment != null && !mineFragment.isHidden()) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    private Fragment getFragment(int toFragment){
        Fragment ret = null;
        switch (toFragment){
            case HOME_FRAGMENT:
                if(null == homeFragment){
                    homeFragment = new HomeFragment();
                }
                ret = homeFragment;
                break;
            case CATEGORY_FRAGMENT:
                if(null == categoryFragment){
                    categoryFragment = new CategoryFragment();
                }
                ret = categoryFragment;
                break;
            case MINE_FRAGMENT:
                if(null == mineFragment){
                    mineFragment = new MineFragment();
                }
                ret = mineFragment;
                break;
        }
        return ret;
    }


    private void showFragment(int toFragment){
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = getFragment(toFragment);
        switch (toFragment){
            case HOME_FRAGMENT:
                fl_treasuer.setVisibility(View.VISIBLE);
                currentFragment = HOME_FRAGMENT;
                break;
            case CATEGORY_FRAGMENT:
                currentFragment = CATEGORY_FRAGMENT;
                fl_treasuer.setVisibility(View.INVISIBLE);
                ll_tab.setVisibility(View.INVISIBLE);
                break;
            case MINE_FRAGMENT:
                currentFragment = MINE_FRAGMENT;
                fl_treasuer.setVisibility(View.INVISIBLE);
                ll_tab.setVisibility(View.INVISIBLE);
                break;
        }
        hiddenFragment(fragmentTransaction, toFragment);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.rl_home_container, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_home:
                showFragment(HOME_FRAGMENT);
                break;
            case R.id.rb_category:
                showFragment(CATEGORY_FRAGMENT);
                break;
            case R.id.rb_member:
                showFragment(MINE_FRAGMENT);
                break;
        }
    }
    @Subscribe
    public void onEventMainThread(OnPageChangeEvent event) {
        Animation animation = new TranslateAnimation(event.fromXDelta, event.toXDelta, 0, 0);
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        cursor.startAnimation(animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_command:
                WebViewActivity.launch(this, Constants.URL_DESIGN_COMMAND);
                return true;
            case R.id.action_chainofresponsibility_1:
                WebViewActivity.launch(this, Constants.URL_DESIGN_CHAINOFRESPONSIBILITY_1);
                return true;
            case R.id.action_chainofresponsibility_2:
                WebViewActivity.launch(this, Constants.URL_DESIGN_CHAINOFRESPONSIBILITY_2);
                return true;
            case R.id.action_state:
                WebViewActivity.launch(this, Constants.URL_DESIGN_STATE);
                return true;
            case R.id.action_visitor_1:
                WebViewActivity.launch(this, Constants.URL_DESIGN_VISITOR_1);
                return true;
            case R.id.action_visitor_2:
                WebViewActivity.launch(this, Constants.URL_DESIGN_VISITOR_2);
                return true;
            case R.id.action_visitor_3:
                WebViewActivity.launch(this, Constants.URL_DESIGN_VISITOR_3);
            case R.id.action_factory:
                WebViewActivity.launch(this, Constants.URL_DESIGN_FACTORY);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
*/

}
