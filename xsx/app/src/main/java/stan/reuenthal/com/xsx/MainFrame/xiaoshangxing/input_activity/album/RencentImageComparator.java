package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.album;

/**
 * Created by FengChaoQun
 * on 2016/7/27
 */

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.photo_choosing.ImageItem;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author xiaanming
 *
 */
public class RencentImageComparator implements Comparator<ImageItem> {

    public int compare(ImageItem o1, ImageItem o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
//        if (o2.getSortLetters().equals("#")) {
//            return -1;
//        } else if (o1.getSortLetters().equals("#")) {
//            return 1;
//        } else {
//            return o1.getSortLetters().compareTo(o2.getSortLetters());
//        }
        File file1=new File(o1.imagePath);
        File file2=new File(o2.imagePath);
        if (file1.lastModified()>file2.lastModified()){
            return -1;
        }else {
            return 1;
        }
    }
}
