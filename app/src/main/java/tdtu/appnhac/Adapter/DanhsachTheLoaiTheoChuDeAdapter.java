package tdtu.appnhac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.appnhac.Activity.DanhsachbaihatActivite;
import tdtu.appnhac.Model.TheLoai;
import tdtu.appnhac.R;

public class DanhsachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhsachTheLoaiTheoChuDeAdapter.ViewHolder> {

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhsachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_theloai_theo_chude, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imageViewHinhNen);
        holder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewHinhNen;
        TextView txtTenTheLoai, txtTenCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewHinhNen = itemView.findViewById(R.id.imageViewTheLoaiTheoChuDe);
            txtTenTheLoai = itemView.findViewById(R.id.textViewTenTheLoaiTheoChuDe);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivite.class);
                    intent.putExtra("idTheLoai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);

                }
            });
        }
    }
}
