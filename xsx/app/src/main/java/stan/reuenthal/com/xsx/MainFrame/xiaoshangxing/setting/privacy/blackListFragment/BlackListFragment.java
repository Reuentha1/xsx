package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.blackListFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.CustomSwipeListView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.SwipeItemView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 15828 on 2016/7/14.
 */
public class BlackListFragment extends BaseFragment implements View.OnClickListener {
    private ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
    private View mView;
    private TextView back;
    private CustomSwipeListView blackList;
    private BaseAdapter baseAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_blacklist, container, false);
        back = (TextView) mView.findViewById(R.id.blacklist_back);
        back.setOnClickListener(this);
        blackList = (CustomSwipeListView) mView.findViewById(R.id.blacklist_listView);
        init();
        return mView;
    }

    private void init() {

        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> itemData = new HashMap<String, Object>();
            itemData.put("Name", "姓名" + i);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cirecleiamge_default);
            itemData.put("Bitmap", bitmap);
            data.add(itemData);
        }

        baseAdapter = new BaseAdapter() {
            SwipeItemView mLastSlideViewWithStatusOn;

            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public Map<String, Object> getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                SwipeItemView slideView = (SwipeItemView) convertView;
                if (slideView == null) {
                    View itemView = getActivity().getLayoutInflater().inflate(R.layout.item_setting_blacklist, null);

                    slideView = new SwipeItemView(getActivity());
                    slideView.setContentView(itemView);

                    holder = new ViewHolder(slideView);
                    slideView.setOnSlideListener(new SwipeItemView.OnSlideListener() {

                        @Override
                        public void onSlide(View view, int status) {
                            // TODO Auto-generated method stub
                            if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
                                mLastSlideViewWithStatusOn.shrink();
                            }

                            if (status == SLIDE_STATUS_ON) {
                                mLastSlideViewWithStatusOn = (SwipeItemView) view;
                            }
                        }
                    });
                    slideView.setTag(holder);
                } else {
                    holder = (ViewHolder) slideView.getTag();
                }
                final Map<String, Object> item = data.get(position);
                if (CustomSwipeListView.mFocusedItemView != null) {
                    CustomSwipeListView.mFocusedItemView.shrink();
                }

                holder.image.setImageBitmap((Bitmap) item.get("Bitmap"));
                holder.name.setText(item.get("Name").toString());
                holder.deleteHolder.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        data.remove(position);
                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                });
                return slideView;
            }
        };

        blackList.setAdapter(baseAdapter);

    }

    private static class ViewHolder {
        public ImageView image;
        public TextView name;
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.blacklist_img);
            name = (TextView) view.findViewById(R.id.blacklist_text);
            deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
        }
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
