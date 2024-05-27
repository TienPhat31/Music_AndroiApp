package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import tdtu.appnhac.Adapter.MainViewPagerAdapter;
import tdtu.appnhac.Fragment.Fragment_Ca_Nhan;
import tdtu.appnhac.Fragment.Fragment_Tim_Kiem;
import tdtu.appnhac.Fragment.Fragment_Trang_Chu;
import tdtu.appnhac.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LookUp();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu());
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem());
        mainViewPagerAdapter.addFragment(new Fragment_Ca_Nhan());
        viewPager.setAdapter(mainViewPagerAdapter);


        Log.i("Check", "Hoàn thành 1");
        tabLayout.addTab(tabLayout.newTab().setText("Trang Chủ").setIcon(R.drawable.icontrangchu));
        tabLayout.addTab(tabLayout.newTab().setText("Tìm kiếm").setIcon(R.drawable.icontimkiem));
        tabLayout.addTab(tabLayout.newTab().setText("Cá Nhân").setIcon(R.drawable.ic_baseline_people_24));



        // Kết nối Tablayout với Adapter
        // --> Thay đổi ViewPager khi nhấn vào Tablayout
        Log.i("Check", "Hoàn thành 2");
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Chuyển tabLayout khi vuốt
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
        
    }

    private void LookUp() {
        viewPager = (ViewPager2) findViewById(R.id.myViewPager);
        tabLayout = (TabLayout) findViewById(R.id.myTabLayout);
    }
}