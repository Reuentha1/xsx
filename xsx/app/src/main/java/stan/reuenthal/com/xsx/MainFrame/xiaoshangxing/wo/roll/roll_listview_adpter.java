package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.roll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import stan.reuenthal.com.xsx.R;

import java.util.List;

/**
 * Created by FengChaoQun
 * on 2016/7/12
 */
public class roll_listview_adpter extends ArrayAdapter<String> {
    private Context context;
    public roll_listview_adpter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.item_roll_listview,null);
        return view;
    }
}
