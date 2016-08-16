package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by FengChaoQun
 * on 2016/6/27
 */
public class LocationUtil {

    public static void bottom_FillWidth(Activity activity, Dialog dialog) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    public static void setWidth(Activity activity, Dialog dialog, int width) {
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (width); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }
}
