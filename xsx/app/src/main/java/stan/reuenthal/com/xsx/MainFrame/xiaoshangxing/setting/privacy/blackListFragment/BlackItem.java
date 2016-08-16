package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.privacy.blackListFragment;

import android.graphics.Bitmap;

/**
 * Created by 15828 on 2016/7/16.
 */
public class BlackItem {
    private String name;
    private Bitmap bitmap;

    public BlackItem(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
