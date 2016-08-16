package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import stan.reuenthal.com.xsx.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by FengChaoQun
 * on 2016/6/27
 */
public class SetStatusBarDarkMode {

    public static void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean setMeizuStatusBarDarkIcon(boolean dark, Activity activity) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static void setStatusBarDarkModeForAll(boolean darkmode, Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.setTheme(R.style.statusBarStyle);
            Log.d("setstyle", "ok");
        }
        setStatusBarDarkMode(darkmode, activity);
        setMeizuStatusBarDarkIcon(darkmode, activity);
    }
}
