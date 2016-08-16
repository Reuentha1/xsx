
package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
    private static boolean is_Debug = false;
    
    public static void setLogEnable(boolean printLog){
        is_Debug = printLog;
    }
    
    public static void toast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resId) {
        try {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void toastDebug(Context context, int resId) {
        if(!is_Debug){
            return;
        }
        try {
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 调试信息提示
     * @param context
     * @param text
     */
    public static void toastDebug(Context context, CharSequence text) {
        if(!is_Debug){
            return;
        }
        try {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void toastCenter(Context context, int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);// 设置Toast显示位置居中
        toast.show();
    }

    public static void toastLong(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void toastLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
