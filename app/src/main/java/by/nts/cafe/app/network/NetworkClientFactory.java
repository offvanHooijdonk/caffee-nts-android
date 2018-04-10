package by.nts.cafe.app.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import by.nts.cafe.app.helper.PrefHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClientFactory {
    private static OkHttpClient httpClient;
    private static Retrofit retrofit;
    private static HallClient hallClient;

    public static HallClient getHallClient(Context ctx) {
        if (hallClient == null) {
            hallClient = getApi(ctx).create(HallClient.class);
        }
        return hallClient;
    }
// TODO recreate API whenever preference changed?
    private static Retrofit getApi(Context ctx) {
        if (retrofit == null) {
            String url = PrefHelper.getEndpointHost(ctx) + PrefHelper.getEndpointPath(ctx);
            if (!url.endsWith("/")) url = url + "/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    //.addNetworkInterceptor(authenticationInterceptor)
                    .build();
        }

        return httpClient;
    }

}
