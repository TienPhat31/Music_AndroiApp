package tdtu.appnhac.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import tdtu.appnhac.Adapter.PlayMusicViewPagerAdapter;
import tdtu.appnhac.Fragment.Fragment_DiaNhac;
import tdtu.appnhac.Fragment.Fragment_PlayMusic;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.R;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar seekBar;
    ImageButton imgNext, imgPlay, imgPre, imgRepeat, imgSuffle;
    ViewPager viewPagerPlayMusic;


    
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_PlayMusic fragment_playMusic;

    public static ArrayList<BaiHatLovest> arrayListDanhSachBaiHat = new ArrayList<>();
    public static PlayMusicViewPagerAdapter playMusicViewPagerAdapter;

    MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        init();
        GetData();

        if (arrayListDanhSachBaiHat.size() > 0) {
            new PlayMP3().execute(arrayListDanhSachBaiHat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
            viewPagerPlayMusic.setCurrentItem(2);
        }

        EventClick();
    }

    private void EventClick() {
        // Load Hình bái hát cho đĩa nhạc
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (playMusicViewPagerAdapter.getItem(0) != null) {
                    if (arrayListDanhSachBaiHat.size() > 0) {
                        fragment_diaNhac.Playnhac(arrayListDanhSachBaiHat.get(0).getHinhBaiHat(), arrayListDanhSachBaiHat.get(0).getTenBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);


        // Nút Play để phát nhạc
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                    Fragment_DiaNhac.objectAnimator.pause();
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                    Fragment_DiaNhac.objectAnimator.start();
                }
                viewPagerPlayMusic.setCurrentItem(2);
            }
        });

        // Nút Repeat lại bài nhạc
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkRandom == true) {
                        checkRandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgSuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        // Nút Random lại bài nhạc
        imgSuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkRandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgSuffle.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgSuffle.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                }else {
                    imgSuffle.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });


        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListDanhSachBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if (position < arrayListDanhSachBaiHat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;

                        if (repeat == true) {
                            if (position == 0) {
                                position = arrayListDanhSachBaiHat.size();
                            }
                            position -= 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrayListDanhSachBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            index = 1;
                        }

                        if (position > arrayListDanhSachBaiHat.size() -1 ) {
                            // trở lại bài đầu tiên
                            position = 0;
                        }

                        new PlayMP3().execute(arrayListDanhSachBaiHat.get(position).getLinkBaiHat());
                        fragment_diaNhac.Playnhac(arrayListDanhSachBaiHat.get(position).getHinhBaiHat(), arrayListDanhSachBaiHat.get(position).getTenBaiHat());
                        viewPagerPlayMusic.setCurrentItem(2);
                        UpdateTime();
                    }
                }

                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);

            }
        });

        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayListDanhSachBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }

                    if (position < arrayListDanhSachBaiHat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            // trở lại bài cuối cùng
                            position = arrayListDanhSachBaiHat.size() - 1;
                        }

                        if (repeat == true) {
                            if (position == 0) {
                                position = arrayListDanhSachBaiHat.size();
                            }
                            position += 1;
                        }

                        if (checkRandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrayListDanhSachBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            index = 1;
                        }

                        new PlayMP3().execute(arrayListDanhSachBaiHat.get(position).getLinkBaiHat());
                        fragment_diaNhac.Playnhac(arrayListDanhSachBaiHat.get(position).getHinhBaiHat(), arrayListDanhSachBaiHat.get(position).getTenBaiHat());
                        viewPagerPlayMusic.setCurrentItem(2);
                        UpdateTime();
                    }
                }

                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });



    }

    private void GetData() {
        Intent intent = getIntent();
        arrayListDanhSachBaiHat.clear();
        if (intent != null ) {
            if (intent.hasExtra("CaKhuc")) {
                BaiHatLovest baiHat = intent.getParcelableExtra("CaKhuc");
                // Toast.makeText(this, baiHat.getTenBaiHat(), Toast.LENGTH_SHORT).show();
                arrayListDanhSachBaiHat.add(baiHat);
            }
            if (intent.hasExtra("AllBaiHat")) {
                ArrayList<BaiHatLovest> arrayListBaiHat = intent.getParcelableArrayListExtra("AllBaiHat");
                arrayListDanhSachBaiHat = arrayListBaiHat;
            }
        }

    }

    private void init() {

        toolbar = findViewById(R.id.toolBarPlayNhac);
        seekBar = findViewById(R.id.seekBarVolumeSong);
        txtTimeSong = findViewById(R.id.textViewTimeSong);
        txtTotalTimeSong = findViewById(R.id.textViewTotalTimeSong);
        imgNext = findViewById(R.id.imageViewButtonNext);
        imgPlay = findViewById(R.id.imageViewButtonPlay);
        imgPre = findViewById(R.id.imageViewButtonPreview);
        imgRepeat = findViewById(R.id.imageViewButtonRepeat);
        imgSuffle = findViewById(R.id.imageViewButtonSuffleSong);
        viewPagerPlayMusic = findViewById(R.id.viewPagerPlayNhac);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    arrayListDanhSachBaiHat.clear();
                }
                finish();
            }
        });

        getSupportActionBar().setTitle("PLAY MUSIC");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        // Gán 2 Fragment (Đĩa nhạc, Danh sách bài hát)  cho ViewPager
        fragment_diaNhac = new Fragment_DiaNhac();
        fragment_playMusic = new Fragment_PlayMusic();
        playMusicViewPagerAdapter = new PlayMusicViewPagerAdapter(getSupportFragmentManager());
        playMusicViewPagerAdapter.AddFragment(fragment_playMusic);
        playMusicViewPagerAdapter.AddFragment(fragment_diaNhac);
        viewPagerPlayMusic.setAdapter(playMusicViewPagerAdapter);


        fragment_diaNhac = (Fragment_DiaNhac) playMusicViewPagerAdapter.getItem(1);

    }


    // Class để phát nhạc
    class PlayMP3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));

                    handler.postDelayed(this, 300);

                    // Nếu chạy hết bài
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (arrayListDanhSachBaiHat.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                        if (position < arrayListDanhSachBaiHat.size()) {
                            imgPlay.setImageResource(R.drawable.iconpause);
                            position++;

                            if (repeat == true) {
                                if (position == 0) {
                                    position = arrayListDanhSachBaiHat.size();
                                }
                                position -= 1;
                            }

                            if (checkRandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(arrayListDanhSachBaiHat.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                index = 1;
                            }

                            if (position > arrayListDanhSachBaiHat.size() -1 ) {
                                // trở lại bài đầu tiên
                                position = 0;
                            }

                            new PlayMP3().execute(arrayListDanhSachBaiHat.get(position).getLinkBaiHat());
                            fragment_diaNhac.Playnhac(arrayListDanhSachBaiHat.get(position).getHinhBaiHat(), arrayListDanhSachBaiHat.get(position).getTenBaiHat());
                            viewPagerPlayMusic.setCurrentItem(2);
                        }
                    }
                    imgPre.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPre.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    },4000);
                    next = false;
                    handler1.removeCallbacks(this);

                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        arrayListDanhSachBaiHat.clear();
    }
}