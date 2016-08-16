package stan.reuenthal.com.xsx.IMpanel;

import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import stan.reuenthal.com.xsx.keyboard.IContact;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ContactHelper {
    public static IContact makeContactFromUserInfo(final UserInfoProvider.UserInfo userInfo) {
        return new IContact() {
            @Override
            public String getContactId() {
                return userInfo.getAccount();
            }

            @Override
            public int getContactType() {
                return IContact.Type.Friend;
            }

            @Override
            public String getDisplayName() {
                return NimUIKit.getContactProvider().getUserDisplayName(userInfo.getAccount());
            }
        };
    }
}
