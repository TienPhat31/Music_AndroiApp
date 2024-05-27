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
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;

public class DanhsachAlbumAdapter extends RecyclerView.Adapter<DanhsachAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> playlistAlbum;

    public DanhsachAlbumAdapter(Context context, ArrayList<Album> playlistAlbum) {
        this.context = context;
        this.playlistAlbum = playlistAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danhsach_tatca_album, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = playlistAlbum.get(position);
        holder.txtTendAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhNenAlbum);
    }

    @Override
    public int getItemCount() {
        return playlistAlbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhNenAlbum;
        TextView txtTendAlbum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNenAlbum = itemView.findViewById(R.id.imageViewDanhSachAlbum);
            txtTendAlbum = itemView.findViewById(R.id.textViewDanhSachAlbum);

            imgHinhNenAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivite.class);
                    intent.putExtra("idAlbum", playlistAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }


}
