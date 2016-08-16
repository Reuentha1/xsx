package stan.reuenthal.com.xsx.IMpanel;

import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public final class UserDataProvider {
    public static final List<AbsContactItem> provide(TextQuery query) {
        List<UserInfoProvider.UserInfo> sources = query(query);
        List<AbsContactItem> items = new ArrayList<>(sources.size());
        for (UserInfoProvider.UserInfo u : sources) {
            items.add(new ContactItem(ContactHelper.makeContactFromUserInfo(u), ItemTypes.FRIEND));
        }

        LogUtil.i(UIKitLogTag.CONTACT, "contact provide data size =" + items.size());
        return items;
    }

    private static final List<UserInfoProvider.UserInfo> query(TextQuery query) {
        if (query != null) {
            List<UserInfoProvider.UserInfo> users = NimUIKit.getContactProvider().getUserInfoOfMyFriends();
            for (Iterator<UserInfoProvider.UserInfo> iter = users.iterator(); iter.hasNext(); ) {
                if (!ContactSearch.hitUser(iter.next(), query)) {
                    iter.remove();
                }
            }
            return users;
        } else {
            return NimUIKit.getContactProvider().getUserInfoOfMyFriends();
        }
    }
}