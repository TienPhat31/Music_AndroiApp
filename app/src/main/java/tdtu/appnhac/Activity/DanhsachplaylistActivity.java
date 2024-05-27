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
import tdtu.appnhac.Adapter.DanhsachPlayListAdapter;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhsachplaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;


    DanhsachPlayListAdapter danhsachPlayListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachplaylist);

        LookUp();
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callBack = dataService.getDanhSachPlayList();

        callBack.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayListPlayList = (ArrayList<Playlist>) response.body();


                danhsachPlayListAdapter = new DanhsachPlayListAdapter(DanhsachplaylistActivity.this, arrayListPlayList);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachplaylistActivity.this, 2));
                recyclerView.setAdapter(danhsachPlayListAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }

    private void init() {
        setSupportActionBar(toolbar);
        // Tao View back ve
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DANH SÁCH PLAYLISTS");
        toolbar.setTitleTextColor(getResources().getColor(R.color.pupil));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void LookUp() {
        toolbar = findViewById(R.id.toolBarDanhSachPlaylist);
        recyclerView = findViewById(R.id.recyclerViewDanhSachPlaylist);

    }
}