package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;

import com.netease.nimlib.sdk.StatusBarNotificationConfig;

import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class DemoCache {

    private static Context context;

    private static String account;

    private static StatusBarNotificationConfig notificationConfig;

    public static void clear() {
        account = null;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        DemoCache.account = account;
        NimUIKit.setAccount(account);
    }

    public static void setNotificationConfig(StatusBarNotificationConfig notificationConfig) {
        DemoCache.notificationConfig = notificationConfig;
    }

    public static StatusBarNotificationConfig getNotificationConfig() {
        return notificationConfig;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();
        //DemoCache.context = context;
    }
}
