package tdtu.appnhac.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuangCao implements Serializable {

    @SerializedName("IDQuangCao")
    @Expose
    private String iDQuangCao;
    @SerializedName("HinhAnh")
    @Expose
    private String hinhAnh;
    @SerializedName("NoiDung")
    @Expose
    private String noiDung;
    @SerializedName("IDBaiHat")
    @Expose
    private String iDBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaihat")
    @Expose
    private String hinhBaihat;

    public String getIDQuangCao() {
    return iDQuangCao;
    }

    public void setIDQuangCao(String iDQuangCao) {
    this.iDQuangCao = iDQuangCao;
    }

    public String getHinhAnh() {
    return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
    this.hinhAnh = hinhAnh;
    }

    public String getNoiDung() {
    return noiDung;
    }

    public void setNoiDung(String noiDung) {
    this.noiDung = noiDung;
    }

    public String getIDBaiHat() {
    return iDBaiHat;
    }

    public void setIDBaiHat(String iDBaiHat) {
    this.iDBaiHat = iDBaiHat;
    }

    public String getTenBaiHat() {
    return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
    this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaihat() {
    return hinhBaihat;
    }

    public void setHinhBaihat(String hinhBaihat) {
    this.hinhBaihat = hinhBaihat;
    }

}