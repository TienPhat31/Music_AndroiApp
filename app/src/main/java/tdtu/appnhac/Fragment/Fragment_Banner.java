package tdtu.appnhac.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.BannerAdapter;
import tdtu.appnhac.Model.QuangCao;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_Banner extends Fragment {

    View view;
    ViewPager viewPager;
    CircularProgressIndicator circularProgressIndicator;
    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;
    int currentItem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_banner, container, false);
        GetData();
        LookUp();
        return view;
    }

    private void LookUp() {
        viewPager = view.findViewById(R.id.viewpager);
        //circularProgressIndicator = view.findViewById(R.id.indicatorDefault);
    }

    // Nhận dữ liệu từ phía Server
    public void GetData() {

        // Đưa link chứa JSON vào biến dataService
        DataService dataService = APIService.getService();

        // Nơi chứa dữ liệu
        Call<List<QuangCao>> callBack = dataService.GetDataBanner();

        callBack.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
             // Lắng nghe khi có KQ trả về
                ArrayList<QuangCao> banners = (ArrayList<QuangCao>) response.body();

                // Truyền du lieu sau khi lay tu Sever --> vao ViewPager
                bannerAdapter = new BannerAdapter(getActivity(), banners);

                viewPager.setAdapter(bannerAdapter);

                // Quang cao tu di chuyen qua trang tiep theo
                handler = new Handler();
                // Runable chay khi handler yeu cau
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;

                        // Nếu lớn hơn số trang quảng cáo --> trở về trang đầu tiên
                        if (currentItem >= viewPager.getAdapter().getCount()) {
                            currentItem = 0;
                        }

                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 5000); //4s
                    }
                };
                handler.postDelayed(runnable, 5000);


            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                // Lắng nghe khi bị thất bại
            }
        });
    }
}
