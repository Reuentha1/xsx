package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.wo.myState.myStateActivity;

/**
 * Created by FengChaoQun
 * on 2016/8/4
 */
public class Name extends TextView {
    private Context context;
    public static final int PERSON_INFO = 1000;
    public static final int PERSON_STATE = 2000;
    private int intent_type;
    public Name(Context context) {
        super(context);
        init(context);
    }

    public Name(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Name(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context=context;
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
