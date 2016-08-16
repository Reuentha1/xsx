package stan.reuenthal.com.xsx.IMpanel;

import com.netease.nimlib.sdk.team.model.TeamMember;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class TeamMemberContact extends AbsContact {

    private TeamMember teamMember;

    public TeamMemberContact(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    @Override
    public String getContactId() {
        return teamMember.getAccount();
    }

    @Override
    public int getContactType() {
        return Type.TeamMember;
    }

    @Override
    public String getDisplayName() {
        return TeamDataCache.getInstance().getTeamMemberDisplayName(teamMember.getTid(), teamMember.getAccount());
    }
}
