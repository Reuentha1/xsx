package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.photoChoosing;

import android.content.Intent;
import android.os.Bundle;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/26.
 */
public class PhotoChoosingActivity extends BaseActivity {
    private String folderName;
    private int resultCode=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_choose);
        mFragmentManager.beginTransaction()
                .replace(R.id.photoChooseContent, new AlbumFragment())
                .commit();
        resultCode=getIntent().getIntExtra("TYPE",0);
    }


    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public void finish() {
        if (resultCode!=0){
//            ArrayList<String> selectPicture=new ArrayList<String>();
//            for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
//                selectPicture.add(Bimp.tempSelectBitmap.get(i).imagePath);
//            }
//            Log.d("726",selectPicture.toString());
            Intent intent=new Intent();
//            intent.putExtra(InputActivity.SELECT_IMAGE_URLS,selectPicture);
            setResult(resultCode,intent);
        }
        super.finish();
    }
}
