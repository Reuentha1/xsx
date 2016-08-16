package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.MyLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FengChaoQun
 * on 2016/6/11
 */
public class XSXApplication extends Application {

//    private List<Activity> mList = new LinkedList<Activity>();
    private Map<String,Activity> mList=new HashMap<String, Activity>();
    private int activityCount = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        /*
        **describe:集中监视Activity生命周期
        */
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
                MyLog.d(activity,"created");
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyLog.d(activity,"resumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityCount--;
                MyLog.d(activity, "destroyed");
            }
        });
    }

    /*
    **describe:添加Activity到Map中
    */

    public void addActivity(Activity activity){
//        mList.add(activity);
        mList.put(activity.toString(),activity);
        activityCount++;
    }

    /*
    **describe:完全退出APP
    */
    public void exit() {
        try {
//            for (Activity activity : mList) {
//                if (activity != null)
//                    activity.finish();
//            }
            for(Map.Entry<String, Activity> entry:mList.entrySet()){
                entry.getValue().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    **describe:finish某个activity
    */
    public void finish_activity(String tag){
        if (mList.containsKey(tag)){
            mList.get(tag).finish();
        }
    }
}
