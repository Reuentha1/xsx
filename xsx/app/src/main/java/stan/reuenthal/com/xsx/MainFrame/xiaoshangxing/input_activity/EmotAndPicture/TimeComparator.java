package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotAndPicture;

/**
 * Created by FengChaoQun
 * on 2016/7/27
 */

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author xiaanming
 *
 */
public class TimeComparator implements Comparator<File> {

    public int compare(File o1, File o2) {
        //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
//        if (o2.getSortLetters().equals("#")) {
//            return -1;
//        } else if (o1.getSortLetters().equals("#")) {
//            return 1;
//        } else {
//            return o1.getSortLetters().compareTo(o2.getSortLetters());
//        }
        if (o1.lastModified()>o2.lastModified()){
            return -1;
        }else {
            return 1;
        }
    }
}
