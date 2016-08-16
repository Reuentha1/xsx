package stan.reuenthal.com.xsx.IMpanel;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import com.netease.nimlib.sdk.team.model.Team;

import java.util.ArrayList;
import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;
import stan.reuenthal.com.xsx.keyboard.ContactGroupStrategy;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.TextComparator;

/**
 * 群数据源提供者
 * <p/>
 */
public class TeamDataProvider {
    public static final List<AbsContactItem> provide(TextQuery query, int itemType) {
        List<TeamContact> sources = query(query, itemType);
        List<AbsContactItem> items = new ArrayList<>(sources.size());
        for (TeamContact t : sources) {
            items.add(createTeamItem(t));
        }

        return items;
    }

    private static AbsContactItem createTeamItem(TeamContact team) {
        return new ContactItem(team, ItemTypes.TEAM) {
            @Override
            public int compareTo(ContactItem item) {
                return compareTeam((TeamContact) getContact(), (TeamContact) (item.getContact()));
            }

            @Override
            public String belongsGroup() {
                return ContactGroupStrategy.GROUP_TEAM;
            }
        };
    }

    private static int compareTeam(TeamContact lhs, TeamContact rhs) {
        return TextComparator.compareIgnoreCase(lhs.getDisplayName(), rhs.getDisplayName());
    }

    /**
     * * 数据查询
     */
    private static final List<TeamContact> query(TextQuery query, int itemType) {
        List<Team> teams;
        if (itemType == ItemTypes.TEAMS.ADVANCED_TEAM) {
            teams = TeamDataCache.getInstance().getAllAdvancedTeams();
        } else if (itemType == ItemTypes.TEAMS.NORMAL_TEAM) {
            teams = TeamDataCache.getInstance().getAllNormalTeams();
        } else {
            teams = TeamDataCache.getInstance().getAllTeams();
        }

        List<TeamContact> contacts = new ArrayList<>();
        for (Team t : teams) {
            if (query == null || ContactSearch.hitTeam(t, query)) {
                contacts.add(new TeamContact(t));
            }
        }

        return contacts;
    }
}
