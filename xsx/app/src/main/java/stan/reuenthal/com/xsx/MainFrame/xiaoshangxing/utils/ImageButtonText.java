package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.setting.DataSetting;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.normalUtils.SPUtils;

/**
 * Created by 15828 on 2016/7/25.
 */
public class ImageButtonText extends LinearLayout {

    private ImageView imgView;
    private TextView textView;
    private boolean checked = false;

    public ImageButtonText(Context context) {
        super(context, null);
    }

    public ImageButtonText(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.button_img_text, this, true);

        this.imgView = (ImageView) findViewById(R.id.imgview);
        this.textView = (TextView) findViewById(R.id.textview);

        this.setClickable(true);
        this.setFocusable(true);
        checked = DataSetting.IsFocused(context);
        if (!checked) {
            imgView.setImageResource(R.mipmap.icon_liuxin);
        } else {
            imgView.setImageResource(R.mipmap.icon_liuxin_select);
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checked) {
                    checked = true;
                    imgView.setImageResource(R.mipmap.icon_liuxin_select);
                    SPUtils.put(context, "focus", true);
                } else {
                    checked = false;
                    imgView.setImageResource(R.mipmap.icon_liuxin);
                    SPUtils.put(context, "focus", false);
                }
            }
        });

    }


    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}
