package stan.reuenthal.com.xsx.MainFrame.xiaoshangxing.Network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FengChaoQun
 * on 2016/8/3
 */
public class Network {
    public static Retrofit retrofit;
    private static final int DEFAULT_TIME=8;            //默认超时为8s
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            //添加日志
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
        }
        return retrofit;
    }
}
