package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.school_circle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.myStateActivity;

/**
 * Created by FengChaoQun
 * on 2016/7/6
 */
public class PraisePeople {
    private  Context context;
    private TextView textView;
    private SpannableString spannableString;

    public PraisePeople(Context mcontext) {
        this.context = mcontext;
        textView=new TextView(context);
        textView.setTextSize(13);

        spannableString=new SpannableString("image");
        Drawable d = context.getResources().getDrawable(R.mipmap.blue_heart);
//        d.setBounds(0, 0, context.getResources().getDimensionPixelSize(R.dimen.image_11sp),
//                context.getResources().getDimensionPixelSize(R.dimen.image_10sp));
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(d,ImageSpan.ALIGN_BASELINE), 0, spannableString.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.append(spannableString);
    }

    public void addName(final String name){
        spannableString= new SpannableString(name+",");
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Log.d("name",name);
                Intent intent=new Intent(context,myStateActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(context.getResources().getColor(R.color.blue1));
                ds.setUnderlineText(false);
            }
        },0,spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

//        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, spannableString.length(),
//                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        textView.append(spannableString);
    }

    public TextView getTextView(){
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(textView.getText().subSequence(0,textView.length()-1));
        return this.textView;
    }
}
