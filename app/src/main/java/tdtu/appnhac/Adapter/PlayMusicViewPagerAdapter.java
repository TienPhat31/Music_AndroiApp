package tdtu.appnhac.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PlayMusicViewPagerAdapter extends FragmentPagerAdapter {

    public final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public PlayMusicViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void AddFragment(Fragment fragment) {
        fragmentArrayList.add(fragment);
    }
}
