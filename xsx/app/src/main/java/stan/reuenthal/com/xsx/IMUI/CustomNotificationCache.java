package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */

import com.netease.nimlib.sdk.msg.model.CustomNotification;

import java.util.LinkedList;
import java.util.List;

/**
 * 自定义通知缓存
 * <p/>
 */
public class CustomNotificationCache {

    public static CustomNotificationCache getInstance() {
        return InstanceHolder.instance;
    }

    private List<CustomNotification> notifications = new LinkedList<>();

    public void addCustomNotification(CustomNotification notification) {
        if (notification == null) {
            return;
        }

        if (!notifications.contains(notification)) {
            notifications.add(0, notification);
        }
    }

    public List<CustomNotification> getCustomNotification() {
        return notifications;
    }

    public static class InstanceHolder {
        public final static CustomNotificationCache instance = new CustomNotificationCache();
    }
}
