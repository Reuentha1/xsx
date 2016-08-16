package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public interface TAdapterDelegate {

    public int getViewTypeCount();

    public Class<? extends TViewHolder> viewHolderAtPosition(int position);

    public boolean enabled(int position);
}