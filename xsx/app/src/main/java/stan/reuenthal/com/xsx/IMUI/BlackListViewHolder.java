package stan.reuenthal.com.xsx.IMUI;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import stan.reuenthal.com.xsx.IMpanel.HeadImageView;
import stan.reuenthal.com.xsx.IMpanel.NimUserInfoCache;
import stan.reuenthal.com.xsx.IMpanel.TViewHolder;
import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class BlackListViewHolder extends TViewHolder {
    private HeadImageView headImageView;
    private TextView accountText;
    private Button removeBtn;
    private UserInfoProvider.UserInfo user;

    @Override
    protected int getResId() {
        return R.layout.black_list_item;
    }

    @Override
    protected void inflate() {
        headImageView = findView(R.id.head_image);
        accountText = findView(R.id.account);
        removeBtn = findView(R.id.remove);
    }

    @Override
    protected void refresh(Object item) {
        user = (NimUserInfo) item;

        accountText.setText(NimUserInfoCache.getInstance().getUserDisplayName(user.getAccount()));
        headImageView.loadBuddyAvatar(user.getAccount());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().getEventListener().onItemClick(user);
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().getEventListener().onRemove(user);
            }
        });
    }

    protected final BlackListAdapter getAdapter() {
        return (BlackListAdapter) adapter;
    }
}
