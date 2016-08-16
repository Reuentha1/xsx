package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.MessageNotice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.EmotionText.EmotinText;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.input_activity.InputActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.CirecleImage;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout.Name;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class Message_adpter extends ArrayAdapter<String> {
    private Context context;
    private int resource;
    List<String> strings;

    public Message_adpter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.strings = objects;
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_xiaoshang_message, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InputActivity.class);
                intent.putExtra(InputActivity.EDIT_STATE, InputActivity.COMMENT);
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.head_image)
        CirecleImage headImage;
        @Bind(R.id.name)
        Name name;
        @Bind(R.id.from)
        TextView from;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.name_type_lay)
        LinearLayout nameTypeLay;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.response)
        LinearLayout response;
        @Bind(R.id.comment_text)
        EmotinText commentText;
        @Bind(R.id.comment_layout)
        LinearLayout commentLayout;
        @Bind(R.id.responsed_name)
        TextView responsedName;
        @Bind(R.id.responsed_text)
        EmotinText responsedText;
        @Bind(R.id.self_response_lay)
        LinearLayout selfResponseLay;
        @Bind(R.id.piblished_person)
        TextView piblishedPerson;
        @Bind(R.id.piblished_text)
        EmotinText piblishedText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
