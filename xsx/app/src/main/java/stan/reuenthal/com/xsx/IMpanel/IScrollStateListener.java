package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public interface IScrollStateListener {

    /**
     * move to scrap heap
     */
    public void reclaim();


    /**
     * on idle
     */
    public void onImmutable();
}
