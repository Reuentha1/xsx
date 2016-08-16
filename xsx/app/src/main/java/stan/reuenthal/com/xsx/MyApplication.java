package stan.reuenthal.com.xsx;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.NimStrings;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stan.reuenthal.com.xsx.IMUI.DemoCache;
import stan.reuenthal.com.xsx.IMUI.MainActivity;
import stan.reuenthal.com.xsx.IMUI.Preferences;
import stan.reuenthal.com.xsx.IMUI.SessionHelper;
import stan.reuenthal.com.xsx.IMUI.UserPreferences;
import stan.reuenthal.com.xsx.IMpanel.FriendDataCache;
import stan.reuenthal.com.xsx.IMpanel.MsgViewHolderThumbBase;
import stan.reuenthal.com.xsx.IMpanel.NimUserInfoCache;
import stan.reuenthal.com.xsx.IMpanel.TeamDataCache;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.MyLog;
import stan.reuenthal.com.xsx.keyboard.ContactProvider;
import stan.reuenthal.com.xsx.keyboard.ImageLoaderKit;
import stan.reuenthal.com.xsx.keyboard.NimUIKit;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MyApplication extends Application {

    private Map<String,Activity> mList=new HashMap<String, Activity>();
    private int activityCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        DemoCache.setContext(this);

        NIMClient.init(this, getLoginInfo(), getOptions());

        ExtraOptions.provide();

        // crash handler
        AppCrashHandler.getInstance(this);


        if (inMainProcess()) {
            // init pinyin
            //PinYin.init(this);
            //PinYin.validate();

            // 初始化UIKit模块
            initUIKit();

            // 注册群通知消息过滤器
            //registerTeamNotificationFilter();

            // 初始化消息提醒
            NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

            // 注册网络通话来电
            //enableAVChat();

            // 注册白板会话
            //enableRTS();

            // 注册语言变化监听
            registerLocaleReceiver(true);
        }

         /*
        **describe:集中监视Activity生命周期
        */
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
                MyLog.d(activity,"created");
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyLog.d(activity,"resumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityCount--;
                MyLog.d(activity, "destroyed");
            }
        });

    }

    private LoginInfo getLoginInfo() {
        String account = Preferences.getUserAccount();
        String token = Preferences.getUserToken();

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
            DemoCache.setAccount(account.toLowerCase());
            return new LoginInfo(account, token);
        } else {
            return null;
        }
    }

    private SDKOptions getOptions() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给SDK完成，需要添加以下配置。
        StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
        if (config == null) {
            config = new StatusBarNotificationConfig();
        }
        // 点击通知需要跳转到的界面
        config.notificationEntrance = MainActivity.class;
        //config.notificationSmallIconId = R.drawable.ic_stat_notify_msg;

        // 通知铃声的uri字符串
        //config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";

        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;

        options.statusBarNotificationConfig = config;
        DemoCache.setNotificationConfig(config);
        UserPreferences.setStatusConfig(config);

        // 配置保存图片，文件，log等数据的目录
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim";
        options.sdkStorageRootPath = sdkPath;

        // 配置数据库加密秘钥
        options.databaseEncryptKey = "NETEASE";

        // 配置是否需要预下载附件缩略图
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小，
        options.thumbnailSize = MsgViewHolderThumbBase.getImageMaxEdge();

        // 用户信息提供者
        options.userInfoProvider = infoProvider;

        // 定制通知栏提醒文案（可选，如果不定制将采用SDK默认文案）
        options.messageNotifierCustomization = messageNotifierCustomization;

        return options;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public boolean inMainProcess() {
        Log.d("dddd","2");
        String packageName = getPackageName();
        String processName = SystemUtil.getProcessName(this);
        return packageName.equals(processName);
    }

    /**
     * 群通知消息过滤器（如果过滤则该消息不存储不上报）
     */
    /*private void registerTeamNotificationFilter() {
        NIMClient.getService(TeamService.class).registerTeamNotificationFilter(new TeamNotificationFilter() {
            @Override
            public boolean shouldIgnore(IMMessage message) {
                if (UserPreferences.getMsgIgnore()
                        && message.getMsgType() == MsgTypeEnum.notification
                        && message.getAttachment() != null
                        && message.getAttachment() instanceof UpdateTeamAttachment) {
                    UpdateTeamAttachment attachment = (UpdateTeamAttachment) message.getAttachment();
                    for (Map.Entry<TeamFieldEnum, Object> field : attachment.getUpdatedFields().entrySet()) {
                        if (field.getKey() == TeamFieldEnum.ICON) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }*/

    /**
     * 音视频通话配置与监听
     */
    /*private void enableAVChat() {
        registerAVChatIncomingCallObserver(true);
    }

    private void registerAVChatIncomingCallObserver(boolean register) {
        AVChatManager.getInstance().observeIncomingCall(new Observer<AVChatData>() {
            @Override
            public void onEvent(AVChatData data) {
                String extra = data.getExtra();
                Log.e("Extra", "Extra Message->" + extra);
                // 有网络来电打开AVChatActivity
                AVChatProfile.getInstance().setAVChatting(true);
                AVChatActivity.launch(DemoCache.getContext(), data, AVChatActivity.FROM_BROADCASTRECEIVER);
            }
        }, register);
    }*/

    /**
     * 白板实时时会话配置与监听
     */
    /*private void enableRTS() {
        registerRTSIncomingObserver(true);
    }*/


    /*private void registerRTSIncomingObserver(boolean register) {
        RTSManager.getInstance().observeIncomingSession(new Observer<RTSData>() {
            @Override
            public void onEvent(RTSData rtsData) {
                RTSActivity.incomingSession(DemoCache.getContext(), rtsData, RTSActivity.FROM_BROADCAST_RECEIVER);
            }
        }, register);
    }*/

    private void registerLocaleReceiver(boolean register) {
        if (register) {
            updateLocale();
            IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);
            registerReceiver(localeReceiver, filter);
        } else {
            unregisterReceiver(localeReceiver);
        }
    }

    private BroadcastReceiver localeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
                updateLocale();
            }
        }
    };

    private void updateLocale() {
        NimStrings strings = new NimStrings();
        strings.status_bar_multi_messages_incoming = getString(R.string.nim_status_bar_multi_messages_incoming);
        strings.status_bar_image_message = getString(R.string.nim_status_bar_image_message);
        strings.status_bar_audio_message = getString(R.string.nim_status_bar_audio_message);
        strings.status_bar_custom_message = getString(R.string.nim_status_bar_custom_message);
        strings.status_bar_file_message = getString(R.string.nim_status_bar_file_message);
        //strings.status_bar_location_message = getString(R.string.nim_status_bar_location_message);
        strings.status_bar_notification_message = getString(R.string.nim_status_bar_notification_message);
        strings.status_bar_ticker_text = getString(R.string.nim_status_bar_ticker_text);
        strings.status_bar_unsupported_message = getString(R.string.nim_status_bar_unsupported_message);
        strings.status_bar_video_message = getString(R.string.nim_status_bar_video_message);
        strings.status_bar_hidden_message_content = getString(R.string.nim_status_bar_hidden_msg_content);
        NIMClient.updateStrings(strings);
    }

    private void initUIKit() {
        // 初始化，需要传入用户信息提供者
        NimUIKit.init(this, infoProvider, contactProvider);

        // 设置地理位置提供者。如果需要发送地理位置消息，该参数必须提供。如果不需要，可以忽略。
        //NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // 会话窗口的定制初始化。
        SessionHelper.init();

        // 通讯录列表定制初始化
        stan.reuenthal.com.xsx.IMUI.ContactHelper.init();
    }

    private UserInfoProvider infoProvider = new UserInfoProvider() {
        @Override
        public UserInfo getUserInfo(String account) {
            UserInfo user = NimUserInfoCache.getInstance().getUserInfo(account);
            if (user == null) {
                NimUserInfoCache.getInstance().getUserInfoFromRemote(account, null);
            }

            return user;
        }

        @Override
        public int getDefaultIconResId() {
            return R.mipmap.avatar_def;
        }

        @Override
        public Bitmap getTeamIcon(String teamId) {
            Drawable drawable = getResources().getDrawable(R.mipmap.nim_avatar_group);
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }

            return null;
        }

        @Override
        public Bitmap getAvatarForMessageNotifier(String account) {
            /**
             * 注意：这里最好从缓存里拿，如果读取本地头像可能导致UI进程阻塞，导致通知栏提醒延时弹出。
             */
            UserInfo user = getUserInfo(account);
            return (user != null) ? ImageLoaderKit.getNotificationBitmapFromCache(user) : null;
        }

        @Override
        public String getDisplayNameForMessageNotifier(String account, String sessionId, SessionTypeEnum sessionType) {
            String nick = null;
            if (sessionType == SessionTypeEnum.P2P) {
                nick = NimUserInfoCache.getInstance().getAlias(account);
            } else if (sessionType == SessionTypeEnum.Team) {
                nick = TeamDataCache.getInstance().getTeamNick(sessionId, account);
                if (TextUtils.isEmpty(nick)) {
                    nick = NimUserInfoCache.getInstance().getAlias(account);
                }
            }
            // 返回null，交给sdk处理。如果对方有设置nick，sdk会显示nick
            if (TextUtils.isEmpty(nick)) {
                return null;
            }

            return nick;
        }
    };

    private ContactProvider contactProvider = new ContactProvider() {
        @Override
        public List<UserInfoProvider.UserInfo> getUserInfoOfMyFriends() {
            List<NimUserInfo> nimUsers = NimUserInfoCache.getInstance().getAllUsersOfMyFriend();
            List<UserInfoProvider.UserInfo> users = new ArrayList<>(nimUsers.size());
            if (!nimUsers.isEmpty()) {
                users.addAll(nimUsers);
            }

            return users;
        }

        @Override
        public int getMyFriendsCount() {
            return FriendDataCache.getInstance().getMyFriendCounts();
        }

        @Override
        public String getUserDisplayName(String account) {
            return NimUserInfoCache.getInstance().getUserDisplayName(account);
        }
    };

    private MessageNotifierCustomization messageNotifierCustomization = new MessageNotifierCustomization() {
        @Override
        public String makeNotifyContent(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }

        @Override
        public String makeTicker(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }
    };

    /*
    **describe:添加Activity到Map中
    */

    public void addActivity(Activity activity){
//        mList.add(activity);
        mList.put(activity.toString(),activity);
        activityCount++;
    }

    /*
    **describe:完全退出APP
    */
    public void exit() {
        try {
//            for (Activity activity : mList) {
//                if (activity != null)
//                    activity.finish();
//            }
            for(Map.Entry<String, Activity> entry:mList.entrySet()){
                entry.getValue().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    **describe:finish某个activity
    */
    public void finish_activity(String tag){
        if (mList.containsKey(tag)){
            mList.get(tag).finish();
        }
    }
}
