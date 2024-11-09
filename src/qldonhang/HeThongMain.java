/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package qldonhang;

import Class.KhachHang;
import Class.DonHang;
import Class.LuuFileDonHang;
import Class.LuuFileKhachHang;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.List;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class HeThongMain extends javax.swing.JFrame {

    private DefaultTableModel model;
    private DefaultTableModel model1;
    private JOptionPane optionPane1;
    private static final long serialVersionUID = 1L;
    private LuuFileDonHang luuFiledonhang = new LuuFileDonHang();
    private LuuFileKhachHang luuFilekhachhang = new LuuFileKhachHang();
    ArrayList<KhachHang> list = new ArrayList<KhachHang>();
    KhachHang x;
    private static int pos = 0;
    private static int check = 0;
    ArrayList<DonHang> list1 = new ArrayList<DonHang>();
    DonHang x1;
    private static int pos1 = 0;
    private static int check1 = 0;

    public HeThongMain() {
        initComponents();
        list1 = (ArrayList<DonHang>) luuFiledonhang.readData();
        list = (ArrayList<KhachHang>) luuFilekhachhang.readData();
        luuFiledonhang.saveData(list1);
        luuFilekhachhang.saveData(list);
        
        model = (DefaultTableModel) jTable.getModel();
        model1 = (DefaultTableModel) jTable1.getModel();
        if (list1.isEmpty()) {
            list1.add(new DonHang("4567", "Vot cau long", Integer.parseInt("40000"), Integer.parseInt("6"), 240000));
            list1.add(new DonHang("4560", "Sua Milo", Integer.parseInt("10000"), Integer.parseInt("4"), 40000));
            list1.add(new DonHang("1567", "Keo cao su", Integer.parseInt("5000"), Integer.parseInt("5"), 25000));
            list1.add(new DonHang("3467", "Gay Bia", Integer.parseInt("100000"), Integer.parseInt("2"), 200000));
        }

        if (list.isEmpty()) {
            list.add(new KhachHang("4567", "Hoang Quoc Manh", "Nam", "Ha Tinh", "0344868243"));
            list.add(new KhachHang("4560", "Duong Thi Quynh Anh", "Nu", "Ha Tinh", "0344868243"));
            list.add(new KhachHang("1567", "Hoang Quoc Manh", "Nam", "Ha Tinh", "0344868243"));
            list.add(new KhachHang("3467", "Hoang Quoc Manh", "Nam", "Ha Tinh", "0344868243"));
        }
        

        View();
        ViewTable();
        View1();
        ViewTable1();
        thongke();
    }

    private void thongke() {
        int totalCustomers = list.size();
        sokhachhang.setText("" + totalCustomers);
        int totaldonhang;
        if (list1.isEmpty()) {
            totaldonhang = 0;
        } else {
            totaldonhang = list1.size();
        }
        sodonhang.setText("" + totaldonhang);
        double totalRevenue = 0.0;

        for (DonHang donHang : list1) {
            // Tính giá trị của từng đơn hàng (số lượng * giá sản phẩm)
            double orderValue = donHang.getSoluong() * donHang.getGiasanpham();
            totalRevenue += orderValue;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // Chuyển đổi doanh thu thành chuỗi đã định dạng
        String doanhThuDaDinhDang = decimalFormat.format(totalRevenue);
        doanhthu.setText(doanhThuDaDinhDang);
    }

    private void capNhatSoThuTu() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }

    private void capNhatSoThuTu1() {
        for (int i = 0; i < model1.getRowCount(); i++) {
            model1.setValueAt(i + 1, i, 0);
        }
    }

    public void View() {
        x = list.get(pos);
        this.txtmkh.setText(x.getMakhachhang());
        this.txthovaten.setText(x.getHovaten());
        this.cbbgioitinh.setSelectedItem(x.getGioitinh());
        this.txtdiachi.setText(x.getDiachi());
        this.txtsdt.setText(x.getSdt());
        OnOff(true, false);
    }

    public void View1() {
        x1 = list1.get(pos1);
        this.txtmadonhang.setText(x1.getMasanpham());
        this.txttensanpham.setText(x1.getTensanpham());
        this.cbbsoluong.setSelectedItem(x1.getSoluong());
        this.txtgiasanpham.setText(String.valueOf(x1.getGiasanpham()));
        OnOff1(true, false);
    }

    public void ViewTable() {
        DefaultTableModel model = (DefaultTableModel) this.jTable.getModel();
        model.setNumRows(0);
        int n = 1;
        for (KhachHang x : list) {
            model.addRow(new Object[]{n++, x.getMakhachhang(), x.getHovaten(), x.getGioitinh(), x.getDiachi(), x.getSdt()});
        }
    }

    public void ViewTable1() {
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        model.setNumRows(0);
        int n = 1;
        for (DonHang x1 : list1) {
            model.addRow(new Object[]{n++, x1.getMasanpham(), x1.getTensanpham(), x1.getSoluong(), x1.getGiasanpham(), x1.getSoluong() * x1.getGiasanpham()});
        }
    }

    public void OnOff(boolean a, boolean b) {
        this.btnLuu.show(b);
        this.btnHuy.show(b);
        this.btnThem.show(a);
        this.btnSua.show(a);
        this.btnXoa.show(a);
    }

    public void OnOff1(boolean a, boolean b) {
        this.btnLuu1.show(b);
        this.btnHuy1.show(b);
        this.btnThem1.show(a);
        this.btnSua1.show(a);
        this.btnXoa1.show(a);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        side_pane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnkhachhang = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btndonhang = new javax.swing.JPanel();
        jlabelhi = new javax.swing.JLabel();
        btntimkiem = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnhotro = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btntrangchu = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        ptrangchu = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel25 = new javax.swing.JLabel();
        sodonhang = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        jLabel29 = new javax.swing.JLabel();
        sokhachhang = new javax.swing.JLabel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        doanhthu = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        pdonhang = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnHuy1 = new javax.swing.JButton();
        btnLuu1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        txtgiasanpham = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txttensanpham = new javax.swing.JTextField();
        txtmadonhang = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbbsoluong = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pkhachhang = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmkh = new javax.swing.JTextField();
        txthovaten = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbgioitinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnHuy = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        ptimkiem = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nhapmadonhang = new javax.swing.JTextField();
        ptimkiem1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        nhapmakhachhang = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        lbmadonhang = new javax.swing.JLabel();
        lbtensanpham = new javax.swing.JLabel();
        lbsoluong = new javax.swing.JLabel();
        lbgiasanpham = new javax.swing.JLabel();
        lbtongtien = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lbmakhachhang = new javax.swing.JLabel();
        lbtenkhachhang = new javax.swing.JLabel();
        lbgioitinh = new javax.swing.JLabel();
        lbdiachi = new javax.swing.JLabel();
        lbsodienthoai = new javax.swing.JLabel();
        btntimkiemm = new com.k33ptoo.components.KButton();
        btntimkiemm1 = new com.k33ptoo.components.KButton();
        btnreset = new com.k33ptoo.components.KButton();
        btnreset1 = new com.k33ptoo.components.KButton();
        photro = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(960, 622));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        side_pane.setBackground(new java.awt.Color(54, 33, 89));
        side_pane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/z5091675000313_826ec383acbbf134515d950ab7842cd6.jpg"))); // NOI18N
        side_pane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, 240, 240));

        btnkhachhang.setBackground(new java.awt.Color(54, 33, 89));
        btnkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnkhachhangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnkhachhangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnkhachhangMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Person_32px.png"))); // NOI18N
        jLabel9.setText("Khách Hàng");

        javax.swing.GroupLayout btnkhachhangLayout = new javax.swing.GroupLayout(btnkhachhang);
        btnkhachhang.setLayout(btnkhachhangLayout);
        btnkhachhangLayout.setHorizontalGroup(
            btnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnkhachhangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        btnkhachhangLayout.setVerticalGroup(
            btnkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnkhachhangLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        side_pane.add(btnkhachhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 210, 50));

        btndonhang.setBackground(new java.awt.Color(54, 33, 89));
        btndonhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndonhangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btndonhangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btndonhangMouseExited(evt);
            }
        });

        jlabelhi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlabelhi.setForeground(new java.awt.Color(255, 255, 255));
        jlabelhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Secured_Letter_25px_2.png"))); // NOI18N
        jlabelhi.setText("  Đơn Hàng");

        javax.swing.GroupLayout btndonhangLayout = new javax.swing.GroupLayout(btndonhang);
        btndonhang.setLayout(btndonhangLayout);
        btndonhangLayout.setHorizontalGroup(
            btndonhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btndonhangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jlabelhi)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        btndonhangLayout.setVerticalGroup(
            btndonhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btndonhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlabelhi, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        side_pane.add(btndonhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 210, 50));

        btntimkiem.setBackground(new java.awt.Color(54, 33, 89));
        btntimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntimkiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntimkiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntimkiemMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Search_18px.png"))); // NOI18N
        jLabel12.setText("   Tìm Kiếm");

        javax.swing.GroupLayout btntimkiemLayout = new javax.swing.GroupLayout(btntimkiem);
        btntimkiem.setLayout(btntimkiemLayout);
        btntimkiemLayout.setHorizontalGroup(
            btntimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btntimkiemLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel12)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        btntimkiemLayout.setVerticalGroup(
            btntimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btntimkiemLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel12)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        side_pane.add(btntimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 400, 230, 50));

        btnhotro.setBackground(new java.awt.Color(54, 33, 89));
        btnhotro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhotroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnhotroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnhotroMouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Open_Envelope_32px.png"))); // NOI18N
        jLabel13.setText(" Hỗ Trợ");

        javax.swing.GroupLayout btnhotroLayout = new javax.swing.GroupLayout(btnhotro);
        btnhotro.setLayout(btnhotroLayout);
        btnhotroLayout.setHorizontalGroup(
            btnhotroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnhotroLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        btnhotroLayout.setVerticalGroup(
            btnhotroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnhotroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        side_pane.add(btnhotro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 210, 50));

        btntrangchu.setBackground(new java.awt.Color(54, 33, 89));
        btntrangchu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btntrangchuMouseDragged(evt);
            }
        });
        btntrangchu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntrangchuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btntrangchuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btntrangchuMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btntrangchuMousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Home_32px.png"))); // NOI18N
        jLabel11.setText("Trang Chủ");

        javax.swing.GroupLayout btntrangchuLayout = new javax.swing.GroupLayout(btntrangchu);
        btntrangchu.setLayout(btntrangchuLayout);
        btntrangchuLayout.setHorizontalGroup(
            btntrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btntrangchuLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        btntrangchuLayout.setVerticalGroup(
            btntrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btntrangchuLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        side_pane.add(btntrangchu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, 50));

        getContentPane().add(side_pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 600));

        jPanel7.setBackground(new java.awt.Color(122, 72, 221));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("TRANG CHỦ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addContainerGap(753, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(51, 255, 255));
        kGradientPanel1.setkGradientFocus(400);
        kGradientPanel1.setkStartColor(new java.awt.Color(204, 102, 255));

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("ĐƠN HÀNG");

        sodonhang.setBackground(new java.awt.Color(255, 255, 255));
        sodonhang.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sodonhang.setForeground(new java.awt.Color(255, 255, 255));
        sodonhang.setText("4");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(sodonhang)
                        .addGap(111, 111, 111))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel25))
                        .addGap(98, 98, 98))))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(sodonhang)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 0, 0));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 153, 153));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("KHÁCH HÀNG");

        sokhachhang.setBackground(new java.awt.Color(255, 255, 255));
        sokhachhang.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        sokhachhang.setForeground(new java.awt.Color(255, 255, 255));
        sokhachhang.setText("4");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel29))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(sokhachhang)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(sokhachhang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel3.setkEndColor(new java.awt.Color(153, 255, 255));
        kGradientPanel3.setkStartColor(new java.awt.Color(0, 204, 204));

        doanhthu.setBackground(new java.awt.Color(255, 255, 255));
        doanhthu.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        doanhthu.setForeground(new java.awt.Color(255, 255, 255));
        doanhthu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        doanhthu.setText("      505.000");

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("TỔNG DOANH THU");

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("VND");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jLabel33)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(doanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doanhthu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ptrangchuLayout = new javax.swing.GroupLayout(ptrangchu);
        ptrangchu.setLayout(ptrangchuLayout);
        ptrangchuLayout.setHorizontalGroup(
            ptrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptrangchuLayout.createSequentialGroup()
                .addGroup(ptrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ptrangchuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ptrangchuLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ptrangchuLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1086, Short.MAX_VALUE))
        );
        ptrangchuLayout.setVerticalGroup(
            ptrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptrangchuLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(ptrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", ptrangchu);

        pdonhang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(122, 72, 221));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pdonhang.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, 750, 130));

        btnHuy1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel.png"))); // NOI18N
        btnHuy1.setText("Hủy");
        btnHuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy1ActionPerformed(evt);
            }
        });
        pdonhang.add(btnHuy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 110, 40));

        btnLuu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnLuu1.setText("Lưu");
        btnLuu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuu1ActionPerformed(evt);
            }
        });
        pdonhang.add(btnLuu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, 40));

        jLabel23.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/How Many Quest.png"))); // NOI18N
        jLabel23.setText("Số lượng :");
        pdonhang.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        btnThem1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add_1.png"))); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        pdonhang.add(btnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 130, 40));

        btnSua1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Tools.png"))); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });
        pdonhang.add(btnSua1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, 40));

        btnXoa1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnXoa1.setText("Xóa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });
        pdonhang.add(btnXoa1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, 40));

        txtgiasanpham.setText("jTextField3");
        txtgiasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiasanphamActionPerformed(evt);
            }
        });
        pdonhang.add(txtgiasanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 157, -1));

        jLabel24.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Price Tag USD_4.png"))); // NOI18N
        jLabel24.setText("Giá Sản Phẩm :");
        pdonhang.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 130, -1));

        txttensanpham.setText("jTextField2");
        pdonhang.add(txttensanpham, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 127, -1));

        txtmadonhang.setText("jTextField1");
        txtmadonhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmadonhangActionPerformed(evt);
            }
        });
        pdonhang.add(txtmadonhang, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 127, -1));

        jLabel26.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Code.png"))); // NOI18N
        jLabel26.setText("Mã Đơn Hàng :");
        pdonhang.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel27.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Product.png"))); // NOI18N
        jLabel27.setText("Tên Sản Phẩm :");
        pdonhang.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 140, -1));

        cbbsoluong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        pdonhang.add(cbbsoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Đơn Hàng", "Tên Sản Phẩm", "Số Lượng", "Giá Thành", "Tổng Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        pdonhang.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 431, 723, 175));

        jTabbedPane1.addTab("tab2", pdonhang);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        panel3.setBackground(new java.awt.Color(204, 204, 204));
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Checked Identification Documents.png"))); // NOI18N
        jLabel3.setText("Mã Khách Hàng :");
        panel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ABC.png"))); // NOI18N
        jLabel4.setText("Họ và tên :");
        panel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

        txtmkh.setText("jTextField1");
        txtmkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmkhActionPerformed(evt);
            }
        });
        panel3.add(txtmkh, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 9, 127, -1));

        txthovaten.setText("jTextField2");
        panel3.add(txthovaten, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 127, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Gender.png"))); // NOI18N
        jLabel5.setText("Giới tính :");
        panel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        cbbgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        panel3.add(cbbgioitinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Address.png"))); // NOI18N
        jLabel6.setText("Địa Chỉ :");
        panel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 95, -1));

        jLabel8.setFont(new java.awt.Font("Roboto Slab Medium", 0, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Phone Contact.png"))); // NOI18N
        jLabel8.setText("Số điện thoại :");
        panel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 128, -1));

        txtdiachi.setText("jTextField3");
        txtdiachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiachiActionPerformed(evt);
            }
        });
        panel3.add(txtdiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 157, -1));

        txtsdt.setText("jTextField4");
        panel3.add(txtsdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 71, 158, -1));

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Khách Hàng", "Họ và tên", "Giới tính", "Địa chỉ", "Số điện thoại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        panel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 224, 731, 160));

        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cancel.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        panel3.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, 40));

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Tools.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        panel3.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, -1, 40));

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        panel3.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, 40));

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add_1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setAutoscrolls(true);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        panel3.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, 40));

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        panel3.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 110, 40));

        jPanel3.setBackground(new java.awt.Color(122, 72, 221));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("KHÁCH HÀNG");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(754, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(829, 829, 829))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(380, 380, 380))))
        );

        javax.swing.GroupLayout pkhachhangLayout = new javax.swing.GroupLayout(pkhachhang);
        pkhachhang.setLayout(pkhachhangLayout);
        pkhachhangLayout.setHorizontalGroup(
            pkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pkhachhangLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1005, Short.MAX_VALUE))
        );
        pkhachhangLayout.setVerticalGroup(
            pkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pkhachhangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab3", pkhachhang);

        jPanel5.setBackground(new java.awt.Color(122, 72, 221));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("TÌM KIẾM");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addContainerGap(754, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        nhapmadonhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapmadonhangActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(122, 72, 221));

        jLabel39.setBackground(new java.awt.Color(255, 255, 255));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("TÌM KIẾM");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addContainerGap(754, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Search_18px.png"))); // NOI18N
        jLabel41.setText("Nhập Mã Đơn Hàng :");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Search_18px.png"))); // NOI18N
        jLabel42.setText("Nhập Mã Khách Hàng :");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Thông Tin Đơn Hàng :");

        lbmadonhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbmadonhang.setText("Mã Đơn Hàng :");

        lbtensanpham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbtensanpham.setText("Tên Sản Phẩm :");

        lbsoluong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsoluong.setText("Số lượng :");

        lbgiasanpham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbgiasanpham.setText("Giá sản phẩm :");

        lbtongtien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbtongtien.setText("Tổng tiền :");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText("Thông Tin Khách Hàng :");

        lbmakhachhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbmakhachhang.setText("Mã Khách Hàng : ");

        lbtenkhachhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbtenkhachhang.setText("Tên Khách Hàng :");

        lbgioitinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbgioitinh.setText("Giới Tính :");

        lbdiachi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbdiachi.setText("Địa Chỉ :");

        lbsodienthoai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbsodienthoai.setText("Số Điện Thoại :");

        btntimkiemm.setText("Tìm Kiếm");
        btntimkiemm.setkEndColor(new java.awt.Color(51, 0, 153));
        btntimkiemm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemmActionPerformed(evt);
            }
        });

        btntimkiemm1.setText("Tìm Kiếm");
        btntimkiemm1.setkEndColor(new java.awt.Color(51, 0, 153));
        btntimkiemm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemm1ActionPerformed(evt);
            }
        });

        btnreset.setText("Reset Thông Tin Đơn Hàng");
        btnreset.setkEndColor(new java.awt.Color(51, 0, 153));
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btnreset1.setText("Reset Thông Tin Khách Hàng");
        btnreset1.setkEndColor(new java.awt.Color(51, 0, 153));
        btnreset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreset1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ptimkiem1Layout = new javax.swing.GroupLayout(ptimkiem1);
        ptimkiem1.setLayout(ptimkiem1Layout);
        ptimkiem1Layout.setHorizontalGroup(
            ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptimkiem1Layout.createSequentialGroup()
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ptimkiem1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ptimkiem1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ptimkiem1Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btntimkiemm, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(ptimkiem1Layout.createSequentialGroup()
                                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbmadonhang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbtensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbgiasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)))
                        .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbdiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbtenkhachhang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                                .addComponent(lbsodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ptimkiem1Layout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nhapmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btntimkiemm1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ptimkiem1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222)
                        .addComponent(btnreset1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1086, 1086, 1086))
        );
        ptimkiem1Layout.setVerticalGroup(
            ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptimkiem1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(btntimkiemm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(nhapmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btntimkiemm1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmadonhang)
                    .addComponent(lbmakhachhang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbtensanpham)
                    .addComponent(lbtenkhachhang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbsoluong)
                    .addComponent(lbgioitinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbgiasanpham)
                    .addComponent(lbdiachi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbsodienthoai)
                    .addComponent(lbtongtien))
                .addGap(18, 18, 18)
                .addGroup(ptimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnreset, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ptimkiemLayout = new javax.swing.GroupLayout(ptimkiem);
        ptimkiem.setLayout(ptimkiemLayout);
        ptimkiemLayout.setHorizontalGroup(
            ptimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptimkiemLayout.createSequentialGroup()
                .addGroup(ptimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ptimkiemLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ptimkiemLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(nhapmadonhang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1086, Short.MAX_VALUE))
            .addGroup(ptimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ptimkiemLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ptimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ptimkiemLayout.setVerticalGroup(
            ptimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ptimkiemLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(nhapmadonhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(336, Short.MAX_VALUE))
            .addGroup(ptimkiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ptimkiemLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ptimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab4", ptimkiem);

        photro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(122, 72, 221));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("HỖ TRỢ");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("PHẦN MỀM QUẢN LÝ ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(754, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        photro.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("HỖ TRỢ KHÁCH HÀNG");
        photro.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 212, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("- Tìm kiếm khách hàng");
        photro.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("4.Khắc phục sự cố:");
        photro.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("- Chỉnh sửa hoặc hủy đơn hàng");
        photro.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("1.Quản lý khách hàng:");
        photro.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("- Thêm, chỉnh sửa, xóa và xem thông tin khách hàng");
        photro.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("- Tạo thống kê doanh thu");
        photro.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("- Xem chi tiết đơn hàng");
        photro.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setText("2.Xử lý đơn hàng:");
        photro.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("- Tạo đơn hàng mới");
        photro.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel48.setText("3.Tạo báo cáo:");
        photro.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("- Truy cập báo cáo bán hàng");
        photro.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("- Thông tin liên hệ để được trợ giúp thêm : 22010144@st.phenikaa-uni.edu.vn");
        photro.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, -1));

        jTabbedPane1.addTab("tab5", photro);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, -40, -1, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged

    }//GEN-LAST:event_jPanel2MouseDragged

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        //drag this pane

    }//GEN-LAST:event_jPanel2MousePressed

    private void txtmkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmkhActionPerformed

    private void txtdiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiachiActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        pos = this.jTable.getSelectedRow();
        View();
    }//GEN-LAST:event_jTableMouseClicked

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        pos = this.jTable.getSelectedRow();
        View();
    }//GEN-LAST:event_jTableKeyReleased

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        View();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        OnOff(false, true);
        check = -1;
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int[] selectedRows = jTable.getSelectedRows();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int selectedRow = selectedRows[i];

            if (selectedRow < list.size()) {
                list.remove(selectedRow);
            }

            if (selectedRow < model.getRowCount()) {
                model.removeRow(selectedRow);
            }
        }       
        capNhatSoThuTu();
        
        if (!list.isEmpty()) {
            if (pos >= list.size()) {
                pos = list.size() - 1; // Đặt lại pos nếu nó vượt quá giới hạn của list
            }
            View();
        } else {
            // Nếu list trống rỗng, cập nhật giao diện với thông tin trống
            OnOff(true, false);
            this.txtmkh.setText("");
            this.txthovaten.setText("");
            this.cbbgioitinh.setSelectedItem("");
            this.txtdiachi.setText("");
            this.txtsdt.setText("");
        }
        luuFilekhachhang.saveData(list);
        thongke();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.txtmkh.setText("");
        this.txthovaten.setText("");
        this.cbbgioitinh.setSelectedItem("");
        this.txtdiachi.setText("");
        this.txtsdt.setText("");
        OnOff(false, true);
        check = 1;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed

        if (this.txtmkh.getText().isEmpty() || this.txthovaten.getText().isEmpty() || this.cbbgioitinh.getSelectedItem() == null || this.txtdiachi.getText().isEmpty() || this.txtsdt.getText().isEmpty()) {
            View();
            return;
        }

        String makhachhang = this.txtmkh.getText();
        String hovaten = this.txthovaten.getText();
        String gioitinh = (String) this.cbbgioitinh.getSelectedItem();
        String diachi = this.txtdiachi.getText();
        String sodienthoai = this.txtsdt.getText();

        // Kiểm tra tên khách hàng có phải là chữ cái hoặc dấu cách hay không
        boolean checkTen = true;
        for (int i = 0; i < hovaten.length(); i++) {
            if (!Character.isLetter(hovaten.charAt(i)) && hovaten.charAt(i) != ' ') {
                checkTen = false;
                break;
            }
        }

        if (!checkTen) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng chỉ được chứa các ký tự chữ cái và dấu cách.", "Lỗi", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        if (!sodienthoai.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số chứ không được chứa chữ cái hoặc ký tự đặc biệt.", "Lỗi", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        if (check == 1) {
            list.add(new KhachHang(makhachhang, hovaten, gioitinh, diachi, sodienthoai));
        }
        if (check == -1) {
            list.set(pos, new KhachHang(makhachhang, hovaten, gioitinh, diachi, sodienthoai));
        }
        luuFilekhachhang.saveData(list);
        View();
        ViewTable();
        thongke();

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnkhachhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhachhangMouseEntered
        btnkhachhang.setBackground(new Color(86, 65, 118));
    }//GEN-LAST:event_btnkhachhangMouseEntered

    private void btnkhachhangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhachhangMouseExited
        btnkhachhang.setBackground(new Color(54, 33, 89));
    }//GEN-LAST:event_btnkhachhangMouseExited

    private void btnkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnkhachhangMouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnkhachhangMouseClicked

    private void btndonhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndonhangMouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btndonhangMouseClicked

    private void btndonhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndonhangMouseEntered
        btndonhang.setBackground(new Color(86, 65, 118));
    }//GEN-LAST:event_btndonhangMouseEntered

    private void btndonhangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndonhangMouseExited
        btndonhang.setBackground(new Color(54, 33, 89));
    }//GEN-LAST:event_btndonhangMouseExited

    private void btntrangchuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntrangchuMouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btntrangchuMouseClicked

    private void btntrangchuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntrangchuMouseEntered
        btntrangchu.setBackground(new Color(86, 65, 118));
    }//GEN-LAST:event_btntrangchuMouseEntered

    private void btntrangchuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntrangchuMouseExited
        btntrangchu.setBackground(new Color(54, 33, 89));
    }//GEN-LAST:event_btntrangchuMouseExited

    private void btntimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimkiemMouseClicked
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btntimkiemMouseClicked

    private void btntimkiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimkiemMouseEntered
        btntimkiem.setBackground(new Color(86, 65, 118));
    }//GEN-LAST:event_btntimkiemMouseEntered

    private void btntimkiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntimkiemMouseExited
        btntimkiem.setBackground(new Color(54, 33, 89));
    }//GEN-LAST:event_btntimkiemMouseExited

    private void btnhotroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhotroMouseClicked
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnhotroMouseClicked

    private void btnhotroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhotroMouseEntered
        btnhotro.setBackground(new Color(86, 65, 118));
    }//GEN-LAST:event_btnhotroMouseEntered

    private void btnhotroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhotroMouseExited
        btnhotro.setBackground(new Color(54, 33, 89));
    }//GEN-LAST:event_btnhotroMouseExited

    private void btnHuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy1ActionPerformed
        View1();
    }//GEN-LAST:event_btnHuy1ActionPerformed

    private void btnLuu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuu1ActionPerformed
        if (this.txtmadonhang.getText().isEmpty() || this.txttensanpham.getText().isEmpty() || this.cbbsoluong.getSelectedItem() == null || this.txtgiasanpham.getText().isEmpty()) {
            View1();
            return;
        }

        String masanpham = this.txtmadonhang.getText();
        String tensanpham = this.txttensanpham.getText();
        String soluongString = (String) this.cbbsoluong.getSelectedItem();
        int soluong = Integer.parseInt(soluongString);
        String giasanphamString = this.txtgiasanpham.getText();
        int giasanpham = Integer.parseInt(giasanphamString);

        // Kiểm tra giá sản phẩm
        if (giasanpham < 0) {
            JOptionPane.showMessageDialog(this, "Giá sản phẩm phải lớn hơn 0.", "Lỗi", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        if (check1 == 1) {
            list1.add(new DonHang(masanpham, tensanpham, giasanpham, soluong, soluong * giasanpham));
        }
        if (check1 == -1) {
            list1.set(pos1, new DonHang(masanpham, tensanpham, giasanpham, soluong, soluong * giasanpham));
        }
        luuFiledonhang.saveData(list1);
        View1();
        ViewTable1();
        thongke();
    }//GEN-LAST:event_btnLuu1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        this.txtmadonhang.setText("");
        this.txttensanpham.setText("");
        this.cbbsoluong.setSelectedItem("");
        this.txtgiasanpham.setText("");

        OnOff1(false, true);
        check1 = 1;
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        OnOff1(false, true);
        check1 = -1;
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed

        int[] selectedRows = jTable1.getSelectedRows();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int selectedRow = selectedRows[i];

            if (selectedRow < list1.size()) {
                list1.remove(selectedRow);
            }

            if (selectedRow < model1.getRowCount()) {
                model1.removeRow(selectedRow);
            }
        }

        capNhatSoThuTu1();
        luuFiledonhang.saveData(list1);
        if (!list1.isEmpty()) {
            if (pos1 >= list1.size()) {
                pos1 = list1.size() - 1; // Đặt lại pos1 nếu nó vượt quá giới hạn của list1
            }
            View1();
        } else {
            // Nếu list1 trống rỗng, cập nhật giao diện với thông tin trống
            OnOff1(true, false);
            this.txtmadonhang.setText("");
            this.txttensanpham.setText("");
            this.cbbsoluong.setSelectedItem("");
            this.txtgiasanpham.setText("");
        }
        thongke();

    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void txtgiasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiasanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiasanphamActionPerformed

    private void txtmadonhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmadonhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmadonhangActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        pos1 = this.jTable1.getSelectedRow();
        View1();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        pos1 = this.jTable1.getSelectedRow();
        View1();
    }//GEN-LAST:event_jTable1KeyReleased

    private void btntrangchuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntrangchuMousePressed

    }//GEN-LAST:event_btntrangchuMousePressed

    private void btntrangchuMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntrangchuMouseDragged

    }//GEN-LAST:event_btntrangchuMouseDragged

    private void nhapmadonhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapmadonhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nhapmadonhangActionPerformed

    private void btntimkiemmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemmActionPerformed
        String maSanPham = nhapmadonhang.getText();
        DonHang donHangTimThay = null;

        for (DonHang donHang : list1) {
            if (donHang.getMasanpham().equals(maSanPham)) {
                donHangTimThay = donHang;
                break;
            }
        }

        // Sử dụng donHangTimThay sau khi kết thúc vòng lặp
        if (donHangTimThay != null) {
            this.lbmadonhang.setText("Mã Đơn Hàng : " + donHangTimThay.getMasanpham());
            this.lbtensanpham.setText("Tên Sản Phẩm : " + donHangTimThay.getTensanpham());
            this.lbsoluong.setText(String.valueOf("Số lượng : " + donHangTimThay.getSoluong()));
            this.lbgiasanpham.setText(String.valueOf("Giá sản phẩm : " + donHangTimThay.getGiasanpham() + " VND"));
            this.lbtongtien.setText(String.valueOf("Tổng tiền : " + donHangTimThay.getSoluong() * donHangTimThay.getGiasanpham() + " VND"));
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã đã nhập.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btntimkiemmActionPerformed

    private void btntimkiemm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemm1ActionPerformed
        String maKhachhang = nhapmakhachhang.getText();
        KhachHang khachHangTimThay = null;

        for (KhachHang khachHang : list) {
            if (khachHang.getMakhachhang().equals(maKhachhang)) {
                khachHangTimThay = khachHang;
                break;
            }
        }

        // Sử dụng donHangTimThay sau khi kết thúc vòng lặp
        if (khachHangTimThay != null) {
            this.lbmakhachhang.setText("Mã Khách Hàng : " + khachHangTimThay.getMakhachhang());
            this.lbtenkhachhang.setText("Tên Khách Hàng : " + khachHangTimThay.getHovaten());
            this.lbgioitinh.setText(String.valueOf("Giới Tính : " + khachHangTimThay.getGioitinh()));
            this.lbdiachi.setText(String.valueOf("Địa Chỉ : " + khachHangTimThay.getDiachi()));
            this.lbsodienthoai.setText(String.valueOf("Số Điện Thoại : " + khachHangTimThay.getSdt()));
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với mã đã nhập.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btntimkiemm1ActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        nhapmadonhang.setText("");

        // Đặt giá trị ban đầu cho các label
        lbmakhachhang.setText("Mã Khách Hàng :");
        lbtenkhachhang.setText("Tên Khách Hàng :");
        lbgioitinh.setText("Giới Tính :");
        lbdiachi.setText("Địa Chỉ :");
        lbsodienthoai.setText("Số Điện Thoại :");
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnreset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreset1ActionPerformed
        nhapmakhachhang.setText("");

        // Đặt giá trị ban đầu cho các label
        lbmadonhang.setText("Mã Đơn Hàng :");
        lbtensanpham.setText("Tên Sản Phẩm :");
        lbsoluong.setText("Số lượng :");
        lbgiasanpham.setText("Giá sản phẩm :");
        lbtongtien.setText("Tổng tiền :");
    }//GEN-LAST:event_btnreset1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HeThongMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HeThongMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HeThongMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HeThongMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HeThongMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnHuy1;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnLuu1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JPanel btndonhang;
    private javax.swing.JPanel btnhotro;
    private javax.swing.JPanel btnkhachhang;
    private com.k33ptoo.components.KButton btnreset;
    private com.k33ptoo.components.KButton btnreset1;
    private javax.swing.JPanel btntimkiem;
    private com.k33ptoo.components.KButton btntimkiemm;
    private com.k33ptoo.components.KButton btntimkiemm1;
    private javax.swing.JPanel btntrangchu;
    private javax.swing.JComboBox<String> cbbgioitinh;
    private javax.swing.JComboBox<String> cbbsoluong;
    private javax.swing.JLabel doanhthu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jlabelhi;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel lbdiachi;
    private javax.swing.JLabel lbgiasanpham;
    private javax.swing.JLabel lbgioitinh;
    private javax.swing.JLabel lbmadonhang;
    private javax.swing.JLabel lbmakhachhang;
    private javax.swing.JLabel lbsodienthoai;
    private javax.swing.JLabel lbsoluong;
    private javax.swing.JLabel lbtenkhachhang;
    private javax.swing.JLabel lbtensanpham;
    private javax.swing.JLabel lbtongtien;
    private javax.swing.JTextField nhapmadonhang;
    private javax.swing.JTextField nhapmakhachhang;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel pdonhang;
    private javax.swing.JPanel photro;
    private javax.swing.JPanel pkhachhang;
    private javax.swing.JPanel ptimkiem;
    private javax.swing.JPanel ptimkiem1;
    private javax.swing.JPanel ptrangchu;
    private javax.swing.JPanel side_pane;
    private javax.swing.JLabel sodonhang;
    private javax.swing.JLabel sokhachhang;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtgiasanpham;
    private javax.swing.JTextField txthovaten;
    private javax.swing.JTextField txtmadonhang;
    private javax.swing.JTextField txtmkh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttensanpham;
    // End of variables declaration//GEN-END:variables
}
