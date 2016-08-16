package stan.reuenthal.com.xsx.IMpanel;

import android.content.Context;

import com.netease.nimlib.sdk.uinfo.UserInfoProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import stan.reuenthal.com.xsx.keyboard.AbsContactItem;
import stan.reuenthal.com.xsx.keyboard.ContactGroupStrategy;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.IContact;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ContactSelectAdapter extends ContactDataAdapter {
    private HashSet<String> selects = new HashSet<String>();

    public ContactSelectAdapter(Context context,
                                ContactGroupStrategy groupStrategy, IContactDataProvider dataProvider) {
        super(context, groupStrategy, dataProvider);
    }

    public final void setAlreadySelectedAccounts(List<String> accounts) {
        selects.addAll(accounts);
    }

    public final List<ContactItem> getSelectedItem() {
        if (selects.isEmpty()) {
            return null;
        }

        List<ContactItem> res = new ArrayList<>();
        for (String account : selects) {
            final UserInfoProvider.UserInfo user = NimUIKit.getUserInfoProvider().getUserInfo(account);
            if (user != null) {
                res.add(new ContactItem(ContactHelper.makeContactFromUserInfo(user), ItemTypes.FRIEND));
            }
        }

        return res;
    }

    public final void selectItem(int position) {
        AbsContactItem item = (AbsContactItem) getItem(position);
        if (item != null && item instanceof ContactItem) {
            selects.add(((ContactItem) item).getContact().getContactId());
        }
        notifyDataSetChanged();
    }

    public final boolean isSelected(int position) {
        AbsContactItem item = (AbsContactItem) getItem(position);
        if (item != null && item instanceof ContactItem) {
            return selects.contains(((ContactItem) item).getContact().getContactId());
        }
        return false;
    }

    public final void cancelItem(int position) {
        AbsContactItem item = (AbsContactItem) getItem(position);
        if (item != null && item instanceof ContactItem) {
            selects.remove(((ContactItem) item).getContact().getContactId());
        }
        notifyDataSetChanged();
    }

    public final void cancelItem(IContact iContact) {
        selects.remove(iContact.getContactId());
    }
}
