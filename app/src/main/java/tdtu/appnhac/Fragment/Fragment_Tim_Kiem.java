package tdtu.appnhac.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Adapter.SearchBaiHatAdapter;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView txtNoData;
    SearchBaiHatAdapter searchBaiHatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolBarSearchBaiHat);
        recyclerView = view.findViewById(R.id.recyclerViewSearchBaiHat);
        txtNoData = view.findViewById(R.id.textViewNoData);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("BBB", "Search thành công");
                SearchBaiHatTheoTuKhao(s);
                Log.d("BBB", "Search thành công 1");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchBaiHatTheoTuKhao(String query) {
        DataService dataService = APIService.getService();
        Call<List<BaiHatLovest>> callBack = dataService.searchBaiHat(query);
        callBack.enqueue(new Callback<List<BaiHatLovest>>() {
            @Override
            public void onResponse(Call<List<BaiHatLovest>> call, Response<List<BaiHatLovest>> response) {
                ArrayList<BaiHatLovest> mangBaiHat = (ArrayList<BaiHatLovest>) response.body();

                if (mangBaiHat.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), mangBaiHat);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchBaiHatAdapter);

                    txtNoData.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    txtNoData.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<List<BaiHatLovest>> call, Throwable t) {

            }
        });
    }
}
