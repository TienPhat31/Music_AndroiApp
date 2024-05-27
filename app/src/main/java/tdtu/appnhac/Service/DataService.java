package tdtu.appnhac.Service;


import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tdtu.appnhac.Model.Album;
import tdtu.appnhac.Model.BaiHatLovest;
import tdtu.appnhac.Model.ChuDe;
import tdtu.appnhac.Model.ChuDevaTheLoai;
import tdtu.appnhac.Model.Playlist;
import tdtu.appnhac.Model.QuangCao;
import tdtu.appnhac.Model.TheLoai;

// Gửi lên phương thức --> sever đã kết nối được --> nhận dữ liệu về
public interface DataService {

    // Tương tác phía đường Link của Quảng Cáo  --> Nhận dữ liệu dưới dạng DS các Object
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();


    // Lấy dữ liệu từ Link của Playlist --> Nhận dữ liệu dưới dạng DS các Object
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrent();

    // Lấy dữ liệu từ Link của ChuDe&&TheLoai --> Nhận dữ liệu dưới dạng DS các Object
    @GET("ChuDevaTheLoaiTrongNgay.php")
    Call<ChuDevaTheLoai> getChuDe_TheLoaiCurrent();

    // Lấy dữ liệu từ Link của Album --> Nhận dữ liệu dưới dạng DS các Object
    @GET("album.php")
    Call<List<Album>> getAlbum();

    // Lấy dữ liệu từ Link của Baihat --> Nhận dữ liệu dưới dạng DS các Object
    @GET("baihatyeuthichnhat.php")
    Call<List<BaiHatLovest>> getBaiHatThichNhat();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatLovest>> getDanhSachBaiHatTheoQuangCao(@Field("idQuangCao") String idQuangCao);


    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatLovest>> getDanhSachBaiHatTheoPlayList(@Field("idPlayList") String idPlayList);

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> getDanhSachPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatLovest>> getDanhSachBaiHatTheoTheLoai(@Field("idTheLoai") String idTheLoai);


    @GET("danhsachtatcachude.php")
    Call<List<ChuDe>> getDanhSachTatCaChuDe();

    @FormUrlEncoded
    @POST("danhsachtheloaithechude.php")
    Call<List<TheLoai>> getDanhSachTheLoaiTheoChuDe(@Field("idChuDe") String idChuDe);

    @GET("danhsachalbum.php")
    Call<List<Album>> getDanhSachTatCaAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHatLovest>> getDanhSachBaiHatTheoAlbum(@Field("$idAlbum") String idAlbum);


    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> updateLuotthich(@Field("luotThich") String luotThich, @Field("idBaiHat") String idBaiHat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHatLovest>> searchBaiHat(@Field("tukhoa") String tukhoa);

}
