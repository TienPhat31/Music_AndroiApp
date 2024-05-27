package tdtu.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Activity.PlayMusicActivity;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>{

    Context context;
    ArrayList<BaiHatLovest> mangBaiHat;

    // Danh sach bai hat theo Quang cao

    public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHatLovest> arrayListBaiHat) {
        this.context = context;
        this.mangBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsachbaihat, parent, false);

        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatLovest baiHatLovest = mangBaiHat.get(position);

        holder.txtIndex.setText(position + 1 + "");
        holder.txtTenBaiHat.setText(baiHatLovest.getTenBaiHat());
        holder.txtTenCaSi.setText(baiHatLovest.getCaSiBaiHat());

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHat, txtTenCaSi;
        ImageView imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.textViewDanhSachIndex);
            txtTenBaiHat = itemView.findViewById(R.id.textViewTenBaiHat_QC);
            txtTenCaSi = itemView.findViewById(R.id.textViewTenCaSi_QC);
            imgLuotThich = itemView.findViewById(R.id.imageViewLuotThichDanhSachBaiHat);

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);

                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.updateLuotthich("1", mangBaiHat.get(getPosition()).getIDBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketQua = response.body();
                            if (ketQua.equals("SUCCESS")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotThich.setEnabled(false);
                }
            });

            // Chuyen qua trang Play Nhạc khi nhấn vào từng bài hát
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("CaKhuc", mangBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
