package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */
public class ReminderSettings {
    /**
     * 最大显示未读数
     */
    public static final int MAX_UNREAD_SHOW_NUMBER = 99;

    public static int unreadMessageShowRule(int unread) {
        return Math.min(MAX_UNREAD_SHOW_NUMBER, unread);
    }
}
