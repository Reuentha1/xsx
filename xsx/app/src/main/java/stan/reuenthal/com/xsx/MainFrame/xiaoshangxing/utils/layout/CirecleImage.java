package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.myStateActivity;

/**
 * Created by FengChaoQun
 * on 2016/4/20
 */
public class CirecleImage extends ImageView {
    private Context context;
    public static final int PERSON_INFO = 1000;
    public static final int PERSON_STATE = 2000;
    private int intent_type;
    /**
     * 获取屏幕密度
     */
    private final float density = getContext().getResources().getDisplayMetrics().density;
    /**
     * �?
     */
    private float roundness;


    public CirecleImage(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public CirecleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public CirecleImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init();
    }

    @Override
    public void draw(Canvas canvas) {
        final Bitmap composedBitmap;
        final Bitmap originalBitmap;
        final Canvas composedCanvas;
        final Canvas originalCanvas;
        final Paint paint;
        final int height;
        final int width;

        width = getWidth();

        height = getHeight();

        composedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        originalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        composedCanvas = new Canvas(composedBitmap);
        originalCanvas = new Canvas(originalBitmap);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        super.draw(originalCanvas);

        composedCanvas.drawARGB(0, 0, 0, 0);

        composedCanvas.drawRoundRect(new RectF(0, 0, width, height), this.roundness, this.roundness, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        composedCanvas.drawBitmap(originalBitmap, 0, 0, paint);

        canvas.drawBitmap(composedBitmap, 0, 0, new Paint());

    }

    public float getRoundness() {
        return this.roundness / this.density;
    }

    public void setRoundness(float roundness) {
        this.roundness = roundness * this.density;
    }

    private void init() {
        // 括号中的数字是调整图片弧度的 调成100为圆形图�? 调成15为圆角图�?
        setRoundness(100);
    }

    public void setIntent_type(int intent_type) {
        this.intent_type = intent_type;
        switch (intent_type) {
            case PERSON_INFO:

                break;
            case PERSON_STATE:
                intent_type = PERSON_STATE;
                setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent state_intent = new Intent(context, myStateActivity.class);
//                        state_intent.putExtra(myStateActivity.TYPE,myStateActivity.OTHRE);
                        state_intent.putExtra(myStateActivity.TYPE, myStateActivity.SELF);
                        context.startActivity(state_intent);
                    }
                });
                break;
        }

    }

    public int getIntent_type() {
        return intent_type;
    }
}
