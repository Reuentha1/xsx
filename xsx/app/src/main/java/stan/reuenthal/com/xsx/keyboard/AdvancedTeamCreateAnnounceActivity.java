package stan.reuenthal.com.xsx.keyboard;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.team.TeamService;
import com.netease.nimlib.sdk.team.constant.TeamFieldEnum;
import com.netease.nimlib.sdk.team.model.Team;

import stan.reuenthal.com.xsx.IMpanel.SimpleCallback;
import stan.reuenthal.com.xsx.IMpanel.TActionBarActivity;
import stan.reuenthal.com.xsx.IMpanel.TeamDataCache;
import stan.reuenthal.com.xsx.R;

/**
 * 创建群公告界面
 */
public class AdvancedTeamCreateAnnounceActivity extends TActionBarActivity {

    // constant
    private final static String EXTRA_TID = "EXTRA_TID";

    // data
    private String teamId;
    private String announce;

    // view
    private EditText teamAnnounceTitle;
    private EditText teamAnnounceContent;

    public static void startActivityForResult(Activity activity, String teamId, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(activity, AdvancedTeamCreateAnnounceActivity.class);
        intent.putExtra(EXTRA_TID, teamId);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nim_advanced_team_create_announce);
        setTitle(R.string.team_annourcement);

        parseIntentData();
        findViews();
        initActionbar();
    }

    private void parseIntentData() {
        teamId = getIntent().getStringExtra(EXTRA_TID);
    }

    private void findViews() {
        teamAnnounceTitle = (EditText) findViewById(R.id.team_announce_title);
        teamAnnounceContent = (EditText) findViewById(R.id.team_announce_content);
        teamAnnounceTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        teamAnnounceContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1024)});
    }

    private void initActionbar() {
        ActionBarUtil.addRightClickableTextViewOnActionBar(this, R.string.save, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAnnounceData();
            }
        });
    }

    private void requestAnnounceData() {
        if (!NetworkUtil.isNetAvailable(this)) {
            Toast.makeText(this, R.string.network_is_not_available, Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(teamAnnounceTitle.getText().toString())) {
            Toast.makeText(AdvancedTeamCreateAnnounceActivity.this, R.string.team_announce_notice, Toast.LENGTH_SHORT).show();
            return;
        }

        ActionBarUtil.setTextViewEnable(this, false);
        // 请求群信息
        Team t = TeamDataCache.getInstance().getTeamById(teamId);
        if (t != null) {
            updateTeamData(t);
            updateAnnounce();
        } else {
            TeamDataCache.getInstance().fetchTeamById(teamId, new SimpleCallback<Team>() {
                @Override
                public void onResult(boolean success, Team result) {
                    if (success && result != null) {
                        updateTeamData(result);
                        updateAnnounce();
                    } else {
                        ActionBarUtil.setTextViewEnable(AdvancedTeamCreateAnnounceActivity.this, true);
                    }
                }
            });
        }
    }

    /**
     * 获得最新公告内容
     *
     * @param team 群
     */
    private void updateTeamData(Team team) {
        if (team == null) {
            Toast.makeText(this, getString(R.string.team_not_exist), Toast.LENGTH_SHORT).show();
            showKeyboard(false);
            finish();
        } else {
            announce = team.getAnnouncement();
        }
    }

    /**
     * 创建公告更新到服务器
     */
    private void updateAnnounce() {
        String announcement = AnnouncementHelper.makeAnnounceJson(announce, teamAnnounceTitle.getText().toString(),
                teamAnnounceContent.getText().toString());
        NIMClient.getService(TeamService.class).updateTeam(teamId, TeamFieldEnum.Announcement, announcement).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                DialogMaker.dismissProgressDialog();
                setResult(Activity.RESULT_OK);
                showKeyboard(false);
                finish();
                Toast.makeText(AdvancedTeamCreateAnnounceActivity.this, R.string.update_success, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int code) {
                DialogMaker.dismissProgressDialog();
                ActionBarUtil.setTextViewEnable(AdvancedTeamCreateAnnounceActivity.this, true);
                Toast.makeText(AdvancedTeamCreateAnnounceActivity.this, String.format(getString(R.string.update_failed), code), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable exception) {
                DialogMaker.dismissProgressDialog();
                ActionBarUtil.setTextViewEnable(AdvancedTeamCreateAnnounceActivity.this, true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        showKeyboard(false);
        super.onBackPressed();
    }
}
