package stan.reuenthal.com.xsx.IMUI;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nimlib.sdk.team.model.Team;

import stan.reuenthal.com.xsx.IMpanel.AbsContactViewHolder;
import stan.reuenthal.com.xsx.IMpanel.ContactDataAdapter;
import stan.reuenthal.com.xsx.IMpanel.HeadImageView;
import stan.reuenthal.com.xsx.IMpanel.TeamDataCache;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.IContact;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class ContactHolder extends AbsContactViewHolder<ContactItem> {

    protected HeadImageView head;

    protected TextView name;

    protected TextView desc;

    protected RelativeLayout headLayout;

    @Override
    public void refresh(ContactDataAdapter adapter, int position, final ContactItem item) {
        // contact info
        final IContact contact = item.getContact();
        if (contact.getContactType() == IContact.Type.Friend) {
            head.loadBuddyAvatar(contact.getContactId());
        } else {
            Team team = TeamDataCache.getInstance().getTeamById(contact.getContactId());
            head.loadTeamIconByTeam(team);
        }
        name.setText(contact.getDisplayName());
        headLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contact.getContactType() == IContact.Type.Friend) {
                    if (NimUIKit.getContactEventListener() != null) {
                        NimUIKit.getContactEventListener().onAvatarClick(context, item.getContact().getContactId());
                    }
                }
            }
        });

        // query result
        desc.setVisibility(View.GONE);
        /*
        TextQuery query = adapter.getQuery();
        HitInfo hitInfo = query != null ? ContactSearch.hitInfo(contact, query) : null;
        if (hitInfo != null && !hitInfo.text.equals(contact.getDisplayName())) {
            desc.setVisibility(View.VISIBLE);
        } else {
            desc.setVisibility(View.GONE);
        }
        */
    }

    @Override
    public View inflate(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.nim_contacts_item, null);

        headLayout = (RelativeLayout) view.findViewById(R.id.head_layout);
        head = (HeadImageView) view.findViewById(R.id.contacts_item_head);
        name = (TextView) view.findViewById(R.id.contacts_item_name);
        desc = (TextView) view.findViewById(R.id.contacts_item_desc);

        return view;
    }
}
