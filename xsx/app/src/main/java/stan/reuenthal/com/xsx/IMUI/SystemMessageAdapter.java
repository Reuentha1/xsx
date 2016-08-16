package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import stan.reuenthal.com.xsx.IMpanel.TAdapter;
import stan.reuenthal.com.xsx.IMpanel.TAdapterDelegate;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class SystemMessageAdapter extends TAdapter {

    private SystemMessageViewHolder.SystemMessageListener systemMessageListener;

    public SystemMessageAdapter(Context context, List<?> items, TAdapterDelegate delegate,
                                SystemMessageViewHolder.SystemMessageListener listener) {
        super(context, items, delegate);
        this.systemMessageListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (systemMessageListener != null) {
            ((SystemMessageViewHolder) view.getTag()).setListener(systemMessageListener);
        }

        return view;
    }
}
