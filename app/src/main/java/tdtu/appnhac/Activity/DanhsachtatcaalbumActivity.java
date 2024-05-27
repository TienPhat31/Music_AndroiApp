package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.DanhsachAlbumAdapter;
import tdtu.appnhac.Adapter.DanhsachPlayListAdapter;
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhsachtatcaalbumActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    DanhsachAlbumAdapter danhsachAlbumAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatcaalbum);

        LookUp();
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callBack = dataService.getDanhSachTatCaAlbum();

        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayListAlbum = (ArrayList<Album>) response.body();
                Toast.makeText(DanhsachtatcaalbumActivity.this, arrayListAlbum.get(0).getTenAlbum(), Toast.LENGTH_SHORT).show();

                danhsachAlbumAdapter = new DanhsachAlbumAdapter(DanhsachtatcaalbumActivity.this, arrayListAlbum);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachtatcaalbumActivity.this, 2));
                recyclerView.setAdapter(danhsachAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        // Tao View back ve
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DANH SÁCH ALBUM");
        toolbar.setTitleTextColor(getResources().getColor(R.color.pupil));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void LookUp() {
        toolbar = findViewById(R.id.toolBarDanhSachAlbum);
        recyclerView = findViewById(R.id.recyclerViewDanhSachAlbum);

    }


}