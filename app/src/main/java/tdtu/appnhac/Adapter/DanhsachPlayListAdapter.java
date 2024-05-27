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
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;

public class DanhsachPlayListAdapter  extends RecyclerView.Adapter<DanhsachPlayListAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhsachPlayListAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_tatca_playlists, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Playlist playlist = playlistArrayList.get(position);
        holder.txtTendPlayList.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhIcon()).into(holder.imgHinhNenPlayList);

    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNenPlayList;
        TextView txtTendPlayList;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNenPlayList = itemView.findViewById(R.id.imgaeViewDanhSachPlayList);
            txtTendPlayList = itemView.findViewById(R.id.textViewDanhSachPlayList);

            // Chuyển qua màn hình thứ 2 DSBaiHatActivity khi nhấn vào 1 trong cac Playlist
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivite.class);
                    intent.putExtra("itemPlaylist", playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
