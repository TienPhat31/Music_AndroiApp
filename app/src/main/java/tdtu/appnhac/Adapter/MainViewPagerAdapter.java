package tdtu.appnhac.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    // Danh sach chứa 2 Fragment (Trang chu, Tim Kiem)
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTittle = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayFragment.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayFragment.size();
    }
    
    public void addFragment(Fragment fragment) {
        arrayFragment.add(fragment);
    }




}
