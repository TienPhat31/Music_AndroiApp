package tdtu.appnhac.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Khởi tạo Retrofit và cấu hình cho Retrofit
public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url) {
        // Tương tác mạng thông qua OkHttpClient này
        OkHttpClient okHttpCliet = new OkHttpClient.Builder().readTimeout(10000, TimeUnit.MILLISECONDS)
                                                            .writeTimeout(10000, TimeUnit.MILLISECONDS)
                                                            .connectTimeout(10000, TimeUnit.MILLISECONDS)
                                                            .retryOnConnectionFailure(true)
                                                            .protocols(Arrays.asList(Protocol.HTTP_1_1)).build();


        // Dữ liệu từ phía Sever trả về dưới dạng API --> GSON sẽ convert dữ liệu thành dạng JAVA
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpCliet)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
