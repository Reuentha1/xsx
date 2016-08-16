package stan.reuenthal.com.xsx.keyboard;

import android.widget.Toast;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.team.model.Team;

import stan.reuenthal.com.xsx.IMpanel.MessageFragment;
import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class TeamMessageFragment extends MessageFragment {

    private Team team;

    @Override
    public boolean isAllowSendMessage(IMMessage message) {
        if (team == null || !team.isMyTeam()) {
            Toast.makeText(getActivity(), R.string.team_send_message_not_allow, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
