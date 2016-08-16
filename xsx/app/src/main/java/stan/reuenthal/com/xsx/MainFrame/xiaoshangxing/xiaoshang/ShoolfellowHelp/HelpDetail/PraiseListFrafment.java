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
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.Name;

import java.util.Random;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class PraiseListFrafment extends Fragment {
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
            emptyText.setText("赶紧赞一下");
        }
        return view;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view=LayoutInflater.from(
                    getContext()).inflate(R.layout.item_praise_list_recycleview, parent,
                    false);
            MyViewHolder holder = new MyViewHolder(view);
            holder.view=view;
            holder.name=(Name) view.findViewById(R.id.name);
            holder.college=(TextView)view.findViewById(R.id.college);
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

            TextView college;
            Name name;
            CirecleImage headImage;
            View view;
            public MyViewHolder(View view)
            {
                super(view);
            }
        }
    }
}
