package tdtu.appnhac.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Activity.DanhsachbaihatActivite;
import tdtu.appnhac.Activity.DanhsachtatcachudeActivity;
import tdtu.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import tdtu.appnhac.Model.ChuDe;
import tdtu.appnhac.Model.ChuDevaTheLoai;
import tdtu.appnhac.Model.TheLoai;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class Fragment_ChuDe_TheLoai extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThemChuDe_TheLoai;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai, null);

        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        txtXemThemChuDe_TheLoai = view.findViewById(R.id.textViewXemThemChuDe_TheLoai);

        // Bat su kien Xem Them trong phan CHUDE VA THELOAI
        txtXemThemChuDe_TheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        getData();
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<ChuDevaTheLoai> callback = dataService.getChuDe_TheLoaiCurrent();

        callback.enqueue(new Callback<ChuDevaTheLoai>() {
            @Override
            public void onResponse(Call<ChuDevaTheLoai> call, Response<ChuDevaTheLoai> response) {
                ChuDevaTheLoai chuDevaTheLoai = response.body();

                //Log.d("BBB", chuDevaTheLoai.getTheLoai().get(0).getTenTheLoai());

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDevaTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDevaTheLoai.getTheLoai());

                // Đưa dữ liệu vào ViewGroup --> rồi lấy ViewGroup đưa vào horizontallScrollView
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(530, 270);
                layoutParams.setMargins(10,20,10,30);

                // Danh sach Chu De
                for (int i = 0; i < (chuDeArrayList.size()) ; i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(8);

                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }

                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("idChuDe", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                // Danh Sach The Loai
                for (int j = 0; j < (theLoaiArrayList.size()) ; j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(8);

                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    // Chuyển qua màn hình thứ 2 DSBaiHatActivity khi nhấn vào 1 trong ca TheLoai
                    int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivite.class);
                            intent.putExtra("idTheLoai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDevaTheLoai> call, Throwable t) {

            }
        });
    }


}
