package stan.reuenthal.com.xsx.IMpanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public abstract class AbsContactViewHolder<T extends AbsContactItem> {
    protected View view;

    protected Context context;

    public AbsContactViewHolder() {

    }

    public abstract void refresh(ContactDataAdapter adapter, int position, T item);

    public abstract View inflate(LayoutInflater inflater);

    public final View getView() {
        return view;
    }

    public void create(Context context) {
        this.context = context;
        this.view = inflate(LayoutInflater.from(context));
    }
}