package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class SystemMessageUnreadManager {

    private static SystemMessageUnreadManager instance = new SystemMessageUnreadManager();

    public static SystemMessageUnreadManager getInstance() {
        return instance;
    }

    private int sysMsgUnreadCount = 0;

    public int getSysMsgUnreadCount() {
        return sysMsgUnreadCount;
    }

    public synchronized void setSysMsgUnreadCount(int unreadCount) {
        this.sysMsgUnreadCount = unreadCount;
    }
}
