package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.roll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.myStateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/12
 */
public class rollActivity extends BaseActivity {
    public static final String TYPE="TYPE";
    public static final int FORBIDDEN=1000;
    public static final int NOTICE=2000;
    private int current_type;
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listview)
    ListView listview;

    private List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        for (int i=0;i<6;i++){
            list.add(""+i);
        }
        roll_listview_adpter adpter=new roll_listview_adpter(this,1,list);
        listview.setAdapter(adpter);

        initType();

    }

    private void initType() {
        current_type = getIntent().getIntExtra(TYPE, 0);
        switch (current_type) {
            case NOTICE:
                title.setText("提醒谁看");
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent state_intent = new Intent(rollActivity.this, myStateActivity.class);
                        state_intent.putExtra(myStateActivity.TYPE, myStateActivity.OTHRE);
                        startActivity(state_intent);
                    }
                });
                break;
            case FORBIDDEN:
                title.setText("不给谁看");
                break;
            default:
                showToast("跳转错误");
                break;
        }
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}
