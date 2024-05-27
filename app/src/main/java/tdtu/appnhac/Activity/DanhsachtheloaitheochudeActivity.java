package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.DanhsachTheLoaiTheoChuDeAdapter;
import tdtu.appnhac.Model.ChuDe;
import tdtu.appnhac.Model.TheLoai;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;

    RecyclerView recyclerViewTheLoaiTheoChuDe;
    Toolbar toolbar;

    DanhsachTheLoaiTheoChuDeAdapter danhsachTheLoaiTheoChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude);

        DataIntent();
        init();

        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();

        Call<List<TheLoai>> callBack = dataService.getDanhSachTheLoaiTheoChuDe(chuDe.getIDChuDe());
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                Log.d("BBB", theLoaiArrayList.get(0).getTenTheLoai());


                danhsachTheLoaiTheoChuDeAdapter = new DanhsachTheLoaiTheoChuDeAdapter(DanhsachtheloaitheochudeActivity.this, theLoaiArrayList);
                recyclerViewTheLoaiTheoChuDe.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this, 2));
                recyclerViewTheLoaiTheoChuDe.setAdapter(danhsachTheLoaiTheoChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });

    }

    private void DataIntent() {
        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra("idChuDe")) {
                chuDe = (ChuDe) intent.getSerializableExtra("idChuDe");
            }
        }

    }

    private void init() {
        recyclerViewTheLoaiTheoChuDe = findViewById(R.id.recyclerViewTheLoaiTheoChuDe);
        toolbar = findViewById(R.id.toolBarTheLoaitheoChuDe);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}