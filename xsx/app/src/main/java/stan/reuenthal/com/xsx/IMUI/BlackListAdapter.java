package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;

import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import java.util.List;

import stan.reuenthal.com.xsx.IMpanel.TAdapter;
import stan.reuenthal.com.xsx.IMpanel.TAdapterDelegate;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class BlackListAdapter extends TAdapter<UserInfoProvider.UserInfo> {

    public BlackListAdapter(Context context, List<UserInfoProvider.UserInfo> items, TAdapterDelegate delegate, ViewHolderEventListener
            viewHolderEventListener) {
        super(context, items, delegate);

        this.viewHolderEventListener = viewHolderEventListener;
    }

    public interface ViewHolderEventListener {
        void onItemClick(UserInfoProvider.UserInfo user);

        void onRemove(UserInfoProvider.UserInfo user);
    }

    private ViewHolderEventListener viewHolderEventListener;

    public ViewHolderEventListener getEventListener() {
        return viewHolderEventListener;
    }
}
