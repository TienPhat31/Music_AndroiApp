package tdtu.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.appnhac.Activity.DanhsachbaihatActivite;
import tdtu.appnhac.Model.QuangCao;
import tdtu.appnhac.R;

public class BannerAdapter extends PagerAdapter {

    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner, null);

        ImageView imgBackgroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView imgBanner = view.findViewById(R.id.imageViewBanner);
        TextView textViewTittleSongBanner = view.findViewById(R.id.textViewTittleBannerBaiHat);
        TextView textViewNoiDung = view.findViewById(R.id.texViewNoiDung);


        // Load 2 anh cho Banner va 2 TextView
        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgBackgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhBaihat()).into(imgBanner);
        textViewTittleSongBanner.setText(arrayListBanner.get(position).getTenBaiHat());
        textViewNoiDung.setText(arrayListBanner.get(position).getNoiDung());


        // Chuyển qua màn hình thứ 2 DSBaiHatActivity khi nhấn vào 1 bài trong Quảng Cáo
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhsachbaihatActivite.class);
                // Đẩy dữ liệu đi
                intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);

            }
        });

        container.addView(view);
        return view;
    }


   // Finish khi chuyen den Pager cuoi cung
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
