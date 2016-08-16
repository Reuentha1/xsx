package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;

/**
 * 通讯录数据源提供者接口
 */
public interface IContactDataProvider {
    public List<AbsContactItem> provide(TextQuery query);
}
