
package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.showheadimg.ShowHeadimgFragment;


public class CommonUtils {
    /**
     * 防止重复点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 检测相机是否存在 s
     */
    public static boolean isExistCamera(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        }
        return false;
    }
    
    /**
     * 裁剪图片
     */
    public static void cutPhoto(Activity activity, Uri uri, boolean isHeadPic, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        if (isHeadPic) {
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);
            
            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FileUtil.getHeadPhotoFileTemp()));
            intent.putExtra("*+---", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("noFaceDetection", true);
        } else {
            // 是否保留比例
//            intent.putExtra("scale", "true");
//            intent.putExtra("output", Uri.fromFile(FileUtil.getWallPaperFile()));
            intent.putExtra("aspectX", width / 10);
            intent.putExtra("aspectY", height / 10);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);

            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FileUtil.getHeadPhotoFileTemp()));
            intent.putExtra("*+---", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("noFaceDetection", true);


        }
        ActivityUtil.startActivityForResultIntent(activity, intent, null,
                ShowHeadimgFragment.ACTIVITY_MODIFY_PHOTO_REQUESTCODE, false);
    }


}
