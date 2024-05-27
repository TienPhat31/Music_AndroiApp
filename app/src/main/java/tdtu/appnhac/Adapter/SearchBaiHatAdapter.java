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

import androidx.annotation.ArrayRes;
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

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHatLovest> baiHatLovestsArrayList;

    public SearchBaiHatAdapter(Context context, ArrayList<BaiHatLovest> baiHatLovestsArrayList) {
        this.context = context;
        this.baiHatLovestsArrayList = baiHatLovestsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.dong_search_baihat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatLovest baiHatLovest = baiHatLovestsArrayList.get(position);
        holder.txtTenBaiHat.setText(baiHatLovest.getTenBaiHat());
        holder.txtTenCaSi.setText(baiHatLovest.getCaSiBaiHat());
        Picasso.with(context).load(baiHatLovest.getHinhBaiHat()).into(holder.imgHinhBaiHat);
    }

    @Override
    public int getItemCount() {
        return baiHatLovestsArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBaiHat, txtTenCaSi;
        ImageView imgLuotThich, imgHinhBaiHat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textViewSearchTenBaiHat);
            txtTenCaSi = itemView.findViewById(R.id.textViewSearchTenCaSi);
            imgHinhBaiHat = itemView.findViewById(R.id.imageViewSearchBaoHat);
            imgLuotThich = itemView.findViewById(R.id.imageViewSearchLuotThich);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("CaKhuc", baiHatLovestsArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.updateLuotthich("1",  baiHatLovestsArrayList.get(getPosition()).getIDBaiHat());
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
        }
    }
}
