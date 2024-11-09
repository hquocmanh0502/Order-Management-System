/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.Serializable;

/**
 *
 * @author Quoc Manh
 */
public class DonHang implements Serializable {
    private String masanpham;
    private String tensanpham;
    private int giasanpham;
    private int soluong;
    private int tongtien;

    public DonHang(String masanpham, String tensanpham, int giasanpham, int soluong, int tongtien) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.giasanpham = giasanpham;
        this.soluong = soluong;
        this.tongtien = tongtien;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
    
}
