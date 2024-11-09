package Class;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Quoc Manh
 */
public class KhachHang implements Serializable {

    private String makhachhang;
    private String hovaten;
    private String gioitinh;
    private String diachi;
    private String sdt;

    public KhachHang(String makhachhang, String hovaten, String gioitinh, String diachi, String sdt) {
        this.makhachhang = makhachhang;
        this.hovaten = hovaten;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    }


