package stan.reuenthal.com.xsx.IMpanel;

import com.netease.nimlib.sdk.msg.model.IMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MessageListPanelHelper {

    private static MessageListPanelHelper instance;

    private List<LocalMessageObserver> observers = new ArrayList<>();

    public static MessageListPanelHelper getInstance() {
        if (instance == null) {
            instance = new MessageListPanelHelper();
        }

        return instance;
    }

    public interface LocalMessageObserver {
        void onAddMessage(IMMessage message);
        void onClearMessages(String account);
    }

    public void registerObserver(LocalMessageObserver o, boolean register) {
        if (register) {
            observers.add(o);
        } else {
            observers.remove(o);
        }
    }

    public void notifyAddMessage(IMMessage msg) {
        for (LocalMessageObserver o : observers) {
            o.onAddMessage(msg);
        }
    }

    public void notifyClearMessages(String account) {
        for (LocalMessageObserver o : observers) {
            o.onClearMessages(account);
        }
    }
}
