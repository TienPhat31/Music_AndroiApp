package tdtu.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;

public class PlayMusicAdapter extends RecyclerView.Adapter<PlayMusicAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHatLovest> baiHatLovestArrayList;


    public PlayMusicAdapter(Context context, ArrayList<BaiHatLovest> baiHatLovestArrayList) {
        this.context = context;
        this.baiHatLovestArrayList = baiHatLovestArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view =inflater.inflate(R.layout.dong_playmusic, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHatLovest baiHatLovest = baiHatLovestArrayList.get(position);
        holder.txtTenBaiHat.setText(baiHatLovest.getTenBaiHat());
        holder.txtTenCaSi.setText(baiHatLovest.getCaSiBaiHat());
        holder.txtIndex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baiHatLovestArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenBaiHat, txtTenCaSi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIndex = itemView.findViewById(R.id.textViewPlayMusicIndex);
            txtTenBaiHat = itemView.findViewById(R.id.textViewTenPlayMusic);
            txtTenCaSi = itemView.findViewById(R.id.textViewTenCaSiPlayMusic);



        }
    }
}
