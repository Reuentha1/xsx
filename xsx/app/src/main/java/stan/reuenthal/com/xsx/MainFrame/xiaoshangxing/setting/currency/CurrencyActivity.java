package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.currency;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.currency.chatBackground.ChatBackgroundFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.currency.chooseBackgroundFragment.ChooseBackgroundFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.currency.currenctFragment.CurrencyFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.CommonUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.FileUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.ToastUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseActivity;

/**
 * Created by 15828 on 2016/7/14.
 */
public class CurrencyActivity extends BaseActivity {
    public static final int ACTIVITY_ALBUM_REQUESTCODE = 1000;
    public static final int ACTIVITY_CAMERA_REQUESTCODE = 1001;
    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;
    private Bitmap btmap_album, btmap_phone, mBitmap;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_currency);
        mFragmentManager.beginTransaction()
                .replace(R.id.setting_currency_Content, new CurrencyFragment())
                .commit();
    }

    public void currency_back(View view) {
        finish();
    }

    public void ChatBackground(View view) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .replace(R.id.setting_currency_Content, new ChatBackgroundFragment())
                .commit();
    }

    public void ChooseBackground(View view) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .replace(R.id.setting_currency_Content, new ChooseBackgroundFragment())
                .commit();
    }

    public void ChooseFromPhone(View view) {
        if (CommonUtils.isExistCamera(this)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
            Uri imageUri = Uri.fromFile(FileUtil.getHeadPhotoFileRaw());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            startActivityForResult(intent, ACTIVITY_CAMERA_REQUESTCODE);
        } else {
            Toast.makeText(this,
                    getResources().getString(R.string.user_no_camera),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void ChooseFromAlbum(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, null);// 调用android的图库
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(i, ACTIVITY_ALBUM_REQUESTCODE);
    }


    public void setmImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_ALBUM_REQUESTCODE:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getData() == null) {
                        ToastUtils.toast(this, getString(R.string.pic_not_valid));
                        return;
                    }
                    Uri uri = data.getData();
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    String img_path = actualimagecursor.getString(actual_image_column_index);
                    int degree = FileUtil.readPictureDegree(img_path);
                    btmap_album = BitmapFactory.decodeFile(img_path);
                    btmap_album = FileUtil.rotaingImageView(degree, btmap_album);
//                    mImageView.setImageBitmap(btmap_album);
//                    FileUtil.deleteTempAndRaw();
                    WindowManager wm = this.getWindowManager();
                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();
                    CommonUtils.cutPhoto(this, data.getData(), false, width, height);
                }

                break;
            case ACTIVITY_CAMERA_REQUESTCODE:
                if (resultCode == Activity.RESULT_OK) {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmapOptions.inSampleSize = 2;
                    int degree = FileUtil.readPictureDegree(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW);
                    Bitmap cameraBitmap = BitmapFactory.decodeFile(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW, bitmapOptions);
                    cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                    FileUtil.saveCutBitmapForCache(this, cameraBitmap);
                    String mCoverPath = FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW;
                    btmap_phone = BitmapFactory.decodeFile(mCoverPath);
//                    mImageView.setImageBitmap(btmap_phone);
//                    FileUtil.deleteTempAndRaw();
                    WindowManager wm = this.getWindowManager();
                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();
                    CommonUtils.cutPhoto(this, Uri.fromFile(FileUtil.getHeadPhotoFileRaw()), false, width, height);
                }
                break;
            case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                Log.d("qqq", "aaaa...");
                String coverPath = FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_TEMP;
                mBitmap = BitmapFactory.decodeFile(coverPath);
                mImageView.setImageBitmap(mBitmap);
            default:

                break;
        }
    }

}
