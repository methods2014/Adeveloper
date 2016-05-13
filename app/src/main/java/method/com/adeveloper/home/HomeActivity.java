package method.com.adeveloper.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseActivity;
import method.com.adeveloper.view.VerticalScrollView;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();
    RelativeLayout rl_home_container;
    RadioButton rb_home;
    RadioButton rb_category;
    RadioButton rb_member;
    FrameLayout fl_treasuer;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    CategoryFragment categoryFragment;
    MineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();
        initView();
        showHomeFragment(homeFragment);
    }

    private void initView(){
        rl_home_container = (android.widget.RelativeLayout)findViewById(R.id.rl_home_container);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_category = (RadioButton) findViewById(R.id.rb_category);
        rb_member = (RadioButton) findViewById(R.id.rb_member);
        fl_treasuer = (FrameLayout) findViewById(R.id.fl_treasuer);
        initTreasureMessage();
    }

    /**
     * 头条
     */
    private void initTreasureMessage() {
        VerticalScrollView verticalScrollView = new VerticalScrollView(this);
        fl_treasuer.addView(verticalScrollView);
    }


    private void hiddenFragment(FragmentTransaction fragmentTransaction){
        if (homeFragment != null && !homeFragment.isHidden()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (categoryFragment != null && !categoryFragment.isHidden()) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (mineFragment != null && !mineFragment.isHidden()) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    private void showHomeFragment(HomeFragment fragment){
        fl_treasuer.setVisibility(View.VISIBLE);
        if(null == fragment){
            fragment = new HomeFragment();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        hiddenFragment(fragmentTransaction);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.rl_home_container, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    private void showCategoryFragment(CategoryFragment fragment){
        fl_treasuer.setVisibility(View.GONE);
        if(null == fragment){
            fragment = new CategoryFragment();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        hiddenFragment(fragmentTransaction);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.rl_home_container, fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    private void showMineFragment(MineFragment fragment){
        fl_treasuer.setVisibility(View.GONE);
        if(null == fragment){
            fragment = new MineFragment();
        }
        fragmentTransaction = fragmentManager.beginTransaction();
        hiddenFragment(fragmentTransaction);
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
                showHomeFragment(homeFragment);
                break;
            case R.id.rb_category:
                showCategoryFragment(categoryFragment);
                break;
            case R.id.rb_member:
                showMineFragment(mineFragment);
                break;
        }
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
