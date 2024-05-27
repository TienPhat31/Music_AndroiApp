package tdtu.appnhac.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import tdtu.appnhac.R;

public class Fragment_DiaNhac extends Fragment {
    View view;
    TextView txtTenBaiHat;
    CircleImageView circleImageView;
    public static ObjectAnimator objectAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dianhac, container, false);
        circleImageView = view.findViewById(R.id.imgCircleDiaNhac);
        txtTenBaiHat = view.findViewById(R.id.txtTenBaiHat);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);

        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        return view;
    }

    public void Playnhac(String hinhanh, String tenBaiHat) {
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Picasso.with(getContext()).load(hinhanh).into(circleImageView);
                txtTenBaiHat.setText(tenBaiHat);
            }
        },300);
    }
}
