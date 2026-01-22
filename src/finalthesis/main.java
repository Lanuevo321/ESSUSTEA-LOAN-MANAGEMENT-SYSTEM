/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package finalthesis;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jerry
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    
    PreparedStatement pst;
    ResultSet rs;
    Connection con;
    Statement st;
    
    public main() {
        initComponents();
        
        String url = "jdbc:mysql://localhost:3306/server";
        String user = "root";
        String pass = "6107";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        }
        
        ddd();
        ddd1();
        dt();
        //allData();
    }
    
     public void ddd() {
        dAte.setText(addSubtractDate(1));
    }

    public static String addSubtractDate(int n) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, n);
        String result = dateFormat.format(cal.getTime());
        return result;
    }
    
    public void ddd1() {
        date2.setText(addSubtractDatee(1));
    }
    
    public static String addSubtractDatee(int nn) {
        DateFormat dateFormatt = new SimpleDateFormat("MM yyyy");
        Calendar call = Calendar.getInstance();
        call.add(Calendar.MONTH, nn);
        String resultt = dateFormatt.format(call.getTime());
        return resultt;
    }

    public void dt() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
        String dd = sdf.format(d);

        date1.setText(dd);
    }

    /*
        THE CODE BELOW IS FOR "SELECT FROM DATABASE"
    */
    
    

        public void lender_accc(){
        String uunn = id.getText();
        String b = "select * from d_pay where id_pay=?";

        try{
            pst = con.prepareCall(b);
            pst.setString(1,uunn);
            ResultSet rs = pst.executeQuery();
       
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(2),rs.getString(6),rs.getString(4),rs.getString(5)});
            }
            

        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        }
        
    }
        
        
        public void contact1(){     
        String uunn = id.getText();
        String b = "select * from d_add where idadd=?";

        try{
            pst = con.prepareCall(b);
            pst.setString(1,uunn);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
           jTextField17.setText(rs.getString(2));     
           jTextField19.setText("Contact : "+rs.getString(3)); // Contact
           currentBal.setText(rs.getString(14)); // balance
           jTextField2.setText(rs.getString(4)); //Loan Amount 
           monAmo.setText(rs.getString(9)); //  monthly Amo
           jTextField3.setText(rs.getString(11)); //month duration
           monthLeft.setText(rs.getString(15));
           //id.setText(rs.getString(1));
           }
           
        }catch(Exception e){
            System.out.println("Error :" + e.getMessage());
        }
    }
        
        public void contact2(){     
        String uunn = id.getText();
        String b = "SELECT * FROM table_balance where id_balance=?";
        try{
            pst = con.prepareCall(b);
            pst.setString(1,uunn);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                currentBal.setText(rs.getString(2)); // balance
                monthLeft.setText(rs.getString(3));
           }
           
           }catch(Exception e){
               System.out.println("Error :" + e.getMessage());
            }
    }
        
        

        public void allData(){
        String l = "SELECT * FROM d_add";

        try {
            PreparedStatement pst = con.prepareStatement(l);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(13), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(9)});
            }
        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        } 
        }
        
        
        public  void bayad(){
         try{
                Connection con;
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/essusserver","root","essustealoanmanagementsystem01");
                Statement st = con.createStatement();
                boolean b=st.execute("update table_balance set current_Bal='"+currentBal.getText()+"', montH_lEft='"+monthLeft.getText()+"' where id_balance='"+id.getText()+"'");
                
                if(!b){
                    formWindowOpened(null);
                }
        }catch(Exception e){
            System.out.println("Error :" + e.getMessage());
        }
    }
        
        public void sortData(){
            String SD = "SELECT * FROM d_add ORDER BY dDate DESC";
            try {
            PreparedStatement pst = con.prepareStatement(SD);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(13), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(9)});
            }
        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        }
        }
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenrated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JLayeredPane();
        login = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        side = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        container1 = new javax.swing.JLayeredPane();
        add = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        jtxtLoanWord = new javax.swing.JTextField();
        jtxtServiceFee = new javax.swing.JTextField();
        jtxtNetAmount = new javax.swing.JTextField();
        jtxtInterestperMonth = new javax.swing.JTextField();
        jtxtMonthlyAmoo = new javax.swing.JTextField();
        jtxtMonthlyAmoW = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jtxtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtContact = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        A_LoanAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        A_MonthDuration = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JTextField();
        pay = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        record = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        view_record = new javax.swing.JPanel();
        jTextField17 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        currentBal = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        monthLeft = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        monAmo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        Print_Format = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel16 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        LABEL = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        JlABLE = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        Pdate = new javax.swing.JTextField();
        Pname = new javax.swing.JTextField();
        Pcontact = new javax.swing.JTextField();
        Ploanableword = new javax.swing.JTextField();
        loanableN = new javax.swing.JTextField();
        PloanableAmount = new javax.swing.JTextField();
        Pservicefee = new javax.swing.JTextField();
        Pnetamount = new javax.swing.JTextField();
        Pinterestpermonth = new javax.swing.JTextField();
        Pmoamortization = new javax.swing.JTextField();
        Pduration = new javax.swing.JTextField();
        Pstartofdeduction = new javax.swing.JTextField();
        PPdate = new javax.swing.JTextField();
        PPname = new javax.swing.JTextField();
        PPloanableword = new javax.swing.JTextField();
        PPloanableamount = new javax.swing.JTextField();
        PPmoamortizationword = new javax.swing.JTextField();
        PPmoamortization = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        PPstartofdeduction = new javax.swing.JTextField();
        dasdasdasdasdds = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        dAte = new javax.swing.JLabel();
        date2 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        lName = new javax.swing.JTextField();
        llContact = new javax.swing.JTextField();
        lllLoanAmount = new javax.swing.JTextField();
        lVserViceFee = new javax.swing.JTextField();
        VnetAmount = new javax.swing.JTextField();
        VlinterestPerMonth = new javax.swing.JTextField();
        VllMonthlyAmo = new javax.swing.JTextField();
        VlllMonthDu = new javax.swing.JTextField();
        VllllStartOfDeduc = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jtxtMonthlyAmo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CAPSTONE PROJECT [BATCH 2024] :   ESSUSTEA Loan Management System @ESSU Salcedo Campus    ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        body.setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(0, 102, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel4.setText("USERNAME :");

        jLabel10.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel10.setText("PASSWORD :");

        jButton5.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jButton5.setText("LOGIN");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField4)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(102, 102, 0));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/asya_eni.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        body.add(login, "card3");

        side.setBackground(new java.awt.Color(0, 153, 0));

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ADD LOAN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PAY LOAN");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("RECORDS");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("LOGOUT");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 0));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/design/asya_eni1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(39, 39, 39))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12))
        );

        javax.swing.GroupLayout sideLayout = new javax.swing.GroupLayout(side);
        side.setLayout(sideLayout);
        sideLayout.setHorizontalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sideLayout.setVerticalGroup(
            sideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        container1.setLayout(new java.awt.CardLayout());

        add.setBackground(new java.awt.Color(240, 253, 244));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(0, 0, 0));
        jTextField6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setText("jTextField6");
        jTextField6.setBorder(null);
        jTextField6.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField6.setSelectionColor(new java.awt.Color(0, 0, 0));

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(0, 0, 0));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setText("jTextField7");
        jTextField7.setBorder(null);
        jTextField7.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField7.setSelectionColor(new java.awt.Color(0, 0, 0));

        jTextField88.setEditable(false);
        jTextField88.setBackground(new java.awt.Color(0, 0, 0));
        jTextField88.setForeground(new java.awt.Color(255, 255, 255));
        jTextField88.setText("jTextField8");
        jTextField88.setBorder(null);
        jTextField88.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField88.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtLoanWord.setEditable(false);
        jtxtLoanWord.setBackground(new java.awt.Color(0, 0, 0));
        jtxtLoanWord.setForeground(new java.awt.Color(255, 255, 255));
        jtxtLoanWord.setText("jTextField9");
        jtxtLoanWord.setBorder(null);
        jtxtLoanWord.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtLoanWord.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtServiceFee.setEditable(false);
        jtxtServiceFee.setBackground(new java.awt.Color(0, 0, 0));
        jtxtServiceFee.setForeground(new java.awt.Color(255, 255, 255));
        jtxtServiceFee.setText("jTextField10");
        jtxtServiceFee.setBorder(null);
        jtxtServiceFee.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtServiceFee.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtNetAmount.setEditable(false);
        jtxtNetAmount.setBackground(new java.awt.Color(0, 0, 0));
        jtxtNetAmount.setForeground(new java.awt.Color(255, 255, 255));
        jtxtNetAmount.setText("jTextField11");
        jtxtNetAmount.setBorder(null);
        jtxtNetAmount.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtNetAmount.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtInterestperMonth.setEditable(false);
        jtxtInterestperMonth.setBackground(new java.awt.Color(0, 0, 0));
        jtxtInterestperMonth.setForeground(new java.awt.Color(255, 255, 255));
        jtxtInterestperMonth.setText("jTextField12");
        jtxtInterestperMonth.setBorder(null);
        jtxtInterestperMonth.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtInterestperMonth.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtMonthlyAmoo.setEditable(false);
        jtxtMonthlyAmoo.setBackground(new java.awt.Color(0, 0, 0));
        jtxtMonthlyAmoo.setForeground(new java.awt.Color(255, 255, 255));
        jtxtMonthlyAmoo.setText("jTextField13");
        jtxtMonthlyAmoo.setBorder(null);
        jtxtMonthlyAmoo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtMonthlyAmoo.setSelectionColor(new java.awt.Color(0, 0, 0));

        jtxtMonthlyAmoW.setEditable(false);
        jtxtMonthlyAmoW.setBackground(new java.awt.Color(0, 0, 0));
        jtxtMonthlyAmoW.setForeground(new java.awt.Color(255, 255, 255));
        jtxtMonthlyAmoW.setText("jTextField14");
        jtxtMonthlyAmoW.setBorder(null);
        jtxtMonthlyAmoW.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jtxtMonthlyAmoW.setSelectionColor(new java.awt.Color(0, 0, 0));

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(0, 0, 0));
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setText("jTextField15");
        jTextField15.setBorder(null);
        jTextField15.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField15.setSelectionColor(new java.awt.Color(0, 0, 0));

        jTextField16.setEditable(false);
        jTextField16.setBackground(new java.awt.Color(0, 0, 0));
        jTextField16.setForeground(new java.awt.Color(255, 255, 255));
        jTextField16.setText("jTextField16");
        jTextField16.setBorder(null);
        jTextField16.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextField16.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CONTACT :");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("APPLICANT NAME :");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("LOAN AMOUNT :");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SERVICE FEE :");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("NET AMOUNT :");

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("INTEREST PER MONTH:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("MONTHLY AMORTIZATION :");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("MONTH DURATION :");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("START OF DEDUCTION :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtMonthlyAmoW)
                    .addComponent(jtxtLoanWord, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField16)
                    .addComponent(jtxtMonthlyAmoo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtInterestperMonth, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtNetAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jtxtServiceFee, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField88, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtLoanWord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtServiceFee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtNetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtInterestperMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtMonthlyAmoo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtMonthlyAmoW, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(240, 253, 244));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setText("APPLICANT NAME :");

        jtxtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtNameKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("CONTACT NUMBER :");

        jtxtContact.setDocument(new JavaApplication12(11));
        jtxtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtContactKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setText("LOAN AMOUNT :");

        A_LoanAmount.setDocument(new JavaApplication12(6));
        A_LoanAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                A_LoanAmountKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                A_LoanAmountKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setText("MONTH DURATION :");

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setText("CLEAR");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEAR_FUNCTION(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setText("ADD");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 0));
        jButton3.setText("PRINT");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        A_MonthDuration.setEditable(false);
        A_MonthDuration.setBackground(new java.awt.Color(240, 253, 244));
        A_MonthDuration.setForeground(new java.awt.Color(240, 253, 244));
        A_MonthDuration.setText("jTextField5");
        A_MonthDuration.setBorder(null);
        A_MonthDuration.setCaretColor(new java.awt.Color(255, 255, 102));
        A_MonthDuration.setDisabledTextColor(new java.awt.Color(255, 255, 102));
        A_MonthDuration.setSelectedTextColor(new java.awt.Color(255, 255, 102));
        A_MonthDuration.setSelectionColor(new java.awt.Color(255, 255, 102));

        jComboBox2.setDocument(new JavaApplication12(2));
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtName)
                            .addComponent(jtxtContact)
                            .addComponent(A_LoanAmount)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addGap(166, 166, 166))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(142, 142, 142))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(141, 141, 141))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(134, 134, 134))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(A_MonthDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(A_LoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(A_MonthDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(addLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        container1.add(add, "card2");

        pay.setBackground(new java.awt.Color(240, 253, 244));

        jTable1.setBackground(new java.awt.Color(240, 253, 244));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO#.", "DATE OF LOAN", "NAME OF APPLICANT", "LOAN AMOUNT", "MONTH AMORTIZATION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCellSelectionEnabled(true);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setGridColor(new java.awt.Color(102, 102, 255));
        jTable1.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(80);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setText("PAY LOAN ");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel27.setText("Displaying list of accounts");

        jTextField12.setText("jTextField12");
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout payLayout = new javax.swing.GroupLayout(pay);
        pay.setLayout(payLayout);
        payLayout.setHorizontalGroup(
            payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(payLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(payLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        payLayout.setVerticalGroup(
            payLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, payLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel13)
                .addGap(32, 32, 32)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        container1.add(pay, "card3");

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.#", "DATE OF LOAN", "NAME OF APPLICANT", "PHONE NUMBER", "LOAN AMOUNT", "MONTH AMORTIZATION"
            }
        ));
        jTable3.setGridColor(new java.awt.Color(102, 102, 255));
        jTable3.setSelectionBackground(new java.awt.Color(102, 102, 255));
        jTable3.setShowGrid(true);
        jScrollPane4.setViewportView(jTable3);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel17.setText("LOAN RECORDS");

        jLabel87.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel87.setText("Displaying list of Info's");

        javax.swing.GroupLayout recordLayout = new javax.swing.GroupLayout(record);
        record.setLayout(recordLayout);
        recordLayout.setHorizontalGroup(
            recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
                    .addGroup(recordLayout.createSequentialGroup()
                        .addGroup(recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        recordLayout.setVerticalGroup(
            recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        container1.add(record, "card4");

        view_record.setBackground(new java.awt.Color(255, 255, 255));

        jTextField17.setEditable(false);
        jTextField17.setBackground(new java.awt.Color(255, 255, 255));
        jTextField17.setFont(new java.awt.Font("Bodoni MT", 3, 36)); // NOI18N
        jTextField17.setText("APPLICANTS NAME");
        jTextField17.setBorder(null);
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField17KeyReleased(evt);
            }
        });

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(255, 255, 255));
        jTextField19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField19.setText("Contact");
        jTextField19.setBorder(null);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE OF PAYMENT", "AMOUNT", "CURRENT BAL.", "MONTH LEFT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jButton6.setText("PAY ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        currentBal.setEditable(false);
        currentBal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        currentBal.setText("jTextField2");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField2.setBorder(null);

        monthLeft.setEditable(false);
        monthLeft.setBackground(new java.awt.Color(255, 255, 255));
        monthLeft.setForeground(new java.awt.Color(255, 255, 255));
        monthLeft.setText("jTextField12");
        monthLeft.setBorder(null);
        monthLeft.setCaretColor(new java.awt.Color(255, 255, 255));
        monthLeft.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        monthLeft.setSelectionColor(new java.awt.Color(255, 255, 255));

        id.setEditable(false);
        id.setBackground(new java.awt.Color(255, 255, 255));
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setText("jTextField12");
        id.setBorder(null);
        id.setCaretColor(new java.awt.Color(255, 255, 255));
        id.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        id.setSelectionColor(new java.awt.Color(255, 255, 255));
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });

        monAmo.setEditable(false);
        monAmo.setBackground(new java.awt.Color(255, 255, 255));
        monAmo.setForeground(new java.awt.Color(255, 255, 255));
        monAmo.setText("jTextField12");
        monAmo.setBorder(null);
        monAmo.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        monAmo.setSelectionColor(new java.awt.Color(255, 255, 255));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("jLabel28");

        jLabel29.setText("ENTER YOUR PAYEMENT :");

        jLabel30.setText("TOTAL BALANCED :");

        jLabel86.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel86.setText("Loan Amount :");

        javax.swing.GroupLayout view_recordLayout = new javax.swing.GroupLayout(view_record);
        view_record.setLayout(view_recordLayout);
        view_recordLayout.setHorizontalGroup(
            view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_recordLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(view_recordLayout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, view_recordLayout.createSequentialGroup()
                                .addComponent(monAmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, view_recordLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(106, 106, 106)))
                        .addComponent(monthLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(view_recordLayout.createSequentialGroup()
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(view_recordLayout.createSequentialGroup()
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(322, 322, 322)
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(251, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, view_recordLayout.createSequentialGroup()
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, view_recordLayout.createSequentialGroup()
                                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentBal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42))))
        );
        view_recordLayout.setVerticalGroup(
            view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(view_recordLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(view_recordLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(14, 14, 14)
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monthLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monAmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(view_recordLayout.createSequentialGroup()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86))
                        .addGap(31, 31, 31)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addGroup(view_recordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currentBal)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        container1.add(view_record, "card5");

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("BELOW THIS LINE TO BE FILLED UP BY THE PROJECT IN-CHARGE");
        jPanel16.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 790, 58));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("APPLICATION FOR CHARACTER SALARY LOAN");
        jPanel16.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 580, 32));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setText("DATE :");
        jPanel16.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 164, 60, 30));

        LABEL.setText("________________________________________________");
        jPanel16.add(LABEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, 20));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setText("CONTACT NUMBER :");
        jPanel16.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 190, -1));

        jLabel35.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel35.setText("Fill in the blanks properly and legibly.");
        jPanel16.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, -1, 20));

        jLabel37.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel37.setText("DIRECTIONS:");
        jPanel16.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 120, -1));

        JlABLE.setText("________________________________________________________________");
        jPanel16.add(JlABLE, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, 20));

        jLabel38.setText("________________________________________________________________");
        jPanel16.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, 20));

        jLabel40.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel40.setText("NAME OF APPLICANT :");
        jPanel16.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 210, -1));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel36.setText("I hereby apply for a loan with a gross amount of:");
        jPanel16.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 390, -1));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setText("______________________________________________________________________________");
        jPanel16.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 840, 40));

        jLabel41.setText("____________________________");
        jPanel16.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 290, 20));

        jLabel42.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel42.setText("( Php )");
        jPanel16.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 60, -1));

        jLabel44.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Project Incharge/Tresurer");
        jPanel16.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 1090, 210, 20));

        jLabel45.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("PROMISORY NOTE");
        jPanel16.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 1100, 412, 58));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel46.setText("______________________________________________________________________________________________");
        jPanel16.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1070, 1430, 50));

        jLabel47.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel47.setText("Service Fee :");
        jPanel16.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, -1, -1));

        jLabel48.setText("________________________________________");
        jPanel16.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 860, 310, 20));

        jLabel49.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel49.setText("Loanable Amount :");
        jPanel16.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 170, -1));

        jLabel50.setText("________________________________________");
        jPanel16.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 650, 300, 20));

        jLabel51.setText("________________________________________");
        jPanel16.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 690, 280, -1));

        jLabel52.setText("________________________________________");
        jPanel16.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 720, 290, -1));

        jLabel53.setText("________________________________________");
        jPanel16.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 760, 280, -1));

        jLabel54.setText("________________________________________");
        jPanel16.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 800, 280, -1));

        jLabel55.setText("________________________________________");
        jPanel16.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 830, 280, -1));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel56.setText("Start of Deduction :");
        jPanel16.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 860, -1, 20));

        jLabel57.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel57.setText("Net Amount :");
        jPanel16.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 720, -1, 20));

        jLabel58.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel58.setText("Interest per Month :");
        jPanel16.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 760, -1, 20));

        jLabel59.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel59.setText("Mo. Amortization :");
        jPanel16.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 790, -1, 30));

        jLabel60.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel60.setText("Duration :");
        jPanel16.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 830, -1, 20));

        jLabel61.setText("____________________________________");
        jPanel16.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 480, 260, 30));

        jLabel62.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Signature of Borrower");
        jPanel16.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 500, 230, -1));

        jLabel63.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("JOSEPH S. ABUNALES");
        jPanel16.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 1070, 230, -1));

        jLabel64.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Name of Borrower");
        jPanel16.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 1470, 190, 20));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel65.setText("_______________________________________________________________________________________");
        jPanel16.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 1420, 50));

        jLabel66.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("ESSUSTEA MONITARY SUPPORT");
        jPanel16.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 33, 620, 58));

        jLabel67.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel67.setText("DATE :");
        jPanel16.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 1160, 90, -1));

        jLabel69.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel69.setText("I,");
        jPanel16.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1200, 30, 40));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel71.setText("having receive the amount of");
        jPanel16.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 1210, 240, -1));

        jLabel72.setText("_________________");
        jPanel16.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 1280, 170, 20));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel73.setText("( Php)");
        jPanel16.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1260, 50, 40));

        jLabel74.setText("_________________");
        jPanel16.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1240, 130, -1));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel75.setText("In the event I failed to settle the monthly amortization, I waive my rights not to  enjoy any Government & Private loan until");
        jPanel16.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1350, 1200, -1));

        jLabel76.setText("____________________________");
        jPanel16.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 1210, 200, 20));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel77.setText("( Php)");
        jPanel16.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1230, 50, -1));

        jLabel78.setText("________________________________________________________________________________");
        jPanel16.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1210, 580, 30));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel79.setText("authorize the payroll starting the amount of");
        jPanel16.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 1230, 380, -1));

        jLabel80.setText("____________________________________________________________________________");
        jPanel16.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 1230, 640, 30));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel81.setText("from the payroll starting the month of");
        jPanel16.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 1270, 320, -1));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel82.setText("until the total sum will be paid.");
        jPanel16.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 1270, 300, 30));

        jLabel83.setText("____________________________________");
        jPanel16.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 980, 330, 30));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Signature of Borrower");
        jPanel16.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 1000, 210, 20));

        jLabel85.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Signature over Printed Name");
        jPanel16.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1450, 280, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText(" my monthly amortization amortization is fully setteld.");
        jPanel16.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1370, 510, -1));

        jLabel43.setText("________________________________________");
        jPanel16.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1430, 380, 20));

        Pdate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pdate.setBorder(null);
        Pdate.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel16.add(Pdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 290, 30));

        Pname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pname.setBorder(null);
        Pname.setCaretColor(new java.awt.Color(255, 255, 255));
        Pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PnameActionPerformed(evt);
            }
        });
        jPanel16.add(Pname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 270, -1));

        Pcontact.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pcontact.setBorder(null);
        Pcontact.setCaretColor(new java.awt.Color(255, 255, 255));
        Pcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PcontactActionPerformed(evt);
            }
        });
        jPanel16.add(Pcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 230, -1));

        Ploanableword.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Ploanableword.setBorder(null);
        Ploanableword.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel16.add(Ploanableword, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 800, -1));

        loanableN.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        loanableN.setBorder(null);
        loanableN.setCaretColor(new java.awt.Color(255, 255, 255));
        loanableN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanableNActionPerformed(evt);
            }
        });
        jPanel16.add(loanableN, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 180, 40));

        PloanableAmount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PloanableAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        PloanableAmount.setText("jTextField7");
        PloanableAmount.setBorder(null);
        jPanel16.add(PloanableAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 640, 270, -1));

        Pservicefee.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pservicefee.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pservicefee.setText("jTextField7");
        Pservicefee.setBorder(null);
        jPanel16.add(Pservicefee, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 680, 270, -1));

        Pnetamount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pnetamount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pnetamount.setText("jTextField7");
        Pnetamount.setBorder(null);
        jPanel16.add(Pnetamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 710, 270, 30));

        Pinterestpermonth.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pinterestpermonth.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pinterestpermonth.setText("jTextField7");
        Pinterestpermonth.setBorder(null);
        jPanel16.add(Pinterestpermonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 750, 270, -1));

        Pmoamortization.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pmoamortization.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pmoamortization.setText("jTextField7");
        Pmoamortization.setBorder(null);
        jPanel16.add(Pmoamortization, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 790, 270, -1));

        Pduration.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pduration.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pduration.setText("jTextField7");
        Pduration.setBorder(null);
        jPanel16.add(Pduration, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 820, 270, -1));

        Pstartofdeduction.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Pstartofdeduction.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pstartofdeduction.setText("jTextField7");
        Pstartofdeduction.setBorder(null);
        jPanel16.add(Pstartofdeduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 850, 270, -1));

        PPdate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PPdate.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PPdate.setText("jTextField7");
        PPdate.setBorder(null);
        jPanel16.add(PPdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 1150, 140, 40));

        PPname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PPname.setText("jTextField7");
        PPname.setBorder(null);
        PPname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPnameActionPerformed(evt);
            }
        });
        jPanel16.add(PPname, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 1200, 190, 30));

        PPloanableword.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PPloanableword.setText("jTextField7");
        PPloanableword.setBorder(null);
        jPanel16.add(PPloanableword, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 1210, 560, -1));

        PPloanableamount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PPloanableamount.setText("jTextField7");
        PPloanableamount.setBorder(null);
        jPanel16.add(PPloanableamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1230, 130, -1));

        PPmoamortizationword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PPmoamortizationword.setText("jTextField7");
        PPmoamortizationword.setBorder(null);
        jPanel16.add(PPmoamortizationword, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 1230, 520, -1));

        PPmoamortization.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PPmoamortization.setText("jTextField7");
        PPmoamortization.setBorder(null);
        PPmoamortization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPmoamortizationActionPerformed(evt);
            }
        });
        jPanel16.add(PPmoamortization, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1270, 130, -1));

        jLabel68.setText("_________________");
        jPanel16.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 1280, 160, 20));

        PPstartofdeduction.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        PPstartofdeduction.setText("jTextField7");
        PPstartofdeduction.setBorder(null);
        PPstartofdeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPstartofdeductionActionPerformed(evt);
            }
        });
        jPanel16.add(PPstartofdeduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 1270, 130, -1));

        dasdasdasdasdds.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dasdasdasdasdds.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dasdasdasdasdds.setText("jTextField7");
        dasdasdasdasdds.setToolTipText("");
        dasdasdasdasdds.setBorder(null);
        jPanel16.add(dasdasdasdasdds, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1420, 250, -1));

        jScrollPane2.setViewportView(jPanel16);

        javax.swing.GroupLayout Print_FormatLayout = new javax.swing.GroupLayout(Print_Format);
        Print_Format.setLayout(Print_FormatLayout);
        Print_FormatLayout.setHorizontalGroup(
            Print_FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
        );
        Print_FormatLayout.setVerticalGroup(
            Print_FormatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );

        container1.add(Print_Format, "card5");

        dAte.setText("jLabel13");

        date2.setText("jLabel13");

        date1.setText("jLabel13");

        lName.setText("jTextField2");

        llContact.setText("jTextField2");

        lllLoanAmount.setText("jTextField2");

        lVserViceFee.setText("jTextField2");

        VnetAmount.setText("jTextField2");

        VlinterestPerMonth.setText("jTextField2");

        VllMonthlyAmo.setText("jTextField2");

        VlllMonthDu.setText("jTextField2");

        VllllStartOfDeduc.setText("jTextField2");

        jTextField18.setText("jTextField2");

        jTextField20.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField5.setText("jTextField5");

        jTextField9.setText("jTextField9");

        jTextField10.setText("jTextField10");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jTextField11.setText("jTextField11");

        jTextField8.setText("jTextField13");

        jtxtMonthlyAmo.setText("jTextField14");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dAte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(llContact, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lllLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lVserViceFee, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VnetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VlinterestPerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VllMonthlyAmo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VlllMonthDu, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VllllStartOfDeduc, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 763, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtMonthlyAmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dAte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(llContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lllLoanAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lVserViceFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VnetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VlinterestPerMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VllMonthlyAmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VlllMonthDu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VllllStartOfDeduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtMonthlyAmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        container1.add(jPanel10, "card7");

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(side, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1076, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                    .addGap(350, 350, 350)
                    .addComponent(container1)))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(side, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(container1))
        );

        body.add(container, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(body)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(body)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here
        DefaultTableModel model1 = (DefaultTableModel)jTable1.getModel();
        while(model1.getRowCount() > 0){
            for(int i = 0; i < model1.getRowCount(); i++){
                model1.removeRow(i);
            }
        }
        
        
        String l = "SELECT * FROM d_add ORDER BY dDate DESC";
        try {
            PreparedStatement pst = con.prepareStatement(l);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1),rs.getString(13), rs.getString(2), rs.getString(4),rs.getString(9)});
            }
        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        }
        
        container1.removeAll();
        container1.add(pay);
        container1.repaint();
        container1.revalidate();
        
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        while(model.getRowCount() > 0){
            for(int i = 0; i < model.getRowCount(); i++){
                model.removeRow(i);
            }
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void CLEAR_FUNCTION(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEAR_FUNCTION
//          TODO add your handling code here:
//          This Function is to CLEAR all input in JTextField:
            jtxtName.setText("");jtxtContact.setText("");A_LoanAmount.setText("");jTextField6.setText("");
            jTextField7.setText("");jTextField88.setText("");jtxtLoanWord.setText("");jtxtServiceFee.setText("");
            jtxtNetAmount.setText("");jtxtInterestperMonth.setText("");jtxtMonthlyAmoo.setText("");
            jtxtMonthlyAmoW.setText("");jTextField15.setText("");jTextField16.setText("");jComboBox2.setText("");
            
            jLabel19.setText("");
            jLabel14.setText("");
            jLabel20.setText("");
            jLabel21.setText("");
            jLabel22.setText("");
            jLabel23.setText("");
            jLabel24.setText("");
            jLabel25.setText("");
            jLabel26.setText("");
            
            jtxtContact.setEnabled(false);
            A_LoanAmount.setEnabled(false);
            jComboBox2.setEnabled(false);
            
            
    }//GEN-LAST:event_CLEAR_FUNCTION

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void A_LoanAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_A_LoanAmountKeyTyped
        // TODO add your handling code here:
        char c  = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_A_LoanAmountKeyTyped

    private void PnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PnameActionPerformed

    private void PcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PcontactActionPerformed

    private void PPnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PPnameActionPerformed

    private void PPmoamortizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPmoamortizationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PPmoamortizationActionPerformed

    private void PPstartofdeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPstartofdeductionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PPstartofdeductionActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       if (Pname.getText().equals("") || Pcontact.getText().equals("") || loanableN.getText().equals("") || Ploanableword.getText().equals("") || Pservicefee.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Oops! Missing information");
        }else{
           printRecord(jPanel16);
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        container1.removeAll(); container1.add(view_record); container1.repaint(); container1.revalidate();
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String jTextField4 = model.getValueAt(index, 0).toString();
        String jTextField5 = model.getValueAt(index, 2).toString();
        String jTextField6 = model.getValueAt(index, 1).toString();
        String jTextField0 = model.getValueAt(index, 3).toString();
       // jTextField17.setText(jTextField5);
        id.setText(jTextField4);
        contact1();
        lender_accc();
        contact2();
       // sortData();
       //lender_acc();
       
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (jtxtName.getText().equals("") || jtxtContact.getText().equals("") || A_LoanAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Oops! no data please provide");
        } else {
            JOptionPane.showMessageDialog(this, "SUCCESS!!");
            String data1[] = {date1.getText(), jtxtName.getText(), A_LoanAmount.getText()};
            DefaultTableModel tblm1 = (DefaultTableModel) jTable1.getModel();
            tblm1.addRow(data1);
            
            String a = jTextField6.getText();   lName.setText(a); 
            String b = jTextField7.getText();   llContact.setText(b);
            String c = jTextField8.getText();   lllLoanAmount.setText(c);
            String d = jtxtServiceFee.getText();lVserViceFee.setText(d);
            String e = jtxtNetAmount.getText(); VnetAmount.setText(e);
            String f = jtxtInterestperMonth.getText(); VlinterestPerMonth.setText(f);
            String g = jtxtMonthlyAmo.getText(); VllMonthlyAmo.setText(g);
            String h = jTextField15.getText(); VlllMonthDu.setText(h);
            String i = jTextField16.getText(); VllllStartOfDeduc.setText(i);
            
            /*
            DATABASE HERE :
            */
            
            /********* d_acc *****************/
            String dName = jTextField6.getText();
            String dContact = jTextField7.getText();
            String dLoanAmount = jTextField8.getText();
            String dLoanAmountWord = jtxtLoanWord.getText();
            String dServiceFee = jtxtServiceFee.getText();
            String dNetAmount = jtxtNetAmount.getText();
            String dInterestPerMonth = jtxtInterestperMonth.getText();
            String dMonthlyAmo = jtxtMonthlyAmo.getText();
            String dMonthlyWord = jtxtMonthlyAmoW.getText();
            String dMonthDu = jTextField15.getText();
            String dStartOfDeduction = jTextField16.getText();
            String dDate = date1.getText();
            String dCurrentBalance = jTextField8.getText();
            String dNumDu = A_MonthDuration.getText();
            /********* d_acc end here : *****************/
            
            
            /********* acc_payed *****************/
            String date_of_payment = date1.getText();
            String monthly_amo = jtxtMonthlyAmo.getText();
            String amo_unt = jtxtMonthlyAmo.getText();
            String month_left = A_MonthDuration.getText();
            String name_acc = jTextField6.getText();
            String loan_amounts = jTextField8.getText();
            String current_bAl = jTextField8.getText();
            /********* acc_payed end here: *****************/
            
            /********* table_balance********************/
            String current_Bal = jTextField8.getText();
            String montH_lEft = A_MonthDuration.getText();
            /********* table_balance end here: ********************/
            
            try{
                Connection con;
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/server", "root", "6107");
                
                String myDataBase = "INSERT INTO d_add(dName,dContact,dLoanAmount,dLoanAmountWord,dServiceFee,dNetAmount,dInterestPerMonth,dMonthlyAmo,dMonthlyWord,dMonthDu,dStartOfDeduction,dDate,dCurrentBalance,dNumDu)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                String myDataBase1 = "INSERT INTO acc_payed(date_of_payment,monthly_amo,amo_unt,month_left,name_acc,loan_amounts,current_bAl)values(?,?,?,?,?,?,?)";
                String myDataBase2 = "INSERT INTO table_balance(current_Bal,montH_lEft)values(?,?)";
                
                PreparedStatement pst = con.prepareStatement(myDataBase);
                PreparedStatement pst1 = con.prepareStatement(myDataBase1);
                PreparedStatement pst2 = con.prepareStatement(myDataBase2);
                
                pst.setString(1, dName);
                pst.setString(2, dContact);
                pst.setString(3, dLoanAmount);
                pst.setString(4, dLoanAmountWord);
                pst.setString(5, dServiceFee);
                pst.setString(6, dNetAmount);
                pst.setString(7, dInterestPerMonth);
                pst.setString(8, dMonthlyAmo);
                pst.setString(9, dMonthlyWord);
                pst.setString(10,dMonthDu);
                pst.setString(11,dStartOfDeduction);
                pst.setString(12,dDate);
                pst.setString(13, dCurrentBalance);
                pst.setString(14, dNumDu);
                
                pst1.setString(1, date_of_payment);
                pst1.setString(2, monthly_amo);
                pst1.setString(3, amo_unt);
                pst1.setString(4, month_left);
                pst1.setString(5, name_acc);
                pst1.setString(6, loan_amounts);
                pst1.setString(7, current_bAl);
                
                pst2.setString(1, current_Bal);
                pst2.setString(2, montH_lEft);
                
                pst.executeUpdate();
                pst1.executeUpdate();
                pst2.executeUpdate();
                con.close();
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            /*
                START HERE:
            */
            
            String a1 = date1.getText();
            Pdate.setText(a1);
            
            String a2 = jTextField6.getText();
            Pname.setText(a2);
            
            String a3 = jTextField7.getText();
            Pcontact.setText(a3);
            
            String a4 = jTextField88.getText();
            loanableN.setText(a4);
            
            String a5 = jtxtLoanWord.getText();
            Ploanableword.setText(a5);
            
            String a6 = jTextField88.getText();
            PloanableAmount.setText(a6);
            
            String a7 = jtxtServiceFee.getText();
            Pservicefee.setText(a7);
            
            String a8 = jtxtNetAmount.getText();
            Pnetamount.setText(a8);
            
            String a9 = jtxtInterestperMonth.getText();
            Pinterestpermonth.setText(a9);
            
            String a10 = jtxtMonthlyAmoo.getText();
            Pmoamortization.setText(a10);
            
            String a11 = jTextField15.getText();
            Pduration.setText(a11);
            
            String a12 = jTextField16.getText();
            Pstartofdeduction.setText(a12);
            
            String a13 = jTextField6.getText();
            PPname.setText(a13);
            
            String a14 = jtxtLoanWord.getText();
            PPloanableword.setText(a14);
            
            String a15 = jTextField88.getText(); 
            PPloanableamount.setText(a15);
            
            String a16 = jtxtMonthlyAmoW.getText();
            PPmoamortizationword.setText(a16);
            
            String a17 = jtxtMonthlyAmoo.getText();
            PPmoamortization.setText(a17);
            
            String a18 = jTextField16.getText();
            PPstartofdeduction.setText(a18);
            
            String a19 = jTextField6.getText(); 
            dasdasdasdasdds.setText(a19);
            
            String a20 = date1.getText();
            PPdate.setText(a20);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        container1.removeAll();
        container1.add(add);
        container1.repaint();
        container1.revalidate();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        container1.removeAll();
        container1.add(record);
        container1.repaint();
        container1.revalidate();
        
        DefaultTableModel model1 = (DefaultTableModel)jTable3.getModel();
        while(model1.getRowCount() > 0){
            for(int i = 0; i < model1.getRowCount(); i++){
                model1.removeRow(i);
            }
        }
        
        
        String l = "SELECT * FROM d_add";
        try {
            PreparedStatement pst = con.prepareStatement(l);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString(1), rs.getString(13), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(9)});
            }
        } catch (Exception ex) {
            System.out.println("Error :" + ex.getMessage());
        }
        
        
        
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       // TODO add your handling code here:
        String userName = jTextField4.getText();
        String passWord = jPasswordField1.getText();
        if(userName.equals("")&&passWord.equals("")){
           JOptionPane.showMessageDialog(this, "Please enter Username & Password","Message",JOptionPane.ERROR_MESSAGE);
        }else if(!(userName.equals(""))&&passWord.equals("")){
           JOptionPane.showMessageDialog(this,"Please enter Password","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(userName.equals("")&&!passWord.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter Username","ERROR",JOptionPane.ERROR_MESSAGE);
        }else if(jTextField4.getText().contains("Essustea")&&jPasswordField1.getText().contains("1960")){
          body.removeAll();body.add(container);body.repaint();body.revalidate();
        jtxtName.setText("");jtxtContact.setText("");A_LoanAmount.setText("");jTextField6.setText("");
        jTextField7.setText("");jTextField88.setText("");jtxtLoanWord.setText("");jtxtServiceFee.setText("");
        jtxtNetAmount.setText("");jtxtInterestperMonth.setText("");jtxtMonthlyAmoo.setText("");
        jtxtMonthlyAmoW.setText("");jTextField15.setText("");jTextField16.setText("");
        jLabel19.setText("");jLabel14.setText("");jLabel20.setText("");jLabel21.setText("");jLabel22.setText("");
        jLabel23.setText("");jLabel24.setText("");jLabel25.setText("");jLabel26.setText("");
        
        jtxtContact.setEnabled(false);
        A_LoanAmount.setEnabled(false);
        jComboBox2.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(this,"Wrong Username & Password","Message",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this,"Do you want to Logout?","Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION){
            body.removeAll();body.add(login);body.repaint();body.revalidate();
        }
       
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jtxtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNameKeyPressed
         // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String name = jtxtName.getText();
            jTextField6.setText(name);
            jtxtContact.requestFocus();
            jtxtContact.setEnabled(true);
           // jTextField5.setText("APPLICANTS NAME :");
           jLabel19.setText("APPLICANT NAME:");
        }
    }//GEN-LAST:event_jtxtNameKeyPressed

    private void jtxtContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtContactKeyTyped
        // TODO add your handling code here:
        char c  = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_jtxtContactKeyTyped

    private void jtxtContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtContactKeyPressed
        // TODO add your handling code here:
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String contact = jtxtContact.getText();
            jTextField7.setText(contact);
            A_LoanAmount.requestFocus();
            A_LoanAmount.setEnabled(true);
            jLabel14.setText("CONTACT:");
        }
    }//GEN-LAST:event_jtxtContactKeyPressed

    private void A_LoanAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_A_LoanAmountKeyPressed
        // TODO add your handling code here:
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String loanAmount = A_LoanAmount.getText();
            jTextField8.setText(loanAmount);
            jComboBox2.requestFocus();
            jComboBox2.setEnabled(true);            
            Double a88 = Double.parseDouble(loanAmount);
            jTextField88.setText(String.format("₱ "+"%,.0f",a88)+".00");
            
            int n1 = Integer.parseInt(A_LoanAmount.getText());
        do {
            if (n1 >= 1 && n1 <= 10000000) {
                jtxtLoanWord.setText(numberToWords(n1)+" Pesos only.");
            } else {
                //  jtxtLoanWord.setText("Ivalid");
            }
        } while (n1 <= 0 || n1 > 1000000);
        jLabel20.setText("LOAN AMOUNT:");
        
        jComboBox2.requestFocus();
        }
    }//GEN-LAST:event_A_LoanAmountKeyPressed

    private void jTextField17KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyReleased
        // TODO add your handling code here:
        //contact();
    }//GEN-LAST:event_jTextField17KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here        
        
        int bal1 = Integer.parseInt(currentBal.getText());
        int mLeft1 = Integer.parseInt(monthLeft.getText());
        
        if(bal1 == mLeft1){
            JOptionPane.showMessageDialog(this, "The Loan was Already fully payed");
            
        }else{
            
            int ball = Integer.parseInt(currentBal.getText());
            int payM1 = Integer.parseInt(jTextField1.getText());
            
            if(bal1 != payM1){
                int bal = Integer.parseInt(currentBal.getText());
                int payM = Integer.parseInt(jTextField1.getText());
                int res = bal - payM;
        
                currentBal.setText(res+"");
            
                int mLeft = Integer.parseInt(monthLeft.getText());
                int mIns = 1;
        
                int lres = mLeft - mIns;
                monthLeft.setText(lres+"");
            }else{
                monthLeft.setText("0");
                currentBal.setText("0");
            }
            
            /*
            DATABASE HERE :
            */
            String id_pay = id.getText(); 
            String DateOfLoan = date1.getText();
            String Payment = monAmo.getText();
           // String Payment = jTextField1.getText();
            String month_LEFT = monthLeft.getText();
            String name_acc = jTextField17.getText();
            String amounT = jTextField2.getText();
            String current_BAl = currentBal.getText();
            String total_Balance = currentBal.getText();
            
          //  String id_balance = id.getText();
            String current_Bal = currentBal.getText();
            String montH_lEft = monthLeft.getText();
            
            
            try{
                
                Connection con;
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/server", "root", "6107");
                
                String myDataBase = "INSERT INTO d_pay(id_pay,DateOfLoan,Payment,month_LEFT,name_acc,amounT,current_bAl,total_Balance)values(?,?,?,?,?,?,?,?)";
               // String myDataBase1 = "INSERT INTO table_balance(id_balance,current_Bal,montH_lEft)VALUES(?,?,?)";
                Statement st = con.createStatement(); 
                boolean b=st.execute("update table_balance set current_Bal='"+currentBal.getText()+"', montH_lEft='"+monthLeft.getText()+"' where id_balance='"+id.getText()+"'");
                
                
                PreparedStatement pst = con.prepareStatement(myDataBase);
              //  PreparedStatement pst1 = con.prepareStatement(myDataBase1);
                
                pst.setString(1, id_pay); 
                pst.setString(2, DateOfLoan);
                pst.setString(3, Payment);
                pst.setString(4, month_LEFT);
                pst.setString(5, name_acc);
                pst.setString(6, amounT);
                pst.setString(7, current_BAl);
                pst.setString(8, total_Balance);
                
                
              //  pst1.setString(1, id_balance);
              //  pst1.setString(2, current_Bal);
              //  pst1.setString(3, montH_lEft);
                
                pst.executeUpdate();
              //  pst1.executeUpdate();
                con.close();
                
            if(!b){
                    formWindowOpened(null);
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (jTextField1.getText().equals("") ) {
            JOptionPane.showMessageDialog(this, "Oops! no data please provide");
            }else{
                JOptionPane.showMessageDialog(this, "Successfull");
                bayad();
                
                DefaultTableModel model1 = (DefaultTableModel)jTable2.getModel();
                while(model1.getRowCount() > 0){
                for(int i = 0; i < model1.getRowCount(); i++){
                model1.removeRow(i);
                 }
             }
        
        
            String uunn = id.getText();
            String b = "select * from d_pay where id_pay=?";

            try{
                pst = con.prepareCall(b);
                pst.setString(1,uunn);
                ResultSet rs = pst.executeQuery();
       
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                while (rs.next()) {
                model.addRow(new String[]{rs.getString(2),rs.getString(6),rs.getString(4),rs.getString(5)});
                }
            

            }catch (Exception ex) {
                System.out.println("Error :" + ex.getMessage());
            }
            jTextField1.setText("");    
          }  
         
       }      
    }//GEN-LAST:event_jButton6ActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        // TODO add your handling code here:
        //contact1();
        DefaultTableModel ob = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        jTable2.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(id.getText()));
    }//GEN-LAST:event_idKeyReleased

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void loanableNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanableNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loanableNActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == 40){
            jPasswordField1.requestFocus();
        }
        
        else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
        String userName = jTextField4.getText();
        String passWord = jPasswordField1.getText();
        
        if(userName.equals("")&&passWord.equals("")){
           JOptionPane.showMessageDialog(this, "Please enter Username & Password","Message",JOptionPane.ERROR_MESSAGE);
        }else if(!(userName.equals(""))&&passWord.equals("")){
           JOptionPane.showMessageDialog(this,"Please enter Password");
        }else if(userName.equals("")&&!passWord.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter Username");
        }else if(jTextField4.getText().contains("Essustea")&&jPasswordField1.getText().contains("1960")){
        body.removeAll();body.add(container);body.repaint();body.revalidate();
        jtxtName.setText("");jtxtContact.setText("");A_LoanAmount.setText("");jTextField6.setText("");
        jTextField7.setText("");jTextField88.setText("");jtxtLoanWord.setText("");jtxtServiceFee.setText("");
        jtxtNetAmount.setText("");jtxtInterestperMonth.setText("");jtxtMonthlyAmoo.setText("");
        jtxtMonthlyAmoW.setText("");jTextField15.setText("");jTextField16.setText("");
        jLabel19.setText("");jLabel14.setText("");jLabel20.setText("");jLabel21.setText("");jLabel22.setText("");
        jLabel23.setText("");jLabel24.setText("");jLabel25.setText("");jLabel26.setText("");
        }else{
            JOptionPane.showMessageDialog(this,"Wrong Username & Password","Message",JOptionPane.ERROR_MESSAGE);
         }
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            String userName = jTextField4.getText();
        String passWord = jPasswordField1.getText();
        if(userName.equals("")&&passWord.equals("")){
           JOptionPane.showMessageDialog(this, "Please enter Username & Password","Message",JOptionPane.ERROR_MESSAGE);
        }else if(!(userName.equals(""))&&passWord.equals("")){
           JOptionPane.showMessageDialog(this,"Please enter Password");
        }else if(userName.equals("")&&!passWord.equals("")){
            JOptionPane.showMessageDialog(this,"Please enter Username");
        }else if(jTextField4.getText().contains("Essustea")&&jPasswordField1.getText().contains("1960")){
        body.removeAll();body.add(container);body.repaint();body.revalidate();
        jtxtName.setText("");jtxtContact.setText("");A_LoanAmount.setText("");jTextField6.setText("");
        jTextField7.setText("");jTextField88.setText("");jtxtLoanWord.setText("");jtxtServiceFee.setText("");
        jtxtNetAmount.setText("");jtxtInterestperMonth.setText("");jtxtMonthlyAmoo.setText("");
        jtxtMonthlyAmoW.setText("");jTextField15.setText("");jTextField16.setText("");
        jLabel19.setText("");jLabel14.setText("");jLabel20.setText("");jLabel21.setText("");jLabel22.setText("");
        jLabel23.setText("");jLabel24.setText("");jLabel25.setText("");jLabel26.setText("");
        
        jtxtContact.setEnabled(false);
        A_LoanAmount.setEnabled(false);
        jComboBox2.setEnabled(false);
        
        }else{
            JOptionPane.showMessageDialog(this,"Wrong Username & Password","Message",JOptionPane.ERROR_MESSAGE);
        }
        }
        
        if(evt.getKeyCode()==38){
            jTextField4.requestFocus();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed
    
    
    
    
    
    private void jComboBox2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyPressed
        // TODO add your handling code here:
          
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        String h1 = jtxtName.getText();
        String h2 = jtxtContact.getText();
        String h3 = A_LoanAmount.getText();
        
        if(h1.equals("")&&h2.equals("")&&h3.equals("")){
            JOptionPane.showMessageDialog(this,"Please provide info","Error",JOptionPane.ERROR_MESSAGE);
        }else if(!(h1.equals("")) && h2.equals("") && h3.equals("")){
            JOptionPane.showMessageDialog(this,"Please provide CONTACT NUMBER & LOAN AMOUNT","Error",JOptionPane.ERROR_MESSAGE);
        }else if(h1.equals("") && !(h2.equals("")) && h3.equals("")){
            JOptionPane.showMessageDialog(this,"Please provide APPLICANT NAME & LOAN AMOUNT","Error",JOptionPane.ERROR_MESSAGE);
        }else if(h1.equals("") && h2.equals("") && !(h3.equals(""))){
            JOptionPane.showMessageDialog(this,"Please provide APPLICANT NAME & CONTACT NUMBER","Error",JOptionPane.ERROR_MESSAGE);
        }else if(!(h1.equals("")) && !(h2.equals("")) && h3.equals("")){
            JOptionPane.showMessageDialog(this,"Please provide LOAN AMOUNT","Error",JOptionPane.ERROR_MESSAGE);
        }else if(!(h1.equals("")) && h2.equals("") && !(h3.equals(""))){
            JOptionPane.showMessageDialog(this,"Please provide CONTACT NUMBER","Error",JOptionPane.ERROR_MESSAGE);
        }else if(h1.equals("") && !(h2.equals("")) && !(h3.equals(""))){
            JOptionPane.showMessageDialog(this,"Please provide APPLICANT NAME","Error",JOptionPane.ERROR_MESSAGE);
        }else{
        String advance_date = dAte.getText();
        jTextField16.setText(advance_date);
//      jTextField33.setText("START OF DEDUCTION :");
        
        String start = jComboBox2.getText();
        jTextField15.setText(start+" Months");
        A_MonthDuration.setText(start);
        
        
        int n1 = Integer.parseInt(A_LoanAmount.getText());
        double n2 = 0.02;
        int n3 = Integer.parseInt(A_MonthDuration.getText());
        int n4 = n3;

        double getServiceFee = n1 * n2; // num3 is service fee = 0.02 computation naman is loanable amount * 0.02
        double getNetAmount = n1 - getServiceFee; // pagkuha naman ht Net Amount is Loanable Amount Minus Services Fee is equal Net Amount
        double getMonthAmortization = getServiceFee * n4 + n1; // Pagkuha naman han monthly amount service fee * how many month + loanble amount / month
        double n5 = getMonthAmortization / n3;

        jtxtServiceFee.setText(getServiceFee+"");
        String p = jtxtServiceFee.getText();
        double w = Double.parseDouble(p);
        jtxtServiceFee.setText(String.format("₱ "+"%,.0f"+".00",w));
        
        jtxtNetAmount.setText(getNetAmount+"");
        String o = jtxtNetAmount.getText();
        double t = Double.parseDouble(o);
        jtxtNetAmount.setText(String.format("₱ "+"%,.0f"+".00",t));
        
        jtxtInterestperMonth.setText("2%");
     
        jtxtMonthlyAmo.setText(n5+"");
        String i = jtxtMonthlyAmo.getText();
        Double f = Double.parseDouble(i);
        jtxtMonthlyAmo.setText(String.format("%.0f",f));
        
        jtxtMonthlyAmoo.setText(String.format("₱ "+"%,.0f",f)+".00");
        
        int n6 = Integer.parseInt(jtxtMonthlyAmo.getText());
        do {
        if (n6 >= 1 && n6 <= 10000000.0) {
            jtxtMonthlyAmoW.setText(numberToWordss(n6)+" Pesos only.");
        } else {
            //  jtxtLoanWord.setText("Ivalid");
        }
        }while (n6 <= 0 || n6 > 1000000);
        
        jLabel21.setText("SERVICE FEE:");
        jLabel22.setText("NET AMOUNT:");
        jLabel23.setText("INTEREST PER MONTH:");
        jLabel24.setText("MONTHLY AMORTIZATION:");
        jLabel25.setText("MONTH DURATION:");
        jLabel26.setText("START OF DEDUCTION:");
        }
        }

    }//GEN-LAST:event_jComboBox2KeyPressed

    private void jComboBox2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyTyped
        // TODO add your handling code here:
        char c  = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_jComboBox2KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel)jTable1.getModel();
        TableRowSorter<DefaultTableModel>obj=new TableRowSorter<>(ob);
        jTable1.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(jTextField12.getText()));
    }//GEN-LAST:event_jTextField12KeyReleased

    private static final String[] ONES = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private static final String[] TENS = {null, null, "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};

    public static String numberToWords(int number) {
        if (number < 10) {
            return ONES[number];
        } else if (number < 20) {
            return TEENS[number % 10];
        } else if (number < 100) {
            return TENS[number / 10] + ((number % 10 >= 0) ? " " + numberToWords(number % 10) : " ");
        } else if (number < 1000) {
            return ONES[number / 100] + " Hundred" + ((number % 10 >= 0) ? " " + numberToWords(number % 100) : " ");
        }
        return numberToWords(number / 1000) + " Thousand" + ((number % 1000 > 0) ? " " + numberToWords(number % 1000) : " ");
    }

    private static final String[] ONESs = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    private static final String[] TEENSs = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    private static final String[] TENSs = {null, null, "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};

    public static String numberToWordss(int numberr) {
        if (numberr <= 10) {
            return ONESs[numberr];
        } else if (numberr < 20) {
            return TEENSs[numberr % 10];
        } else if (numberr < 100) {
            return TENSs[numberr / 10] + ((numberr % 10 >= 0) ? " " + numberToWordss(numberr % 10) : " ");
        } else if (numberr < 1000) {
            return ONESs[numberr / 100] + " Hundred" + ((numberr % 10 >= 0) ? " " + numberToWordss(numberr % 100) : " ");
        }
        return numberToWordss(numberr / 1000) + " Thousand" + ((numberr % 1000 > 0) ? " " + numberToWordss(numberr % 1000) : " ");
    }
    
    
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A_LoanAmount;
    private javax.swing.JTextField A_MonthDuration;
    private javax.swing.JLabel JlABLE;
    private javax.swing.JLabel LABEL;
    private javax.swing.JTextField PPdate;
    private javax.swing.JTextField PPloanableamount;
    private javax.swing.JTextField PPloanableword;
    private javax.swing.JTextField PPmoamortization;
    private javax.swing.JTextField PPmoamortizationword;
    private javax.swing.JTextField PPname;
    private javax.swing.JTextField PPstartofdeduction;
    private javax.swing.JTextField Pcontact;
    private javax.swing.JTextField Pdate;
    private javax.swing.JTextField Pduration;
    private javax.swing.JTextField Pinterestpermonth;
    private javax.swing.JTextField PloanableAmount;
    private javax.swing.JTextField Ploanableword;
    private javax.swing.JTextField Pmoamortization;
    private javax.swing.JTextField Pname;
    private javax.swing.JTextField Pnetamount;
    private javax.swing.JPanel Print_Format;
    private javax.swing.JTextField Pservicefee;
    private javax.swing.JTextField Pstartofdeduction;
    private javax.swing.JTextField VlinterestPerMonth;
    private javax.swing.JTextField VllMonthlyAmo;
    private javax.swing.JTextField VlllMonthDu;
    private javax.swing.JTextField VllllStartOfDeduc;
    private javax.swing.JTextField VnetAmount;
    private javax.swing.JPanel add;
    private javax.swing.JLayeredPane body;
    private javax.swing.JPanel container;
    private javax.swing.JLayeredPane container1;
    private javax.swing.JTextField currentBal;
    private javax.swing.JLabel dAte;
    private javax.swing.JTextField dasdasdasdasdds;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel date2;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JTextField jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jtxtContact;
    private javax.swing.JTextField jtxtInterestperMonth;
    private javax.swing.JTextField jtxtLoanWord;
    private javax.swing.JTextField jtxtMonthlyAmo;
    private javax.swing.JTextField jtxtMonthlyAmoW;
    private javax.swing.JTextField jtxtMonthlyAmoo;
    private javax.swing.JTextField jtxtName;
    private javax.swing.JTextField jtxtNetAmount;
    private javax.swing.JTextField jtxtServiceFee;
    private javax.swing.JTextField lName;
    private javax.swing.JTextField lVserViceFee;
    private javax.swing.JTextField llContact;
    private javax.swing.JTextField lllLoanAmount;
    private javax.swing.JTextField loanableN;
    private javax.swing.JPanel login;
    private javax.swing.JTextField monAmo;
    private javax.swing.JTextField monthLeft;
    private javax.swing.JPanel pay;
    private javax.swing.JPanel record;
    private javax.swing.JPanel side;
    private javax.swing.JPanel view_record;
    // End of variables declaration//GEN-END:variables
    private void printRecord(JPanel panel) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName("Print Record");
        printerJob.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D graphics2D = (Graphics2D) graphics;
                graphics2D.translate(pageFormat.getImageableX() * 2, pageFormat.getImageableY() * 2);
                graphics2D.scale(0.5, 0.5);

                jPanel16.paint(graphics2D);

                return Printable.PAGE_EXISTS;
            }
        });

        boolean returningResult = printerJob.printDialog();
        if (returningResult) {
            try {
                printerJob.print();
            } catch (PrinterException printerException) {
                JOptionPane.showMessageDialog(this, "Print Error:" + printerException.getMessage());
            }

        }
    }

}