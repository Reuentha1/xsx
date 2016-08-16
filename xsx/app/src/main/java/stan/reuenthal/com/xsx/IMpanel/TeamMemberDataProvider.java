package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import com.netease.nimlib.sdk.team.model.TeamMember;

import java.util.ArrayList;
import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;
import stan.reuenthal.com.xsx.keyboard.ContactGroupStrategy;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.TextComparator;

/**
 * 群成员数据源提供者
 * <p/>
 */
public class TeamMemberDataProvider {
    public static final List<AbsContactItem> provide(TextQuery query, String tid) {
        List<TeamMemberContact> sources = query(query, tid);
        List<AbsContactItem> items = new ArrayList<>(sources.size());
        for (TeamMemberContact t : sources) {
            items.add(createTeamMemberItem(t));
        }

        return items;
    }

    private static AbsContactItem createTeamMemberItem(TeamMemberContact teamMember) {
        return new ContactItem(teamMember, ItemTypes.TEAM_MEMBER) {
            @Override
            public int compareTo(ContactItem item) {
                return compareTeamMember((TeamMemberContact) getContact(), (TeamMemberContact) (item.getContact()));
            }

            @Override
            public String belongsGroup() {
                return ContactGroupStrategy.GROUP_TEAM;
            }
        };
    }

    private static int compareTeamMember(TeamMemberContact lhs, TeamMemberContact rhs) {
        return TextComparator.compareIgnoreCase(lhs.getDisplayName(), rhs.getDisplayName());
    }

    /**
     * * 数据查询
     */
    private static final List<TeamMemberContact> query(TextQuery query, String tid) {
        List<TeamMember> teamMembers = TeamDataCache.getInstance().getTeamMemberList(tid);

        List<TeamMemberContact> contacts = new ArrayList<>();
        for (TeamMember t : teamMembers) {
            if (t != null && (query == null || ContactSearch.hitTeamMember(t, query))) {
                contacts.add(new TeamMemberContact(t));
            }
        }

        return contacts;
    }
}
