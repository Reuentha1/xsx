package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;

import com.netease.nimlib.sdk.msg.model.RecentContact;

import java.util.List;

import stan.reuenthal.com.xsx.IMpanel.TAdapter;
import stan.reuenthal.com.xsx.IMpanel.TAdapterDelegate;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class RecentContactAdapter extends TAdapter<RecentContact> {

    private RecentContactsCallback callback;

    public RecentContactAdapter(Context context, List<RecentContact> items, TAdapterDelegate delegate) {
        super(context, items, delegate);
    }

    public RecentContactsCallback getCallback() {
        return callback;
    }

    public void setCallback(RecentContactsCallback callback) {
        this.callback = callback;
    }
}
