package stan.reuenthal.com.xsx.IMpanel;

import java.util.ArrayList;
import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ContactDataProvider implements IContactDataProvider {

    private int[] itemTypes;

    public ContactDataProvider(int... itemTypes) {
        this.itemTypes = itemTypes;
    }

    @Override
    public List<AbsContactItem> provide(TextQuery query) {
        List<AbsContactItem> data = new ArrayList<>();

        for (int itemType : itemTypes) {
            data.addAll(provide(itemType, query));
        }

        return data;
    }

    private final List<AbsContactItem> provide(int itemType, TextQuery query) {
        switch (itemType) {
            case ItemTypes.FRIEND:
                return UserDataProvider.provide(query);
            case ItemTypes.TEAM:
            case ItemTypes.TEAMS.ADVANCED_TEAM:
            case ItemTypes.TEAMS.NORMAL_TEAM:
                return TeamDataProvider.provide(query, itemType);
            default:
                return new ArrayList<>();
        }
    }
}
