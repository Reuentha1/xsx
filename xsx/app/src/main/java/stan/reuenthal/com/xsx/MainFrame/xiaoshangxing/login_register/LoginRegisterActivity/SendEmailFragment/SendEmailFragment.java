package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SendEmailFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.LoginRegisterActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by FengChaoQun
 * on 2016/6/24
 */
public class SendEmailFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-SendEmailFragment";

    private View mView;
    private View back;
    private TextView complete, email, email_sended_notice;

    public static SendEmailFragment newInstance() {
        return new SendEmailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_send_email, container, false);
        mView = view;
        initView();
        return view;
    }

    private void initView() {
        back = mView.findViewById(R.id.back);
        back.setOnClickListener(this);
        complete = (TextView) mView.findViewById(R.id.complete);
        complete.setOnClickListener(this);
        email = (TextView) mView.findViewById(R.id.email);
        email_sended_notice = (TextView) mView.findViewById(R.id.email_sended_notice);

        String emai_address = ((LoginRegisterActivity) getActivity()).getEmai();
        email.setText(email.getText().toString().replace("@@", emai_address));
        email_sended_notice.setText(email_sended_notice.getText().toString().replace("@@", emai_address));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                getFragmentManager().popBackStack();
                break;
            case R.id.complete:
                Intent intent = new Intent(getContext(), LoginRegisterActivity.class);
                intent.putExtra(LoginRegisterActivity.INTENT_TYPE, LoginRegisterActivity.LOGIN);
                getActivity().startActivity(intent);
                getActivity().finish();
        }
    }
}
