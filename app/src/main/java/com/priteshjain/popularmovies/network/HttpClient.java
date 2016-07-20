package com.priteshjain.popularmovies.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.priteshjain.popularmovies.BuildConfig;
import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.constants.Constant;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class HttpClient {

    private static final Interceptor rewriteCacheControlHeader = new Interceptor() {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder request = originalRequest.newBuilder();
            Response response = chain.proceed(request.build());
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", String.format("max-age=%d", 600))
                    .build();
        }
    };

    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private static final File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
    private static final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static final Cache cache = new Cache(cacheDir, cacheSize);


    private static final OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.
                            newBuilder().
                            url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            }).cache(cache)
            .addInterceptor(rewriteCacheControlHeader);

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build();

    public ApiEndpoint getClient() {
        return retrofit.create(ApiEndpoint.class);
    }



}
