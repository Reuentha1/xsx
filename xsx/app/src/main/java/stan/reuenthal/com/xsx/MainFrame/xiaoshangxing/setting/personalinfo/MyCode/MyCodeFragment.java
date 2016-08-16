package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.MyCode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.ActionSheet;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

/**
 * Created by 15828 on 2016/7/12.
 */
public class MyCodeFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private TextView back;
    private ActionSheet mActionSheet;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personalinfo_mycode, container, false);
        back = (TextView) mView.findViewById(R.id.mycode_back);
        imageView = (ImageView) mView.findViewById(R.id.MyCode_threepoint);
        back.setOnClickListener(this);
        imageView.setOnClickListener(this);


        return mView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mycode_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.MyCode_threepoint:
                if (mActionSheet == null) {
                    mActionSheet = new ActionSheet(getActivity());
                    mActionSheet.addMenuItem(getResources().getString(R.string.savetophone));
                }
                mActionSheet.show();
                WindowManager windowManager = getActivity().getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                WindowManager.LayoutParams lp = mActionSheet.getWindow().getAttributes();
                lp.width = (int) (display.getWidth()); //设置宽度
                mActionSheet.getWindow().setAttributes(lp);
                mActionSheet.setMenuListener(new ActionSheet.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {
                        if (position == 0)
                            Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onCancel() {
                          Toast.makeText(getActivity(), "onCancel", Toast.LENGTH_SHORT).show();
                    }
                });
            default:
                break;
        }

    }
}
