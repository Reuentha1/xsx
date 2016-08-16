package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.image;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.FileUtils;

import java.io.File;
import java.util.UUID;

/**
 * Created by FengChaoQun
 * on 2016/7/11
 */
public class SaveImageTask extends AsyncTask<String, Void, File> {
    private final Context context;

    public SaveImageTask(Context context) {
        this.context = context;
    }

    @Override
    protected File doInBackground(String... params) {
        String url = params[0]; // should be easy to extend to share multiple images at once
        try {
            Log.d("dowload", "start");
            return Glide
                    .with(context)
                    .load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get() // needs to be called on background thread
                    ;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        if (result == null) {
            Log.d("dowload", "fail");
            return;
        }
        String path = result.getPath();
        File file = new File("/sdcard/XSX" + UUID.randomUUID().toString() + ".jpg");
        try {
            FileUtils.copyFileTo(result, file);
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}