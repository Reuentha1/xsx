package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.api;

import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.BaseUrl;
import stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network.Bean.CheckCode;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by FengChaoQun
 * on 2016/8/6
 */
public interface CheckCodeApi {
        @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
        @POST(BaseUrl.CHECK_CODE)
        Observable<ResponseBody> login(@Body CheckCode checkCode);
}
