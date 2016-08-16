package stan.reuenthal.com.xsx.IMUI;

import android.text.TextUtils;

import com.netease.nimlib.sdk.msg.attachment.NotificationAttachment;

import stan.reuenthal.com.xsx.IMpanel.TeamDataCache;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class TeamRecentViewHolder extends CommonRecentViewHolder {

    @Override
    protected String getContent() {
        String content = descOfMsg();

        String fromId = recent.getFromAccount();
        if (!TextUtils.isEmpty(fromId)
                && !fromId.equals(NimUIKit.getAccount())
                && !(recent.getAttachment() instanceof NotificationAttachment)) {
            String tid = recent.getContactId();
            String teamNick = getTeamUserDisplayName(tid, fromId);
            content = teamNick + ": " + content;
        }

        return content;
    }

    private String getTeamUserDisplayName(String tid, String account) {
        return TeamDataCache.getInstance().getTeamMemberDisplayName(tid, account);
    }

}
