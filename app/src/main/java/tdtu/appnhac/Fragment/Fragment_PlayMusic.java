package tdtu.appnhac.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tdtu.appnhac.Activity.PlayMusicActivity;
import tdtu.appnhac.Adapter.PlayMusicAdapter;
import tdtu.appnhac.R;

public class Fragment_PlayMusic extends Fragment {
    View view;
    RecyclerView recyclerView;

    PlayMusicAdapter playMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playmusic,container, false);
        recyclerView = view.findViewById(R.id.recyclerViewPlayMusic);

        if (PlayMusicActivity.arrayListDanhSachBaiHat.size() > 0) {
            playMusicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.arrayListDanhSachBaiHat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playMusicAdapter);
        }

        return view;
    }
}
