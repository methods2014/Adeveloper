package method.com.adeveloper.home;

import android.os.Bundle;
import android.view.View;

import method.com.adeveloper.R;
import method.com.adeveloper.base.BaseActivity;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
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
    @Override
    public void onClick(View v) {

    }
}
