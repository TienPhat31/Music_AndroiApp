package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.DanhSachTatCaChuDeAdapter;
import tdtu.appnhac.Model.ChuDe;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhsachtatcachudeActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachTatCaChuDe;

    DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter;


    ArrayList<ChuDe> chuDeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcachude);

        LookUp();
        init ();
        getData();
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callBack = dataService.getDanhSachTatCaChuDe();

        callBack.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDeArrayList = (ArrayList<ChuDe>) response.body();

                danhSachTatCaChuDeAdapter = new DanhSachTatCaChuDeAdapter(DanhsachtatcachudeActivity.this, chuDeArrayList);
                recyclerViewDanhSachTatCaChuDe.setLayoutManager(new GridLayoutManager(DanhsachtatcachudeActivity.this, 1));

                recyclerViewDanhSachTatCaChuDe.setAdapter(danhSachTatCaChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void LookUp() {
        toolbar = findViewById(R.id.toolBarDanhSachTatCaChuDe);
        recyclerViewDanhSachTatCaChuDe = findViewById(R.id.recylerViewDanhSachTatCaChuDe);
    }

    private void init () {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getColor(R.color.pupil));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}