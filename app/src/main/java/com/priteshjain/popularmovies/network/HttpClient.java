package com.priteshjain.popularmovies.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.priteshjain.popularmovies.BuildConfig;
import com.priteshjain.popularmovies.constants.ApiEndpoint;
import com.priteshjain.popularmovies.constants.Constant;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    OkHttpClient.Builder httpClient =
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
            });

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build();

    public ApiEndpoint getClient() {
        return retrofit.create(ApiEndpoint.class);
    }
}