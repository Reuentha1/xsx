package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.xiaoshang.ShoolReward.RewardDetail;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBasePresenter;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.utils.IBaseView;

/**
 * Created by FengChaoQun
 * on 2016/7/23
 */
public class RewardDetailContract {
    public interface  view extends IBaseView<Presenter>{
        /*
       **describe:滑块滑动到指定位置
       */
        void moveToPosition(int position);
        /*
        **describe:滑块闪现到指定位置
        */
        void moveImediate(int position);
        /*
        **describe:弹出分享对话框
        */
        void showShareDialog();
        /*
        **describe:跳转到评论页面
        */
        void gotoInput();
        /*
        **describe:跳转到选人界面
        */
        void gotoSelectPeson();
        /*
        **describe:设置赞或取消
        */
        void setPraise();
        /*
        **describe:设置 评论 转发 赞 的数目
        */
        void setCount(int transmit, int comment, int praise);
        /*
       **describe:弹出转发成功对话框
       */
        void showTransmitSuccess();
    }

    public interface Presenter extends IBasePresenter{

        /*
       **describe:转发  赞 分享到校友圈
       */
        void transmit();

        void praise();

        void share();

        /*
        **describe:收藏与否
        */
        void collect();
    }
}
