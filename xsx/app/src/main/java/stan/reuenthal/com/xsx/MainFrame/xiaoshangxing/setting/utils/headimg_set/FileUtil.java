
package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    private static final String TAG = "FileUtil";
    // 用户头像保存位置
    private final static String HEADPHOTO_PATH = "/Android/data/com.soulrelay.uploadpic/";

    // 剪切头像时临时保存头像名字，完成或取消时删除
    public final static String HEADPHOTO_NAME_TEMP = "user_photo_temp.jpg";
    //拍照临时存取图片
    public final static String HEADPHOTO_NAME_RAW = "user_photo_raw.jpg";

    // 剪切壁纸图片
    public final static String WALLPAPER = "wallpaper.jpg";


    public static String getCropPath(String path) {
        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_REMOVED.equals(storageState)) {
            return null;
        }

        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + HEADPHOTO_PATH + "cache" + File.separator;

        String s = MD5.Md5Encode(path)+".jpg";
        return dirPath + s;
    }

    /**
     * 用户头像保存路径
     */
    public static String getHeadPhotoDir() {
        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_REMOVED.equals(storageState)) {
            return null;
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + HEADPHOTO_PATH;
        SDCardUtil.mkdirs(path);
        return path;
    }

    /**
     * 剪切头像时临时保存头像名字，完成或取消时删除
     */
    public static File getHeadPhotoFileTemp() {
        File file = new File(getHeadPhotoDir() + HEADPHOTO_NAME_TEMP);
        return file;
    }

    /**
     * 剪切头像时临时保存头像名字，完成或取消时删除(用于拍照时存储原始图片)
     */
    public static File getHeadPhotoFileRaw() {
        File file = new File(getHeadPhotoDir() + HEADPHOTO_NAME_RAW);
        return file;
    }

    /**
     * 获取剪切壁纸图片
     */
    public static File getWallPaperFile() {
        File file = new File(getHeadPhotoDir() + WALLPAPER);
        return file;
    }

    public static void saveCutBitmapForCache(Context context, Bitmap bitmap) {
        File file = new File(getHeadPhotoDir() + /*File.separator +*/ HEADPHOTO_NAME_RAW);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }
    
    /**
     * Delete the file/dir from the local disk
     * 
     */
    public static boolean deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            Log.w(TAG, "the file is not exist while delete the file");
            return false;
        }

        return deleteDir(file);
    }

    /**
     * Delete the file from the local disk
     * 
     * @param dir
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                // 递归删除目录中的子目录下
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }

        }
        if (!dir.canRead() || !dir.canWrite()) {
            Log.w(TAG, "has no permission to can or write while delete the file");
            return false;
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 删除临时文件（拍照的原始图片以及临时文件）
     * @param path
     */
    public static void deleteTempAndRaw() {
       deleteFile(FileUtil.getHeadPhotoDir()  + FileUtil.HEADPHOTO_NAME_TEMP);
       deleteFile(FileUtil.getHeadPhotoDir()  + FileUtil.HEADPHOTO_NAME_RAW);
    }

}
