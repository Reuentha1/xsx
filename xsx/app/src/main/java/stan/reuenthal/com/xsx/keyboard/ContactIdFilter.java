package stan.reuenthal.com.xsx.keyboard;

import java.util.Collection;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ContactIdFilter implements ContactItemFilter {
    private static final long serialVersionUID = -6813849507791265300L;

    private final Collection<String> ids;

    private boolean exclude = true; // false means include

    public ContactIdFilter(Collection<String> ids) {
        this.ids = ids;
    }

    public ContactIdFilter(Collection<String> ids, boolean exclude) {
        this.ids = ids;
        this.exclude = exclude;
    }

    @Override
    public boolean filter(AbsContactItem item) {
        if (item instanceof ContactItem) {
            IContact contact = ((ContactItem) item).getContact();
            boolean contains = ids.contains(contact.getContactId());
            return exclude ? contains : !contains;
        }

        return false;
    }
}
