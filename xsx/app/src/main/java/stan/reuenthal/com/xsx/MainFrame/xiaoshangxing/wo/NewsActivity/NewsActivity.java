package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.DialogUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.LocationUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.DetailsActivity.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/11
 */
public class NewsActivity extends BaseActivity implements NewsContract.View {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.myState)
    TextView myState;
    @Bind(R.id.empty)
    TextView empty;
    @Bind(R.id.title)
    RelativeLayout title;
    @Bind(R.id.listview)
    ListView listview;

    private NewsContract.Presenter mPresenter;
    private newsAdapter adapter;
    private List<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        setmPresenter(new NewsPresenter(this, this));
        mPresenter.loadData();
        initView();
    }

    private void initView() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoDetail();
            }
        });
    }

    @Override
    public void initData() {
        for (int i=0;i<=15;i++){
            list.add(""+i);
        }
        adapter=new newsAdapter(this,11,list);
        listview.setAdapter(adapter);
    }

    @Override
    public void cleanData() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCleanDialog() {
        DialogUtils.DialogMenu2 dialogMenu2 = new DialogUtils.DialogMenu2(this);
        dialogMenu2.addMenuItem("清空所有消息");
        dialogMenu2.setMenuListener(new DialogUtils.DialogMenu2.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                mPresenter.cleanData();
            }

            @Override
            public void onCancel() {

            }
        });
        dialogMenu2.initView();
        dialogMenu2.show();
        LocationUtil.bottom_FillWidth(this, dialogMenu2);
    }

    @Override
    public void clickOnClean() {
        if (adapter.isEmpty()) {
            showToast("没有数据让你清空啦...");
        } else {
            showCleanDialog();
        }
    }

    @Override
    public void gotoDetail() {
        Intent detai_intent = new Intent(this, DetailsActivity.class);
        startActivity(detai_intent);
    }

    @Override
    public void setmPresenter(@Nullable NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick({R.id.back, R.id.empty, R.id.title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.empty:
                clickOnClean();
                break;
            case R.id.title:
                break;
        }
    }
}
