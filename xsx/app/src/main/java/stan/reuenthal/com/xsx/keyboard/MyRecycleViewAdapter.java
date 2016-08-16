package stan.reuenthal.com.xsx.keyboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import stan.reuenthal.com.xsx.R;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> implements View.OnClickListener{

    Context context;
    public List<Pic> list=new ArrayList<>();
    public static int mheight=0;
    public double a=0;
    private List<Pic> ceferened =new ArrayList<>();

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(String)v.getTag());
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data);
    }

    public MyRecycleViewAdapter(Context context, List<Pic> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        mheight=viewGroup.getHeight();
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.pic.setImageBitmap(list.get(position).getBitmap());
        ViewGroup.LayoutParams layoutParams=viewHolder.pic.getLayoutParams();
        layoutParams.height=mheight;
        layoutParams.width=(int) (mheight*(double)((float)list.get(position).getHeight()/(float)list.get(position).getWidth()));
        viewHolder.pic.setLayoutParams(layoutParams);
        viewHolder.xuanzhong.setImageResource(R.mipmap.weixuanzhong);
        viewHolder.itemView.setTag(list.get(position).getPath());
        viewHolder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ddd","success");
            }
        });
        viewHolder.xuanzhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.xuanzhong.setImageResource(R.mipmap.xuanzhong);
                ceferened.add(list.get(position));
            }
        });
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView pic;
        public ImageView xuanzhong;
        public ViewHolder(View view){
            super(view);
            pic = (ImageView) view.findViewById(R.id.sdv_img);
            xuanzhong=(ImageView)view.findViewById(R.id.iv_weixuanzhong);
        }
    }

}