package tdtu.appnhac.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.R;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {

    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    
    class ViewHolder {
        TextView txtTenPlaylist;
        ImageView imgBackgroundPlaylist, imgPlaylist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_playlist, null);

            viewHolder = new ViewHolder();

            viewHolder.txtTenPlaylist = convertView.findViewById(R.id.textViewTenPlaylist);
            viewHolder.imgBackgroundPlaylist = convertView.findViewById(R.id.imageViewBackgroundPlaylist);
            viewHolder.imgPlaylist = convertView.findViewById(R.id.imageViewPlaylist);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Playlist playlist = getItem(position);

        Picasso.with(getContext()).load(playlist.getHinhNen()).into(viewHolder.imgBackgroundPlaylist);
        Picasso.with(getContext()).load(playlist.getHinhIcon()).into(viewHolder.imgPlaylist);
        viewHolder.txtTenPlaylist.setText(playlist.getTen());

        return convertView;
    }
}
