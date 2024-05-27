package tdtu.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.BaiHatAdapter;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_BaiHat extends Fragment {

    View view;
    RecyclerView recyclerView;

    BaiHatAdapter baiHatAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat_thichnhat,container, false);


        recyclerView = view.findViewById(R.id.recylerView_baihat_yeuthich);

        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();

        Call<List<BaiHatLovest>> callBack = dataService.getBaiHatThichNhat();

        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                ArrayList<BaiHatLovest> arrayListBaiHat = (ArrayList<BaiHatLovest>) response.body();
                baiHatAdapter = new BaiHatAdapter(getActivity(), arrayListBaiHat);


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(baiHatAdapter);
                Log.d("BBB", "Thành công");


                //Log.d("BBB", arrayListBaiHat.get(0).getTenBaiHat());

            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });
    }
}
