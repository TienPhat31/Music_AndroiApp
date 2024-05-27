package tdtu.appnhac.Service;


import android.util.Log;

// Giúp kết hơp 2 class APIRetrofitClinet và DataService
public class APIService {
    // Dùng trong class APIRetrofitClinet
    private static String base_url = "https://amphibian-responses.000webhostapp.com/Sever/";

    // Dùng trong class DataService:
    public static DataService getService() {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
