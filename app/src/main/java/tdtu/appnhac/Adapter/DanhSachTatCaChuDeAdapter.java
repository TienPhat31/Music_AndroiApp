package tdtu.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.appnhac.Activity.DanhsachtheloaitheochudeActivity;
import tdtu.appnhac.Model.ChuDe;
import tdtu.appnhac.R;

public class DanhSachTatCaChuDeAdapter extends RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<ChuDe> arrayListChuDe;

    public DanhSachTatCaChuDeAdapter(Context context, ArrayList<ChuDe> arrayListChuDe) {
        this.context = context;
        this.arrayListChuDe = arrayListChuDe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_tatca_chude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChuDe chuDe = arrayListChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgChuDe);

    }

    @Override
    public int getItemCount() {
        return arrayListChuDe.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgChuDe = itemView.findViewById(R.id.imageViewChuDe);

            imgChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("idChuDe", arrayListChuDe.get(getPosition()));
                    context.startActivity(intent);

                }
            });

        }
    }
}
