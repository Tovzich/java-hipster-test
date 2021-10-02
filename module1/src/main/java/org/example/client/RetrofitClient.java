package org.example.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {
    public interface Api {}

    private final Retrofit retrofit;

    public RetrofitClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://31.131.249.140:8080/")
                .client(httpClient.build())
                .build();
    }

    public <T extends Api> T buildClient(Class<T> clientClass) {
        return retrofit.create(clientClass);
    }
}