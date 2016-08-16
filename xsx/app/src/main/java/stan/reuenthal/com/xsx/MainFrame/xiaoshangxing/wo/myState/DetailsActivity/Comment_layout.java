package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.DetailsActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import stan.reuenthal.com.xsx.R;

/**
 * Created by FengChaoQun
 * on 2016/7/11
 */
public class Comment_layout {
    private Context context;
    private RelativeLayout relativeLayout;
    public Comment_layout(Context context) {
        this.context=context;
        relativeLayout= (RelativeLayout) LayoutInflater.from(context).
                inflate(R.layout.mystate_comment_layout,null);

    }

    public RelativeLayout getView(){
        return relativeLayout;
    }
}
