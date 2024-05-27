package tdtu.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.appnhac.Activity.PlayMusicActivity;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;
import tdtu.appnhac.Service.APIService;
import tdtu.appnhac.Service.DataService;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHatLovest> arrayListBaiHat;

    public BaiHatAdapter(Context context, ArrayList<BaiHatLovest> arrayListBaiHat) {
        this.context = context;
        this.arrayListBaiHat = arrayListBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.dong_baihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatLovest baiHatLovest = arrayListBaiHat.get(position);

        holder.txtTenCaSiBaiHat.setText(baiHatLovest.getCaSiBaiHat());
        holder.txtTenBaiHat.setText(baiHatLovest.getTenBaiHat());
        Picasso.with(context).load(baiHatLovest.getHinhBaiHat()).into(holder.imgHinhBaiHat);

    }

    @Override
    public int getItemCount() {
        return arrayListBaiHat.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHat, txtTenCaSiBaiHat;
        ImageView imgHinhBaiHat, imgLuotThich;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textViewTenBaiHat);
            txtTenCaSiBaiHat = itemView.findViewById(R.id.textViewTenCaSiBaiHat);
            imgHinhBaiHat = itemView.findViewById(R.id.imageViewHinhBaiHat);
            imgLuotThich = itemView.findViewById(R.id.imageViewLuotThich);

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);

                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.updateLuotthich("1", arrayListBaiHat.get(getPosition()).getIDBaiHat());
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
                    intent.putExtra("CaKhuc", arrayListBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
