package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.HelpDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.Name;

import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class TransmitListFrafment extends Fragment {
    private RecyclerView recyclerView;
    private TextView emptyText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_comment_list,null);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HomeAdapter());
        emptyText=(TextView)view.findViewById(R.id.empty_text);
        Random random=new Random();
        if (random.nextInt(2)==1){
            recyclerView.setVisibility(View.GONE);
            emptyText.setVisibility(View.VISIBLE);
            emptyText.setText("赶紧转发一下");
        }
        return view;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view=LayoutInflater.from(
                    getContext()).inflate(R.layout.item_transmit_list_recycleview, parent,
                    false);
            MyViewHolder holder = new MyViewHolder(view);
            holder.name=(Name) view.findViewById(R.id.name);
            holder.college=(TextView)view.findViewById(R.id.college);
            holder.time=(TextView)view.findViewById(R.id.time);
            holder.transmitLoction=(TextView)view.findViewById(R.id.transmit_loaction);
            holder.text=(EmotinText) view.findViewById(R.id.text);
            holder.headImage=(CirecleImage)view.findViewById(R.id.head_image);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
        }

        @Override
        public int getItemCount()
        {
            return 100;
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView college, time, transmitLoction;
            Name name;
            EmotinText text;
            CirecleImage headImage;
            public MyViewHolder(View view)
            {
                super(view);
            }
        }
    }
}
