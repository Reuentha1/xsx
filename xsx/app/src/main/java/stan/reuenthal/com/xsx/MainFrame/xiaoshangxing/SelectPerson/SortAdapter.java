package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.SelectPerson;

/**
 * Created by FengChaoQun
 * on 2016/7/27
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.SectionIndexer;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;

import java.util.List;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
    private List<SortModel> list = null;
    private Context mContext;
    private int limit;
    private SelectPersonActivity activity;

    public SortAdapter(Context mContext, List<SortModel> list,int limit,SelectPersonActivity activity ) {
        this.mContext = mContext;
        this.list = list;
        this.limit=limit;
        this.activity=activity;
    }

    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     * @param list
     */
    public void updateListView(List<SortModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_person, null);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.head=(CirecleImage)view.findViewById(R.id.head_image);
            viewHolder.checkBox=(CheckBox)view.findViewById(R.id.checkbox);
            viewHolder.line=view.findViewById(R.id.line);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if(position == getPositionForSection(section)){
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            if (section==64){
                viewHolder.tvLetter.setText("☆星标好友");
            }else {
                viewHolder.tvLetter.setText(mContent.getSortLetters());
            }
            viewHolder.line.setVisibility(View.GONE);

        }else{
            viewHolder.tvLetter.setVisibility(View.GONE);
            viewHolder.line.setVisibility(View.VISIBLE);
        }

        viewHolder.name.setText(this.list.get(position).getName());

        if (activity.getSelectPerson().contains(viewHolder.name.getText().toString())){
            viewHolder.checkBox.setChecked(true);

        }else {
            viewHolder.checkBox.setChecked(false);
        }


        final ViewHolder viewHolder_temp=viewHolder;
        final CheckBox check=viewHolder.checkBox;
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()){
                    if (activity.getSelectPerson().size()>=limit){
                        check.setChecked(false);
                        activity.showOverLimit();
                    }else {
                        activity.addSelectPerson(viewHolder_temp.name.getText().toString());
                    }
                }else {
                        activity.reduceSelectPerson(viewHolder_temp.name.getText().toString());
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder_temp.checkBox.performClick();
            }
        });

        return view;

    }



    final static class ViewHolder {
        TextView tvLetter;
        TextView name;
        CirecleImage head;
        CheckBox checkBox;
        View line;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public Object[] getSections() {
        return null;
    }
}
