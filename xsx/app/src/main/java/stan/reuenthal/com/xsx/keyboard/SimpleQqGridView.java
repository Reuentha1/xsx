package stan.reuenthal.com.xsx.keyboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class SimpleQqGridView extends RelativeLayout {

    protected View view;
    protected Context context;

    private RecyclerView recyclerView;
    private MyRecycleViewAdapter mAdapter;
    public List<String> list=new ArrayList<>();
    private String path="";
    private List<Pic> pics=new ArrayList<>();

    public SimpleQqGridView(Context context) {
        this(context, null);
    }

    public SimpleQqGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_apps, this);
        setBackgroundColor(getResources().getColor(R.color.white));
        init();
    }

    public void init(){
        getDatas();
        recyclerView=(RecyclerView)findViewById(R.id.rv_pics);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        mAdapter = new MyRecycleViewAdapter(context,pics);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL_LIST));
        mAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(context,data,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDatas(){
        path= Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DCIM+"/Camera";
        Log.d("ddd",path);
        File file=new File(path);
        File[] files=file.listFiles();
        Log.d("ddd",String.valueOf(files.length));
        Matrix m=new Matrix();
        m.postRotate(90);
        for (File mfile:files) {
            list.add(mfile.getPath());
        }
        for (int i=list.size()-1;i>list.size()-11;i--){
            String mpath= list.get(i);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            Bitmap bmp=BitmapFactory.decodeFile(mpath,options);
            int mwidth1=options.outWidth;
            int mheight1=options.outHeight;
            options.inSampleSize = 10;
            options.inJustDecodeBounds=false;
            bmp=BitmapFactory.decodeFile(mpath, options);
            int heightm=bmp.getWidth();
            int widthm=bmp.getHeight();
            Bitmap b=Bitmap.createBitmap(bmp,0,0,heightm,widthm,m,true);
            Pic pic=new Pic(mpath,b,mheight1,mwidth1);
            pics.add(pic);
        }
    }

    /*protected void init(){
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        gv_apps.setPadding(0,0,0, EmoticonsKeyboardUtils.dip2px(context, 20));
        RelativeLayout.LayoutParams params = (LayoutParams) gv_apps.getLayoutParams();
        params.bottomMargin = EmoticonsKeyboardUtils.dip2px(context, 20);
        gv_apps.setLayoutParams(params);
        gv_apps.setVerticalSpacing(EmoticonsKeyboardUtils.dip2px(context, 18));
        ArrayList<AppBean> mAppBeanList = new ArrayList<>();
        mAppBeanList.add(new AppBean(R.mipmap.lcw, "QQ电话"));
        mAppBeanList.add(new AppBean(R.mipmap.lde, "视频电话"));
        mAppBeanList.add(new AppBean(R.mipmap.ldh, "短视频"));
        mAppBeanList.add(new AppBean(R.mipmap.lcx, "收藏"));
        mAppBeanList.add(new AppBean(R.mipmap.ldc, "发红包"));
        mAppBeanList.add(new AppBean(R.mipmap.ldk, "转账"));
        mAppBeanList.add(new AppBean(R.mipmap.ldf, "悄悄话"));
        mAppBeanList.add(new AppBean(R.mipmap.lcu, "文件"));
        AppsAdapter adapter = new AppsAdapter(getContext(), mAppBeanList);
        gv_apps.setAdapter(adapter);
    }*/
}
