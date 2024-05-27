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

import java.lang.reflect.Array;
import java.util.ArrayList;

import tdtu.appnhac.Activity.DanhsachbaihatActivite;
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.R;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arrayListAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> arrayListAlbum) {
        this.context = context;
        this.arrayListAlbum = arrayListAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrayListAlbum.get(position);

        holder.txtTenCaSiAlbum.setText(album.getTenCaSiAlbum());
        holder.txtViewTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return arrayListAlbum.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhAlbum;
        TextView txtViewTenAlbum, txtTenCaSiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhAlbum = itemView.findViewById(R.id.imageViewAlbum);
            txtViewTenAlbum = itemView.findViewById(R.id.textViewTenAlbum);
            txtTenCaSiAlbum = itemView.findViewById(R.id.textViewTenCaSi);

            imgHinhAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhsachbaihatActivite.class);
                    intent.putExtra("idAlbum", arrayListAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
