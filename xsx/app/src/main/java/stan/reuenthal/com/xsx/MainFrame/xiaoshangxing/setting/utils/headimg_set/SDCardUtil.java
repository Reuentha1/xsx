
package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;

public class SDCardUtil {

    private static final String APP_DIR_NAME = "durian";

    public static String getImageCachePath() {
        String path = getCacheDir() + "image/";
        mkdirs(path);
        return path;
    }

    public static String getThmbnailsCachePath() {
        return getCacheDir() + "thumbnails/";
    }

    /**
     * SdCard cache目录
     *
     * @return
     */
    public static String getCacheDir() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/Android/data/com.storm.durian/cache/";
        mkdirs(path);
        return path;
    }

    public synchronized  static  void mkdirs(String dir){
        File filedir = new File(dir);
        if (filedir != null && !filedir.exists()) {
            filedir.mkdirs();
        }
    }


    /**
     * SdCard cache目录, 处理Coolpad 手机的/udisk情况,
     *
     * @param context
     * @return
     */
    /*public static String getSDCardDir(Context context) {
        String path = null;
        boolean bExists = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (bExists) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            ArrayList<String> pathList = FileOperationUtils.getSdPaths(context);
            for (String pathStr : pathList) {
                if (new File(pathStr).exists() && new File(pathStr).canWrite()) {
                    path = pathStr;
                    break;
                }
            }
        }

        return path + "/Android/data/com.storm.smart/cache/";
    }*/

    public static boolean isExsit() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * @param type The type of storage directory to return.  Should be one of
     *             {@link #DIRECTORY_MUSIC}, {@link #DIRECTORY_PODCASTS},
     *             {@link #DIRECTORY_RINGTONES}, {@link #DIRECTORY_ALARMS},
     *             {@link #DIRECTORY_NOTIFICATIONS}, {@link #DIRECTORY_PICTURES},
     *             {@link #DIRECTORY_MOVIES}, {@link #DIRECTORY_DOWNLOADS}, or
     *             {@link #DIRECTORY_DCIM}.  May not be null.
     */
    public static String getExternalStoragePublicDirectory(String type) {
        String path;
        if (!isExsit()) {
            return "";
        }

        return Environment.getExternalStoragePublicDirectory(type).getPath();
    }

    /**
     * 获取生成的缩略图路径
     *
     * @param path
     * @return
     */
    public static String getDCIMThumbPath(String path) {
        return getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/.thumbnails/" + MD5.Md5Encode(path) + ".png";
    }


    public static String getExternalStoragePath() {
        if (isExsit()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "/";
    }

    public static String getMntPath() {
        String mntPath = getExternalStoragePath();
        if (!"/".equals(mntPath) && mntPath.lastIndexOf("/") != -1
                && !mntPath.endsWith("/storage/emulated/0")) {
            mntPath = mntPath.substring(0, mntPath.lastIndexOf("/") + 1);
        }

        return mntPath;
    }

    public static String getMntPath2() {
        String mntPath = getExternalStoragePath();
        if (!"/".equals(mntPath) && mntPath.lastIndexOf("/") != -1
                && !mntPath.endsWith("/storage/emulated/0")) {
            mntPath = mntPath.substring(0, mntPath.lastIndexOf("/"));
        }

        return mntPath;
    }


    /**
     * 截屏缓存
     *
     * @return
     */
    public static String getScreenShotExternalStorageCacheDir() {
        String path = "";
        if (!TextUtils.isEmpty(getCacheDir())) {
            path = getCacheDir() + "screen_shoot/";
        }
        return path;
    }


    /**
     * 获取path路径的总空间大小
     *
     * @param path
     * @return
     */
    public static long getTotalCapacityInPath(String path) {
        long capacity = 0;
        try {
            StatFs stat = new StatFs(path);
            long blockSize = stat.getBlockSize();
            long blockCount = stat.getBlockCount();
            capacity = blockSize * blockCount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return capacity;
    }


    public static double[] getSDCardCapacityInfo(String path) {
        double[] capacitys = new double[]{
                0.0, 0.0, 0.0
        };
        String state = Environment.getExternalStorageState();
        try {
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                StatFs stat = new StatFs(path);

                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getBlockCount();
                long availaBlock = stat.getAvailableBlocks();

                double totalCapacity = availableBlocks * blockSize;
                double vailaleCapacity = availaBlock * blockSize;
                capacitys[0] = totalCapacity;
                capacitys[1] = vailaleCapacity;
                capacitys[2] = totalCapacity - vailaleCapacity;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return capacitys;
    }

    public static double getSdcardAvailableSpace() {
        String state = Environment.getExternalStorageState();
        try {
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());

                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getBlockCount();
                long availaBlock = stat.getAvailableBlocks();

                double vailaleCapacity = availaBlock * blockSize;
                return vailaleCapacity;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
