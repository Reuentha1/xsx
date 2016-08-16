package stan.reuenthal.com.xsx.IMUI;

import android.content.Context;

import java.util.List;

import stan.reuenthal.com.xsx.IMpanel.TAdapter;
import stan.reuenthal.com.xsx.IMpanel.TAdapterDelegate;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class FileBrowserAdapter extends TAdapter {

    public static class FileManagerItem {
        private String name;
        private String path;

        public FileManagerItem(String name, String path) {
            this.name = name;
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

    }

    public FileBrowserAdapter(Context context, List<?> items, TAdapterDelegate delegate) {
        super(context, items, delegate);
    }
}
