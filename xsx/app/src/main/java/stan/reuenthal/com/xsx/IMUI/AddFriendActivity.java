package stan.reuenthal.com.xsx.IMUI;

/**
 * Created by G.J.Lee on 八月.
 * Email finalfantasy@games.com
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import stan.reuenthal.com.xsx.IMpanel.NimUserInfoCache;
import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.keyboard.ActionBarUtil;
import stan.reuenthal.com.xsx.keyboard.DialogMaker;
import stan.reuenthal.com.xsx.keyboard.EasyAlertDialogHelper;

/**
 * 添加好友页面
 */
public class AddFriendActivity extends TActionBarActivity {

    private ClearableEditTextWithIcon searchEdit;

    public static final void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddFriendActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_activity);
        setTitle(R.string.add_buddy);
        findViews();
        initActionbar();
    }

    private void findViews() {
        searchEdit = findView(R.id.search_friend_edit);
        searchEdit.setDeleteImage(R.mipmap.nim_grey_delete_icon);
    }

    private void initActionbar() {
        ActionBarUtil.addRightClickableTextViewOnActionBar(this, R.string.search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchEdit.getText().toString())) {
                    Toast.makeText(AddFriendActivity.this, R.string.not_allow_empty, Toast.LENGTH_SHORT).show();
                } else if (searchEdit.getText().toString().equals(DemoCache.getAccount())) {
                    Toast.makeText(AddFriendActivity.this, R.string.add_friend_self_tip, Toast.LENGTH_SHORT).show();
                } else {
                    query();
                }
            }
        });
    }

    private void query() {
        DialogMaker.showProgressDialog(this, null, false);
        final String account = searchEdit.getText().toString().toLowerCase();
        NimUserInfoCache.getInstance().getUserInfoFromRemote(account, new RequestCallback<NimUserInfo>() {
            @Override
            public void onSuccess(NimUserInfo user) {
                DialogMaker.dismissProgressDialog();
                if (user == null) {
                    EasyAlertDialogHelper.showOneButtonDiolag(AddFriendActivity.this, R.string.user_not_exsit,
                            R.string.user_tips, R.string.ok, false, null);
                } else {
                    UserProfileActivity.start(AddFriendActivity.this, account);
                }
            }

            @Override
            public void onFailed(int code) {
                DialogMaker.dismissProgressDialog();
                if (code == 408) {
                    Toast.makeText(AddFriendActivity.this, R.string.network_is_not_available, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddFriendActivity.this, "on failed:" + code, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onException(Throwable exception) {
                DialogMaker.dismissProgressDialog();
                Toast.makeText(AddFriendActivity.this, "on exception:" + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
