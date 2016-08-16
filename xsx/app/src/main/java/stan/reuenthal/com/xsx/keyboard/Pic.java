package stan.reuenthal.com.xsx.keyboard;

import android.graphics.Bitmap;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class Pic {
    private String path;
    private Bitmap bitmap;
    private int height;
    private int width;

    public Pic(String path, Bitmap bitmap, int height, int width) {
        this.path = path;
        this.bitmap = bitmap;
        this.height = height;
        this.width = width;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pic)) return false;

        Pic pic = (Pic) o;

        if (height != pic.height) return false;
        if (width != pic.width) return false;
        if (path != null ? !path.equals(pic.path) : pic.path != null) return false;
        return bitmap != null ? bitmap.equals(pic.bitmap) : pic.bitmap == null;

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (bitmap != null ? bitmap.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    @Override
    public String toString() {
        return "Pic{" +
                "path='" + path + '\'' +
                ", bitmap=" + bitmap +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}