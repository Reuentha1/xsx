package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.NewsActivity.NewsActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.WoFrafment.check_photo.ImagePagerActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.check_photo.myStateImagePagerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by FengChaoQun
 * on 2016/7/9
 */
public class myStateActivity extends BaseActivity implements StateContract.View {
    public static final String TYPE = "TYPE";
    public static final int SELF = 1000;
    public static final int OTHRE = 2000;
    private int current_type;
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.more)
    ImageView newsList;
    @Bind(R.id.title)
    TextView title;
    private ListView listView;
    private RelativeLayout headView;
    private List<String> list = new ArrayList<String>();
    private StateContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystate);
        ButterKnife.bind(this);
        setmPresenter(new StatePresenter(this,this));
        typeOfState();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        headView = (RelativeLayout) getLayoutInflater().inflate(R.layout.util_mystate_header, null);
        listView.addHeaderView(headView);
        headView.setEnabled(false);
        mPresenter.LoadData();
    }

    @Override
    public void refreshData() {

        for (int i = 0; i < 12; i++) {
            list.add("hh");
        }

        String[] urls2 = {
                "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
                "http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
                "http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg"
        };

        final ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            imageUrls.add(urls2[i]);
        }

        Mystate_adpter mystate_adpter = new Mystate_adpter(this, 1, list,this);
        listView.setAdapter(mystate_adpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    Intent intent = new Intent(myStateActivity.this, myStateImagePagerActivity.class);
                    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, imageUrls);
                    intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void setmPresenter(@Nullable StateContract.Presenter presenter) {
        this.mPresenter=presenter;
    }

    @Override
    public void gotoNews() {
        Intent news_intent = new Intent(myStateActivity.this, NewsActivity.class);
        startActivity(news_intent);
    }

    @Override
    public void typeOfState() {
        current_type = getIntent().getIntExtra(TYPE, 0);
        switch (current_type) {
            case SELF:
                title.setText("我的动态");
                newsList.setVisibility(View.VISIBLE);
                break;
            case OTHRE:
                title.setText("动态");
                newsList.setVisibility(View.GONE);
                break;
            default:
                showToast("跳转异常...");
//                finish();
                break;
        }
    }

    public int getCurrent_type() {
        return current_type;
    }

    @OnClick({R.id.back, R.id.more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.more:
                gotoNews();
                break;
        }
    }
}
