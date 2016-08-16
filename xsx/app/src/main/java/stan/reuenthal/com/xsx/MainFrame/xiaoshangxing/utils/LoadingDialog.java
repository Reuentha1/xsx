package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;


/**
 * 加载对话框控件
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class LoadingDialog extends Dialog {

    private Context mContext;
    private LayoutInflater inflater;
    private LayoutParams lp;
    private TextView loadtext;
    private onDismiss onDismiss;

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog);

        this.mContext = context;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.loadingdialog, null);
        loadtext = (TextView) layout.findViewById(R.id.loading_text);
        final View progressBar = layout.findViewById(R.id.progressBar);
        setContentView(layout);

        // 设置window属性
        lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 0.3f; // 去背景遮盖
        lp.alpha = 1.0f;
        getWindow().setAttributes(lp);
//实现转动
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layout, "fcq", 0f, 360f);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                        linearLayout.setRotationX((Float) animation.getAnimatedValue());
//                        linearLayout.setRotationY((Float) animation.getAnimatedValue());
//                        layout.setRotation((Float) animation.getAnimatedValue());
                progressBar.setRotation((Float) animation.getAnimatedValue());
//                        layout.setPivotX(50+(Float) animation.getAnimatedValue());
//                        layout.setPivotY(50+(Float) animation.getAnimatedValue());
            }
        });


//		this.setCancelable(false);
        this.setCanceledOnTouchOutside(false);

    }

    @Override
    public void dismiss() {
        if (onDismiss!=null){
            onDismiss.doBeforDismiss();
        }
        super.dismiss();
    }

    public void setOnDismiss(onDismiss on){
        this.onDismiss=on;
    }

    public void setLoadText(String content) {
        loadtext.setText(content);
    }

    public interface onDismiss{
        void doBeforDismiss();
    }
}