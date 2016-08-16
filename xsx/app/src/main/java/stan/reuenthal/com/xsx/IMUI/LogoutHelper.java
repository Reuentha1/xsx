package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import stan.reuenthal.com.xsx.keyboard.LoginSyncDataStatusObserver;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * 注销帮助类
 * Created by huangjun on 2015/10/8.
 */
public class LogoutHelper {
    public static void logout() {
        // 清理缓存&注销监听&清除状态
        NimUIKit.clearCache();
        ChatRoomHelper.logout();
        DemoCache.clear();
        LoginSyncDataStatusObserver.getInstance().reset();
    }
}
