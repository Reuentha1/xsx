package stan.reuenthal.com.xsx.IMUI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import stan.reuenthal.com.xsx.IMpanel.TViewHolder;
import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class FileBrowserViewHolder extends TViewHolder{
    private ImageView fileImage;
    private TextView fileName;
    private FileBrowserAdapter.FileManagerItem fileItem;

    private Bitmap directoryBitmap;
    private Bitmap fileBitmap;

    @Override
    protected int getResId() {
        return R.layout.file_browser_list_item;
    }

    @Override
    protected void inflate() {
        directoryBitmap = BitmapFactory.decodeResource(DemoCache.getContext().getResources(),R.mipmap.directory);
        fileBitmap = BitmapFactory.decodeResource(DemoCache.getContext().getResources(), R.mipmap.file);
        fileImage = (ImageView) view.findViewById(R.id.file_image);
        fileName = (TextView) view.findViewById(R.id.file_name);
    }

    @Override
    protected void refresh(Object item) {
        fileItem = (FileBrowserAdapter.FileManagerItem) item;

        File f = new File(fileItem.getPath());
        if(fileItem.getName().equals("@1")) {
            fileName.setText("/");
            fileImage.setImageBitmap(directoryBitmap);
        } else if(fileItem.getName().equals("@2")) {
            fileName.setText("..");
            fileImage.setImageBitmap(directoryBitmap);
        } else {
            fileName.setText(fileItem.getName());
            if(f.isDirectory()) {
                fileImage.setImageBitmap(directoryBitmap);
            } else if (f.isFile()) {
                fileImage.setImageBitmap(fileBitmap);
            }
        }

    }
}
