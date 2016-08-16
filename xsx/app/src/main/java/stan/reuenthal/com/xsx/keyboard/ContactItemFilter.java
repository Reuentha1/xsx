package stan.reuenthal.com.xsx.keyboard;

import java.io.Serializable;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public interface ContactItemFilter extends Serializable {
    boolean filter(AbsContactItem item);
}
