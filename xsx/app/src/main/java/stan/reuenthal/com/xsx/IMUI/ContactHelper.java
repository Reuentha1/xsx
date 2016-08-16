package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */

import android.content.Context;

import stan.reuenthal.com.xsx.keyboard.ContactEventListener;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * UIKit联系人列表定制展示类
 * <p/>
 */
public class ContactHelper {

    public static void init() {
        setContactEventListener();
    }

    private static void setContactEventListener() {
        NimUIKit.setContactEventListener(new ContactEventListener() {
            @Override
            public void onItemClick(Context context, String account) {
                UserProfileActivity.start(context, account);
            }

            @Override
            public void onItemLongClick(Context context, String account) {

            }

            @Override
            public void onAvatarClick(Context context, String account) {
                UserProfileActivity.start(context, account);
            }
        });
    }

}
