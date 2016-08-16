package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SelectSchoolFreagment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.MainActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SchoolNoOpenFragment.SchoolNoOpenFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SerchFragment.SerchFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class SelectSchoolFragment extends BaseFragment implements SelectSchoolContract.View, View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-SelectSchoolFragment";

    private View mView;
    private ListView listView;
    private TextView tv_school;
    private View reflesh, serch;
    private String[] school_list;

    private SelectSchoolContract.Presenter mPresenter;

    public static SelectSchoolFragment newInstance() {
        return new SelectSchoolFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_select_school, container, false);
        mView = view;
        setmPresenter(new SelectSchoolPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        tv_school = (TextView) mView.findViewById(R.id.school);
        tv_school.setOnClickListener(this);
        reflesh = mView.findViewById(R.id.reflesh);
        reflesh.setOnClickListener(this);
        serch = mView.findViewById(R.id.serch_layout);
        serch.setOnClickListener(this);

        listView = (ListView) mView.findViewById(R.id.listview);
        school_list = new String[]{"江南大学", "江南小学", "江南中学"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.item_textview_no_pad,
                school_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.isSchoolAccess(school_list[position]);
            }
        });
    }

    @Override
    public void setmPresenter(@Nullable SelectSchoolContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void gotoMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void setCurrentSchool(String school) {
        tv_school.setText(school);
    }

    @Override
    public void setAdapter(String[] arrayList) {
        school_list = arrayList;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.item_textview_no_pad, arrayList);
//        MyAdapter adapter=new MyAdapter(getContext(),R.layout.item_textview_no_pad,arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void gotoAppeal() {
        SchoolNoOpenFragment frag = ((LoginRegisterActivity) mActivity).getSchoolNoOpenFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(SchoolNoOpenFragment.TAG)
                .commit();
    }

    @Override
    public void clickOnCurrent() {
        mPresenter.isSchoolAccess(tv_school.getText().toString());
    }

    @Override
    public void clickOnSerch() {
        SerchFragment frag = ((LoginRegisterActivity) mActivity).getSerchFragment();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.loginregisterContent, frag)
                .addToBackStack(SerchFragment.TAG)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.serch_layout:
                clickOnSerch();
                break;
            case R.id.school:
                clickOnCurrent();
                break;
            case R.id.reflesh:
                mPresenter.clickOnReflesh();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((LoginRegisterActivity) getActivity()).setIs_finish(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((LoginRegisterActivity) getActivity()).setIs_finish(false);
    }

    private class MyAdapter extends ArrayAdapter<String> {
        private String[] list;
        private TextView textView;

        public MyAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            textView = (TextView) LayoutInflater.from(context).inflate(resource, null);
            list = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            textView.setText(list[position]);
            return textView;
        }
    }
}
