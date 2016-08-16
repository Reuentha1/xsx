package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.api;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.BaseUrl;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.login;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by FengChaoQun
 * on 2016/8/3
 */
public interface loginApi {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST(BaseUrl.LOGIN)
    Observable<ResponseBody>login(@Body login login);
}
