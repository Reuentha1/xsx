package stan.reuenthal.com.xsx.keyboard;

/**
 * Created by G.J.Lee on 七月.
 * Email finalfantasy@games.com
 */
public class AppBean {

    private int id;
    private int icon;
    private String funcName;

    public int getIcon() {
        return icon;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getId() {
        return id;
    }

    public AppBean(int icon, String funcName){
        this.icon = icon;
        this.funcName = funcName;
    }
}
