package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/7/28
 */
public class SelectPersonActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = BaseActivity.TAG + "-SelectPersonActivity";

//    public static final String TRANSMIT_TYPE="TRANSMIT_TYPE";
    public static final String SELECT_PERSON="SELECT_PERSON";
//    public static final int SCHOOL_HELP_TRANSMIT=80001;
//    public static final int SCHOOL_REWARD_TRANSMIT=80002;
    public static final int SELECT_PERSON_CODE=9000;
//    private int current_type;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private PinyinComparator pinyinComparator;

    private RecyclerView recyclerView;
    private SelectedPersonAdapter selectedPersonAdapter;
    private List<String> selectPerson=new ArrayList<String>();
    private int noLimit = 1000;
    private int limit;


    private TextView cancel;
    private TextView sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_person);
        limit = getIntent().getIntExtra("limit", noLimit);
        initView();
        refreshCount();
    }

    private void initView(){
        sortListView =(ListView)findViewById(R.id.listview);
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        cancel=(TextView)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        sure=(TextView)findViewById(R.id.sure);
        sure.setOnClickListener(this);

        recyclerView=(RecyclerView)findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
//        for (int i=0;i<=10;i++){
//            selectPerson.add(""+i);
//        }
        selectedPersonAdapter=new SelectedPersonAdapter(this,selectPerson,this);
        recyclerView.setAdapter(selectedPersonAdapter);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                if (s.contains("☆")){
                    sortListView.setSelection(0);
                    return;
                }
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.date));

        SortModel hs=new SortModel();
        hs.setName("华爽");
        hs.setSortLetters("@");
        hs.setSpecial(true);

        SourceDateList.add(hs);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(this, SourceDateList,limit,this);
        sortListView.setAdapter(adapter);

//        current_type=getIntent().getIntExtra(TRANSMIT_TYPE,SCHOOL_HELP_TRANSMIT);
    }


    private List<SortModel> filledData(String[] date) {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(
                        filterStr.toString().toUpperCase()) != -1
                        || characterParser.getSelling(name).toUpperCase()
                        .startsWith(filterStr.toString().toUpperCase())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        Log.d("hhhh", filterStr.toString().toUpperCase());

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    public List<String> getSelectPerson() {
        return selectPerson;
    }

    public void setSelectPerson(List<String> selectPerson) {
        this.selectPerson = selectPerson;
        selectedPersonAdapter.setSelectPerson(this.selectPerson);
        selectedPersonAdapter.notifyDataSetChanged();
    }

    public void addSelectPerson(String name){
        this.selectPerson.add(name);
        selectedPersonAdapter.setSelectPerson(this.selectPerson);
        selectedPersonAdapter.notifyDataSetChanged();
        refreshCount();
    }

    public void reduceSelectPerson(String name){
        if (selectPerson.contains(name)){
            selectPerson.remove(name);
            selectedPersonAdapter.setSelectPerson(this.selectPerson);
            selectedPersonAdapter.notifyDataSetChanged();
            refreshCount();
        }
    }

    public void showOverLimit(){
        Toast.makeText(SelectPersonActivity.this, "最多只能选择"+limit+"人", Toast.LENGTH_SHORT).show();
    }

    public void refreshCount(){
        if (limit == noLimit) {
            sure.setText("确认(" + selectPerson.size() + ")");
        } else {
            sure.setText("确认(" + selectPerson.size() + "/" + limit + ")");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.sure:
                sure();
                break;
        }
    }

    private void sure(){
        Intent intent=new Intent();
        intent.putStringArrayListExtra(SELECT_PERSON,(ArrayList<String>) selectPerson);
        setResult(SELECT_PERSON_CODE,intent);
        finish();
    }
}
