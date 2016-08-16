package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import stan.reuenthal.com.xsx.R;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.BaseFragment;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.MessageNotice.MessageNoticeActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.ShoolRewardActivity;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolfellowHelp.ShoolfellowHelpActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by FengChaoQun
 * on 2016/7/18
 */
public class XiaoShangFragment extends BaseFragment {
    public static final String TAG = BaseFragment.TAG + "-XiaoShangFragment";
    @Bind(R.id.xiaoshang_notice)
    ImageView xiaoshangNotice;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.first)
    ImageView first;
    @Bind(R.id.second)
    ImageView second;
    @Bind(R.id.third)
    ImageView third;
    @Bind(R.id.forth)
    ImageView forth;
    @Bind(R.id.five)
    ImageView five;
    @Bind(R.id.scrollview)
    HorizontalScrollView scrollView;
    @Bind(R.id.tuch)
    ImageView tuch;
    private View mview;

    private float current, result, current2;
    private int start = 0;
    private int currentImage=1;
    private boolean isMoving;
    private Handler handler = new Handler();
    private Runnable runnable;
    private ValueAnimator animator;

    private int image_width, divider, padding_start, total, tuchlength;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test, null);
        mview = view;
        ButterKnife.bind(this, view);
        runnable = new Runnable() {
            @Override
            public void run() {
                isMoving = false;
            }
        };
        init();
        return view;
    }

    private void init() {
        setImagePosition(currentImage);
        image_width = getResources().getDimensionPixelSize(R.dimen.x808);
        divider = getResources().getDimensionPixelSize(R.dimen.x48);
        padding_start = getResources().getDimensionPixelSize(R.dimen.x136);
        total = image_width * 5 + divider * 4 + padding_start * 2;

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isMoving) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            current = event.getX();
                            current2 = event.getX();
                            return true;
                        case MotionEvent.ACTION_UP:
                            int des = (int) (current - event.getX());
                            jujiment(des);
                            return true;
                    }
                }
                return true;
            }
        });

        tuch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tuchlength = tuch.getWidth();
                final int item = tuchlength / 5;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        current = event.getX();
                        int des1 = (int) current;
                        if (des1 <= item) {
                            setPosition(1);
                        } else if (des1 <= item * 2) {
                            setPosition(2);
                        } else if (des1 <= item * 3) {
                            setPosition(3);
                        } else if (des1 <= item * 4) {
                            setPosition(4);
                        } else if (des1 <= item * 5) {
                            setPosition(5);
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float i = event.getX() - current;
                        if (Math.abs(i) > 1) {
                            float sca = (float) total / tuch.getWidth();
                            scrollView.smoothScrollBy((int) (i * sca), 0);
                            current = event.getX();
                        }
                        instantSetImage((int) (event.getX()));
                        break;
                    case MotionEvent.ACTION_UP:
                        final int des = (int) event.getX();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (des <= item) {
                                    setFoucus(1);
                                } else if (des <= item * 2) {
                                    setFoucus(2);
                                } else if (des <= item * 3) {
                                    setFoucus(3);
                                } else if (des <= item * 4) {
                                    setFoucus(4);
                                } else if (des <= item * 5) {
                                    setFoucus(5);
                                }
                            }
                        }, 250);
                }
                return false;
            }
        });

        xiaoshangNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notice_intent=new Intent(getContext(), MessageNoticeActivity.class);
                startActivity(notice_intent);
            }
        });
    }


    private void myMove(int x) {
        scrollView.smoothScrollBy(x, 0);
    }

    public static XiaoShangFragment newInstance() {
        return new XiaoShangFragment();
    }

    private void tuchUp(int position) {
        View v = first;
        switch (position) {
            case 1:
                v = first;
                break;
            case 2:
                v = second;
                break;
            case 3:
                v = third;
                break;
            case 4:
                v = forth;
                break;
            case 5:
                v = five;
                break;
        }

        int[] xy = new int[2];
        v.getLocationOnScreen(xy);
        if (xy[0] < 0) {
            Log.d("select", "1");
            jujiment(20);
        } else if (xy[0] > 96 * 2) {
            Log.d("select", "2");
            jujiment(-20);
        } else {
            Log.d("select", "3");
            setFoucus(currentImage);
        }
    }

    private void instantSetImage(int loaction) {
        tuchlength = tuch.getWidth();
        final int item = tuchlength / 5;
        if (loaction <= item) {
            setImagePosition(1);
        } else if (loaction <= item * 2) {
            setImagePosition(2);
        } else if (loaction <= item * 3) {
            setImagePosition(3);
        } else if (loaction <= item * 4) {
            setImagePosition(4);
        } else if (loaction <= item * 5) {
            setImagePosition(5);
        }
    }

    private void setFoucus(final int position) {
        if (isMoving) {
            return;
        }
        int[] xy = new int[2];
        switch (position) {
            case 1:
                first.getLocationOnScreen(xy);
                break;
            case 2:
                second.getLocationOnScreen(xy);
                break;
            case 3:
                third.getLocationOnScreen(xy);
                break;
            case 4:
                forth.getLocationOnScreen(xy);
                break;
            case 5:
                five.getLocationOnScreen(xy);
                break;
        }

//        setImagePosition(position);
        Log.d("goto" + position, "" + xy[0]);
        animator = ValueAnimator.ofInt(0, xy[0] - padding_start);
        int abs = Math.abs(xy[0] - padding_start);
        abs = abs > 150 ? abs : 150;
        animator.setDuration(abs <= 300 ? abs : 300 /*3000*/);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = (int) animator.getAnimatedValue();
                scrollView.smoothScrollBy(i - start, 0);
                start = i;
            }
        });
        currentImage = position;
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isMoving = true;
                Log.d("animator", "animator start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                isMoving=false;
                setImagePosition(position);
                handler.postDelayed(runnable, 200);
                Log.d("animator", "animator end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d("animator", "animator cancel");
                isMoving = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
//        isMoving = true;
//        handler.postDelayed(runnable, abs);
    }

    private void setPosition(final int position) {

        int[] xy = new int[2];
        switch (position) {
            case 1:
                first.getLocationOnScreen(xy);
                break;
            case 2:
                second.getLocationOnScreen(xy);
                break;
            case 3:
                third.getLocationOnScreen(xy);
                break;
            case 4:
                forth.getLocationOnScreen(xy);
                break;
            case 5:
                five.getLocationOnScreen(xy);
                break;
        }

        setImagePosition(position);


        scrollView.smoothScrollBy(xy[0] - padding_start, 0);

        currentImage = position;


    }

    private void setImagePosition(int position) {
        switch (position) {
            case 1:
                tuch.setImageResource(R.mipmap.xiaoshang_select1);
                title.setText("校历资讯");
                break;
            case 2:
                tuch.setImageResource(R.mipmap.xiaoshang_select2);
                title.setText("校内悬赏");
                break;
            case 3:
                tuch.setImageResource(R.mipmap.xiaoshang_select3);
                title.setText("校友互帮");
                break;
            case 4:
                tuch.setImageResource(R.mipmap.xiaoshang_select4);
                title.setText("计划发起");
                break;
            case 5:
                tuch.setImageResource(R.mipmap.xiaoshang_select5);
                title.setText("闲置出售");
                break;
        }
    }

    private void jujiment(int result) {
        Log.d("current", "" + currentImage);
        if (!isMoving) {
            if (result > 15) {
                if (currentImage != 5) {
                    setFoucus(currentImage + 1);
                    Log.d("current", "to" + (currentImage));
                }

            } else if (result < -15) {
                if (currentImage != 1) {
                    setFoucus(currentImage - 1);
                    Log.d("current", "to" + (currentImage));
                }
            } else {
                gotoOther(currentImage);
            }
        }

    }

    private void gotoOther(int position) {
        switch (position) {
            case 2:
                Intent rewaed_intent=new Intent(getContext(), ShoolRewardActivity.class);
                getContext().startActivity(rewaed_intent);
                break;
            case 3:
                Intent help_intent = new Intent(getContext(), ShoolfellowHelpActivity.class);
                getContext().startActivity(help_intent);
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        isMoving = false;
        setImagePosition(currentImage);
    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
//        if (animator!=null){
//            animator.cancel();
//        }
    }


}
