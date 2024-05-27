package tdtu.appnhac.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BaiHatLovest implements Parcelable {

    @SerializedName("IDBaiHat")
    @Expose
    private String iDBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("CaSiBaiHat")
    @Expose
    private String caSiBaiHat;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;
    @SerializedName("LuotThich")
    @Expose
    private String luotThich;

    protected BaiHatLovest(Parcel in) {
        iDBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        caSiBaiHat = in.readString();
        linkBaiHat = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<BaiHatLovest> CREATOR = new Creator<BaiHatLovest>() {
        @Override
        public BaiHatLovest createFromParcel(Parcel in) {
            return new BaiHatLovest(in);
        }

        @Override
        public BaiHatLovest[] newArray(int size) {
            return new BaiHatLovest[size];
        }
    };

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

    public String getHinhBaiHat() {
    return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
    this.hinhBaiHat = hinhBaiHat;
    }

    public String getCaSiBaiHat() {
    return caSiBaiHat;
    }

    public void setCaSiBaiHat(String caSiBaiHat) {
    this.caSiBaiHat = caSiBaiHat;
    }

    public String getLinkBaiHat() {
    return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
    this.linkBaiHat = linkBaiHat;
    }

    public String getLuotThich() {
    return luotThich;
    }

    public void setLuotThich(String luotThich) {
    this.luotThich = luotThich;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iDBaiHat);
        parcel.writeString(tenBaiHat);
        parcel.writeString(hinhBaiHat);
        parcel.writeString(caSiBaiHat);
        parcel.writeString(linkBaiHat);
        parcel.writeString(luotThich);
    }
}
