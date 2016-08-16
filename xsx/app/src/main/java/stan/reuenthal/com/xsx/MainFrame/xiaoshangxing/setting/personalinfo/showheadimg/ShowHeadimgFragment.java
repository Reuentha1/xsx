package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.showheadimg;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.PersonalInfoActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.ActionSheet;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.CommonUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.FileUtil;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.headimg_set.ToastUtils;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.io.ByteArrayOutputStream;

/**
 * Created by tianyang on 2016/7/11.
 */
public class ShowHeadimgFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = BaseFragment.TAG + "-ShowHeadimgFragment";
    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;
    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;
    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;
    private View mView;
    private ImageView img, bigImg;
    private ActionSheet mActionSheet;
    private Bitmap sBitmap, bBitmap;
    private PersonalInfoActivity mActivity;
    private TextView back;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personalinfo_showheadimg, container, false);
        mActivity = (PersonalInfoActivity) getActivity();
        img = (ImageView) mView.findViewById(R.id.showhead_threepoint);
        bigImg = (ImageView) mView.findViewById(R.id.setting_showhead_img);
        back = (TextView) mView.findViewById(R.id.showheadimg_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        //    setBigImg();
        img.setOnClickListener(this);
        return mView;
    }

//    private void setBigImg() {
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("headImg", Activity.MODE_PRIVATE);
//        //第一步:取出字符串形式的Bitmap
//        String imageString = sharedPreferences.getString("bigImage", "");
//        //第二步:利用Base64将字符串转换为ByteArrayInputStream
//        byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
//        //第三步:利用ByteArrayInputStream生成Bitmap
//        Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
//        bigImg.setImageBitmap(bitmap);
//    }

    @Override
    public void onClick(View v) {
        if (mActionSheet == null) {
            mActionSheet = new ActionSheet(getActivity());
            mActionSheet.addMenuItem(getResources().getString(R.string.takephoto))
                    .addMenuItem(getResources().getString(R.string.fromalbum))
                    .addMenuItem(getResources().getString(R.string.savetophone));
        }
        mActionSheet.show();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mActionSheet.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        mActionSheet.getWindow().setAttributes(lp);
        mActionSheet.setMenuListener(new ActionSheet.MenuListener() {
            @Override
            public void onItemSelected(int position, String item) {
                if (position == 0) photo();
                else if (position == 1) album();
                else if (position == 2) saveToPhone();
            }


            @Override
            public void onCancel() {
                //  Toast.makeText(getActivity(), "onCancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToPhone() {

    }

    private void photo() {
        if (CommonUtils.isExistCamera(getActivity())) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
            Uri imageUri = Uri.fromFile(FileUtil.getHeadPhotoFileRaw());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
            startActivityForResult(intent, ACTIVITY_CAMERA_REQUESTCODE);
        } else {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.user_no_camera),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void album() {
        Intent i = new Intent(Intent.ACTION_PICK, null);// 调用android的图库
        i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(i, ACTIVITY_ALBUM_REQUESTCODE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_ALBUM_REQUESTCODE:
                if (resultCode == Activity.RESULT_OK) {
                    if (data.getData() == null) {
                        ToastUtils.toast(getActivity(), getString(R.string.pic_not_valid));
                        return;
                    }
                    Uri uri = data.getData();
                    Log.d("uri", "" + uri);
                    uri = geturi(data);
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    actualimagecursor.moveToFirst();
                    String img_path = actualimagecursor.getString(actual_image_column_index);
                    int degree = FileUtil.readPictureDegree(img_path);
                    bBitmap = BitmapFactory.decodeFile(img_path);
                    bBitmap = FileUtil.rotaingImageView(degree, bBitmap);
//                    bigImg.setImageBitmap(bBitmap);
                    CommonUtils.cutPhoto(getActivity(), data.getData(), true,
                            mActivity.getImagCoverWidth(), mActivity.getImagCoverHeight());

                }
                break;
            case ACTIVITY_CAMERA_REQUESTCODE:
                if (resultCode == Activity.RESULT_OK) {
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmapOptions.inSampleSize = 2;
                    int degree = FileUtil.readPictureDegree(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW);
                    Bitmap cameraBitmap = BitmapFactory.decodeFile(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW, bitmapOptions);
                    cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                    FileUtil.saveCutBitmapForCache(getActivity(), cameraBitmap);
                    CommonUtils.cutPhoto(getActivity(), Uri.fromFile(FileUtil.getHeadPhotoFileRaw()), true,
                            mActivity.getImagCoverWidth(), mActivity.getImagCoverHeight());
                    String mCoverPath = FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW;
                    bBitmap = BitmapFactory.decodeFile(mCoverPath);
//                    bigImg.setImageBitmap(bBitmap);

                }
                break;
            case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                String coverPath = FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_TEMP;
                sBitmap = BitmapFactory.decodeFile(coverPath);
//                saveBitmap();
                //      bigImg.setImageBitmap(sBitmap);

                //接下来是完成上传功能
//                HttpUtil.uploadCover(this, UrlContainer.UP_LIVE_COVER + "?uid="
//                        + LoginUtils.getInstance(this), coverPath, this);
                //成功之后删除临时图片
                FileUtil.deleteTempAndRaw();
                break;
        }
    }

    private void saveBitmap() {
        Log.d("qqq", "save...");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bBitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String bImageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        ByteArrayOutputStream mbyteArrayOutputStream = new ByteArrayOutputStream();
        sBitmap.compress(Bitmap.CompressFormat.PNG, 80, mbyteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] mbyteArray = mbyteArrayOutputStream.toByteArray();
        String sImageString = new String(Base64.encodeToString(mbyteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("headImg", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bigImage", bImageString);
        editor.putString("smallImage", sImageString);
        editor.apply();
    }

    /**
     * 解决小米手机上获取图片路径为null的情况
     *
     * @param intent
     * @return
     */
    public Uri geturi(Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = getActivity().getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID},
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        Log.d("uri2", "" + uri);
        return uri;
    }

}
