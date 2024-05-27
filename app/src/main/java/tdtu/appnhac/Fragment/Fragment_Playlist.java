package tdtu.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Activity.DanhsachbaihatActivite;
import tdtu.appnhac.Activity.DanhsachplaylistActivity;
import tdtu.appnhac.Adapter.PlaylistAdapter;
import tdtu.appnhac.Model.ChuDevaTheLoai;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIRetrofitClient;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_Playlist extends Fragment {

    View view;
    ListView listView;
    TextView txtTittlePlaylist, txtXemThemPlaylist;

    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> arrayListPlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn View
        view = inflater.inflate(R.layout.fragment_playlist, container, false);
        LookUp();
        GetData();

        txtXemThemPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void LookUp() {
        listView = view.findViewById(R.id.listViewPlaylist);
        txtTittlePlaylist = view.findViewById(R.id.textViewTittlePlaylist);
        txtXemThemPlaylist = view.findViewById(R.id.textViewViewMorePlaylist);
    }

    private void GetData() {
        DataService dataService = APIService.getService();

        Call<List<Playlist>> callback = dataService.GetPlaylistCurrent();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                arrayListPlaylist = (ArrayList<Playlist>) response.body();

                //Log.d("BBB", arrayListPlaylist.get(1).getTen());

                playlistAdapter = new PlaylistAdapter(getContext(), android.R.layout.simple_list_item_1, arrayListPlaylist);
                listView.setAdapter(playlistAdapter);
                setListViewHeightBasedOnChildren(listView);

                // Chuyển qua màn hình thứ 2 DSBaiHatActivity khi nhấn vào 1 trong ca Playlist
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), DanhsachbaihatActivite.class);
                        intent.putExtra("itemPlaylist", arrayListPlaylist.get(i));
                        startActivity(intent);
                    }
                });
                
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }



    // Set lai chiều cao cho ListViewPlaylist trên stackOverFlow
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            if(listItem != null){
                // This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();

            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
