package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.hometown;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.personalinfo.PersonalInfoActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.city_choosing.ArrayWheelAdapter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.city_choosing.OnWheelChangedListener;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.utils.city_choosing.WheelView;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by tianyang on 2016/7/9.
 */
public class HometownFragment extends BaseFragment implements View.OnClickListener,OnWheelChangedListener {
    private View mView;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private Button mBtnComplete;
    private String[] mProvinceDatas;
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    private String mCurrentProviceName;
    private String mCurrentCityName;
    private TextView textView,back;
    private PersonalInfoActivity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_setting_personinfo_hometown,container,false);
        mActivity = (PersonalInfoActivity)getActivity();
        mViewProvince = (WheelView) mView.findViewById(R.id.id_province);
        mViewCity = (WheelView) mView.findViewById(R.id.id_city);
        mBtnComplete = (Button) mView.findViewById(R.id.hometown_complete);
        textView = (TextView) mView.findViewById(R.id.hometown_text);
        mViewCity.setShadowColor(0xefefeff5,0xdfefeff5,0x0fefeff5);
        mViewProvince.setShadowColor(0xefefeff5,0xdfefeff5,0x0fefeff5);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mBtnComplete.setOnClickListener(this);
        back = (TextView) mView.findViewById(R.id.hometown_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        setUpData();
        return mView;
    }



    @Override
    public void onClick(View v) {
        String hometown = mCurrentProviceName+" "+mCurrentCityName;
        textView.setText(hometown);
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hometown",Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("name",hometown);
//        editor.apply();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        }
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),mProvinceDatas));
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hometown",Activity.MODE_PRIVATE);
//        String name = sharedPreferences.getString("name","");
//        if(!name.equals("")) textView.setText(name);
        updateCities();
        updateAreas();
    }

    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
    }

    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(), cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    private void initProvinceDatas()
    {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getActivity().getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            provinceList = handler.getDataList();
            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                }
            }
            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    cityNames[j] = cityList.get(j).getName();
                }
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void setText(String text){
        Log.d("qqq","setting2...");
        textView.setText(text);
    }




}
