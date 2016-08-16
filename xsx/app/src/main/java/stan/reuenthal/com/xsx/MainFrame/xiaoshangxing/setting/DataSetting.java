package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting;

import android.content.Context;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;

/**
 * Created by 15828 on 2016/7/16.
 */
public class DataSetting {
    /**
     * 新消息通知——是否开启接受新消息通知
     *
     * @return
     */
    public static boolean IsAccepted(Context context) {
        return (boolean) SPUtils.get(context, "newnotice_accept", false);
    }

    /**
     * 新消息通知——是否开启通知新消息详情
     *
     * @return
     */
    public static boolean IsInformed(Context context) {
        return (boolean) SPUtils.get(context, "newnotice_inform", false);
    }

    /**
     * 新消息通知——是否开启声音
     *
     * @return
     */
    public static boolean IsSounded(Context context) {
        return (boolean) SPUtils.get(context, "newnotice_sound", false);
    }

    /**
     * 新消息通知——是否开启振动
     *
     * @return
     */
    public static boolean IsVibrationed(Context context) {
        return (boolean) SPUtils.get(context, "newnotice_vibration", false);
    }

    /**
     * 新消息通知——是否开启校友圈更新
     *
     * @return
     */
    public static boolean IsRenewed(Context context) {
        return (boolean) SPUtils.get(context, "newnotice_renew", false);
    }

    /**
     * 隐私——是否开启允许查看十张图片
     *
     * @return
     */
    public static boolean IsAllowedTenPicture(Context context) {
        return (boolean) SPUtils.get(context, "privacy_allow", false);
    }

    /**
     * 通用——是否开启听筒模式
     *
     * @return
     */
    public static boolean IsReceiveMode(Context context) {
        return (boolean) SPUtils.get(context, "currency_receivemode", false);
    }


    /**
     * 新消息通知——消息免打扰列表
     *
     * @return
     */
    public static Integer NoDisturbList(Context context) {
        return (Integer) SPUtils.get(context, "nodisturbList", 0);
    }


    /**
     * 资料设置——是否设为星标好友
     *
     * @return
     */
    public static boolean IsStarMarkfriends(Context context) {
        return (boolean) SPUtils.get(context, "starMarkfriends", false);
    }

    /**
     * 资料设置——是否暗恋他
     *
     * @return
     */
    public static boolean IsCrush(Context context) {
        return (boolean) SPUtils.get(context, "crush", false);
    }

    /**
     * 资料设置——是否不让他看我的校友圈
     *
     * @return
     */
    public static boolean IsBuKanWo(Context context) {
        return (boolean) SPUtils.get(context, "bukanwo", false);
    }

    /**
     * 资料设置——是否不看他的校友圈
     *
     * @return
     */
    public static boolean IsBuKanTa(Context context) {
        return (boolean) SPUtils.get(context, "bukanta", false);
    }

    /**
     * 资料设置——是否加入黑名单
     *
     * @return
     */
    public static boolean IsAddToBlackList(Context context) {
        return (boolean) SPUtils.get(context, "addToBlackList", false);
    }


    /**
     * 个人资料——是否已留心
     *
     * @return
     */
    public static boolean IsFocused(Context context) {
        return (boolean) SPUtils.get(context, "focus", false);
    }




}
