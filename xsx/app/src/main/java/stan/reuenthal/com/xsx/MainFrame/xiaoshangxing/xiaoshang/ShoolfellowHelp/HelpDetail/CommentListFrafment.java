package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.HelpDetail;

import android.content.Intent;
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
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.Name;

/**
 * Created by FengChaoQun
 * on 2016/7/20
 */
public class CommentListFrafment extends Fragment {
    private RecyclerView recyclerView;
    private TextView emptyText;
    private boolean isCollect;//记录是否是collect界面
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_comment_list,null);
        recyclerView=(RecyclerView) view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HomeAdapter());
        emptyText=(TextView)view.findViewById(R.id.empty_text);
//        Random random=new Random();
//        if (random.nextInt(4)==1){
//            recyclerView.setVisibility(View.GONE);
//            emptyText.setVisibility(View.VISIBLE);
//            emptyText.setText("赶紧评论一下");
//        }
        return view;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view=LayoutInflater.from(
                    getContext()).inflate(R.layout.item_comment_list_recycleview, parent,
                    false);
            MyViewHolder holder = new MyViewHolder(view);
            holder.view=view;
            holder.name=(Name) view.findViewById(R.id.name);
            holder.college=(TextView)view.findViewById(R.id.college);
            holder.time=(TextView)view.findViewById(R.id.time);
            holder.text=(EmotinText) view.findViewById(R.id.text);
            holder.headImage=(CirecleImage)view.findViewById(R.id.head_image);


            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCollect){
                        Intent comment_input=new Intent(getContext(),InputActivity.class);
                        comment_input.putExtra(InputActivity.EDIT_STATE,InputActivity.COMMENT);
                        comment_input.putExtra(InputActivity.COMMENT_OBJECT,"校上行");
                        startActivity(comment_input);
                    }else {
                        Intent comment_input=new Intent(getContext(),InputActivity.class);
                        comment_input.putExtra(InputActivity.EDIT_STATE,InputActivity.COMMENT);
                        comment_input.putExtra(InputActivity.COMMENT_OBJECT,"校上行");
                        startActivity(comment_input);
                    }
                }
            });
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

            TextView college,time;
            EmotinText text;
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
