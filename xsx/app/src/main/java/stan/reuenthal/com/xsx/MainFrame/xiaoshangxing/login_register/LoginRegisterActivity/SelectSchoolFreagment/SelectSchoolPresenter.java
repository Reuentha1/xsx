package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.login_register.LoginRegisterActivity.SelectSchoolFreagment;

/**
 * Created by FengChaoQun
 * on 2016/6/25
 */
public class SelectSchoolPresenter implements SelectSchoolContract.Presenter {
    private SelectSchoolContract.View mView;

    public SelectSchoolPresenter(SelectSchoolContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void clickOnReflesh() {
        refreshList();
    }

    @Override
    public void isSchoolAccess(String name) {
        if (!name.contains("江南大学")) {
            mView.gotoAppeal();
        } else {
            mView.gotoMain();
        }
    }

    @Override
    public void refreshList() {
        String[] lists = {"乱点什么", "现在还不能刷新", "点出bug你来改啊", "无聊!"};
        mView.setAdapter(lists);
    }
}
