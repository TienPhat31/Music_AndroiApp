package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.DanhSachBaiHatAdapter;
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.Model.QuangCao;
import tdtu.appnhac.Model.TheLoai;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhsachbaihatActivite extends AppCompatActivity {

    QuangCao quangCao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    ImageView imageViewDanhSachCaKhuc;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;

    ArrayList<BaiHatLovest> arrayListBaiHat;

    DanhSachBaiHatAdapter danhSachBaiHatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat_activite);

        DataIntent();
        LookUp();
        init();

        // quang cao co ton tai
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")) {
            setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhBaihat());
            getDataQuangCao(quangCao.getIDQuangCao());
            //Log.d("BBB", quangCao.ge());

        }

        // Playlist co ton tai
        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getHinhIcon());
            getDataPlayList(playlist.getIDPlayList());
            //Log.d("BBB", quangCao.ge());
        }

        // TheLoai co ton tai
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            getDataTheLoai(theLoai.getIDTheLoai());
            //Log.d("BBB", quangCao.ge());
        }

        // Album co ton tai
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            getDataAlbum(album.getIDAlbum());
            //Log.d("BBB", quangCao.ge());
        }
    }
    // Tải danh sách bài hát theo IDALBUM
    private void getDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatLovest>> callBack = dataService.getDanhSachBaiHatTheoAlbum(idAlbum);

        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                arrayListBaiHat = (ArrayList<BaiHatLovest>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivite.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();

            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });

    }

    // Tải danh sách bài hát theo IDPLAYLIST
    private void getDataPlayList(String idPlayList) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatLovest>> callBack = dataService.getDanhSachBaiHatTheoPlayList(idPlayList);
        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                arrayListBaiHat = (ArrayList<BaiHatLovest>) response.body();
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivite.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });

    }

    // Tải danh sách bài hát theo IDQUẢNG CÁO
    private void getDataQuangCao(String idQuangCao) {
        DataService dataService = APIService.getService();

        Call<List<BaiHatLovest>> callBack = dataService.getDanhSachBaiHatTheoQuangCao(idQuangCao);
        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                arrayListBaiHat = (ArrayList<BaiHatLovest>) response.body();

                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivite.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });
    }

    // Tải danh sách bài hát theo IDTHELOAI
    private void getDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatLovest>> callBack = dataService.getDanhSachBaiHatTheoTheLoai(idTheLoai);

        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                arrayListBaiHat = (ArrayList<BaiHatLovest>) response.body();

                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhsachbaihatActivite.this, arrayListBaiHat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivite.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        Picasso.with(this).load(hinh).into(imageViewDanhSachCaKhuc);
    }

    private void init() {
        // Nut Back
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

    }

    private void LookUp() {
        coordinatorLayout = findViewById(R.id.cordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
        toolbar = findViewById(R.id.toolBarDanhSach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerViewDanhSachBaiHat);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageViewDanhSachCaKhuc = findViewById(R.id.imageViewDanhSachCaKhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();

        if (intent != null) {
            // Intent cua Quang Cao
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
                //Toast.makeText(DanhsachbaihatActivite.this, quangCao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            // Intent cua Playlist
            if (intent.hasExtra("itemPlaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemPlaylist");
            }

            // Intent cua TheLoai
            if (intent.hasExtra("idTheLoai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idTheLoai");
            }

            // Intent cua Album
            if (intent.hasExtra("idAlbum")) {
                album = (Album) intent.getSerializableExtra("idAlbum");
            }
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivite.this, PlayMusicActivity.class);
                intent.putExtra("AllBaiHat", arrayListBaiHat);
                startActivity(intent);
            }
        });

    }


}