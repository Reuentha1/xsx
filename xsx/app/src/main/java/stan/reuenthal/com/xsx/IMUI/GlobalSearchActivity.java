package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import stan.reuenthal.com.xsx.IMpanel.ContactDataAdapter;
import stan.reuenthal.com.xsx.IMpanel.ContactDataProvider;
import stan.reuenthal.com.xsx.IMpanel.IContactDataProvider;
import stan.reuenthal.com.xsx.IMpanel.ItemTypes;
import stan.reuenthal.com.xsx.IMpanel.LabelHolder;
import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.AbsContactItem;
import stan.reuenthal.com.xsx.keyboard.ContactGroupStrategy;
import stan.reuenthal.com.xsx.keyboard.ContactItem;
import stan.reuenthal.com.xsx.keyboard.StringUtil;

/**
 * 全局搜索页面(目前仅支持通讯录搜索)
 * <p/>
 */
public class GlobalSearchActivity extends TActionBarActivity implements AdapterView.OnItemClickListener {

    private ContactDataAdapter adapter;

    private ListView lvContacts;

    private SearchView searchView;

    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GlobalSearchActivity.class);
        context.startActivity(intent);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.global_search_menu, menu);
        final MenuItem item = menu.findItem(R.id.action_search);

        getHandler().post(new Runnable() {
            @Override
            public void run() {
                MenuItemCompat.expandActionView(item);
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                finish();

                return false;
            }
        });

        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                showKeyboard(false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                if (StringUtil.isEmpty(arg0)) {
                    lvContacts.setVisibility(View.GONE);
                } else {
                    lvContacts.setVisibility(View.VISIBLE);
                }
                adapter.query(arg0);
                return true;
            }
        });
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.global_search_result);
        lvContacts = (ListView) findViewById(R.id.searchResultList);
        lvContacts.setVisibility(View.GONE);
        SearchGroupStrategy searchGroupStrategy = new SearchGroupStrategy();
        IContactDataProvider dataProvider = new ContactDataProvider(ItemTypes.FRIEND, ItemTypes.TEAM);

        adapter = new ContactDataAdapter(this, searchGroupStrategy, dataProvider);
        adapter.addViewHolder(ItemTypes.LABEL, LabelHolder.class);
        adapter.addViewHolder(ItemTypes.FRIEND, ContactHolder.class);
        adapter.addViewHolder(ItemTypes.TEAM, ContactHolder.class);

        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemClickListener(this);
        lvContacts.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                showKeyboard(false);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
            }
        });
        findViewById(R.id.global_search_root).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (searchView != null) {
            searchView.clearFocus();
        }
    }

    private static class SearchGroupStrategy extends ContactGroupStrategy {
        public static final String GROUP_FRIEND = "FRIEND";
        public static final String GROUP_TEAM = "TEAM";

        SearchGroupStrategy() {
            add(ContactGroupStrategy.GROUP_NULL, 0, "");
            add(GROUP_TEAM, 1, "群组");
            add(GROUP_FRIEND, 2, "好友");
        }

        @Override
        public String belongs(AbsContactItem item) {
            switch (item.getItemType()) {
                case ItemTypes.FRIEND:
                    return GROUP_FRIEND;
                case ItemTypes.TEAM:
                    return GROUP_TEAM;
                default:
                    return null;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AbsContactItem item = (AbsContactItem) adapter.getItem(position);
        switch (item.getItemType()) {
            case ItemTypes.TEAM: {
                SessionHelper.startTeamSession(this, ((ContactItem) item).getContact().getContactId());
                finish();
                break;
            }

            case ItemTypes.FRIEND: {
                SessionHelper.startP2PSession(this, ((ContactItem) item).getContact().getContactId());
                finish();
                break;
            }
            default:
                break;
        }
    }

}
