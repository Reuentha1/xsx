package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.privacyFistFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.PrivacyGridAdapter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15828 on 2016/7/14.
 */
public class PrivacyFistFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private TextView cancel, complete;
    private GridView gridView;
    private boolean isAdded = true;
    private List<Bitmap> data = new ArrayList<>(), dataTest1, dataTest2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_privacyfist, container, false);
        cancel = (TextView) mView.findViewById(R.id.privacyfirst_cancel);
        complete = (TextView) mView.findViewById(R.id.privacyfirst_complete);
        cancel.setOnClickListener(this);
        complete.setOnClickListener(this);
        gridView = (GridView) mView.findViewById(R.id.privacyfirst_gridview);

        final Bitmap bitmap1 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.cirecleiamge_default);
        dataTest1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataTest1.add(bitmap1);
        }
        dataTest2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataTest2.add(bitmap1);
        }

        final PrivacyGridAdapter adapter = new PrivacyGridAdapter(getActivity(), data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data.size() != 0) {
                    Log.d("qqq", "..." + adapter.getCount() + "...." + position);
                    if (position == adapter.getCount() - 1) {
                        for (int i = 0; i < adapter.getCount() - 2; i++) {
                            if (adapter.getRedDeleteViews().get(i).getVisibility() == View.GONE) {
                                adapter.getRedDeleteViews().get(i).setVisibility(View.VISIBLE);
                                isAdded = false;
                            } else {
                                adapter.getRedDeleteViews().get(i).setVisibility(View.GONE);
                                isAdded = true;
                            }
                        }
                    } else if (position == adapter.getCount() - 2) {
                        if (isAdded) data.add(bitmap1);
                    } else {
                        if (!isAdded) {
                            data.remove(position);
                            Log.d("qqq", "...222" + adapter.getCount() + "...." + position);
                            if (data.size() != 0)
                                adapter.getRedDeleteViews().get(adapter.getCount() - 2).setVisibility(View.GONE);
                            else {
                                complete.setAlpha((float) 0.5);
                                complete.setClickable(false);
                                adapter.getRedDeleteViews().get(adapter.getCount() - 1).setVisibility(View.GONE);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    complete.setClickable(true);
                    complete.setAlpha(1);
                    isAdded = true;
                    data.add(bitmap1);
                    adapter.notifyDataSetChanged();
                    Log.d("qqq", "...count" + adapter.getCount());
                }


            }
        });

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.privacyfirst_cancel:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.privacyfirst_complete:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            default:
                break;
        }

    }
}
