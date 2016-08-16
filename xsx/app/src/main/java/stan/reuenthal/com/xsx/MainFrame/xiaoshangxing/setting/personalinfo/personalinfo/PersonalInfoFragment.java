package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.personalinfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.PersonalInfoActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.io.ByteArrayInputStream;

/**
 * Created by tianyang on 2016/7/9.
 */
public class PersonalInfoFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-PersonalInfoFragment";

    private View mView;
    private TextView hometown;
    private ImageView imgCover;
    private PersonalInfoActivity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personinfo,container,false);
        Log.d("qqq", "onCreate....");
        mActivity = (PersonalInfoActivity) getActivity();
        imgCover = (ImageView) mView.findViewById(R.id.setting_personinfo_headView);
        ViewGroup.LayoutParams para;
        para = imgCover.getLayoutParams();
        mActivity.setImagCoverHeight(para.height);
        mActivity.setImagCoverWidth(para.width);
//        setImgCover();
//        getHometown();
        return mView;
    }

    private void setImgCover() {
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("headImg", Context.MODE_PRIVATE);
        //第一步:取出字符串形式的Bitmap
        String imageString=sharedPreferences.getString("smallImage", "");
        //第二步:利用Base64将字符串转换为ByteArrayInputStream
        byte[] byteArray= Base64.decode(imageString, Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
        //第三步:利用ByteArrayInputStream生成Bitmap
        Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
        if(bitmap!=null) imgCover.setImageBitmap(bitmap);
    }

    private void getHometown() {
        hometown = (TextView) mView.findViewById(R.id.personinfo_hometown);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hometown", Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        if(!name.equals("")) hometown.setText(name);
    }


}
