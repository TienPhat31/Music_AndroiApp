package tdtu.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Activity.DanhsachtatcaalbumActivity;
import tdtu.appnhac.Adapter.AlbumAdapter;
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_Album extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtViewXemThemAlbum;

    AlbumAdapter albumAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);

        recyclerViewAlbum = view.findViewById(R.id.recyclerViewAlbum);
        txtViewXemThemAlbum = view.findViewById(R.id.textViewXemThemAlbum);

        txtViewXemThemAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcaalbumActivity.class);
                startActivity(intent);

            }
        });


        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callBack = dataService.getAlbum();

        callBack.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbum = (ArrayList<Album>) response.body();

                albumAdapter = new AlbumAdapter(getActivity(), arrayAlbum);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
}
