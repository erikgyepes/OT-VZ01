
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MyFrame.java
 *
 * Created on Mar 21, 2010, 12:36:07 PM
 */

/**
 *
 * @author depi
 */
public class MyFrame extends javax.swing.JFrame {
    String driver = "com.mysql.jdbc.Driver";
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String username = "root";
    String password = "";
    String URL = "jdbc:mysql://localhost/firma";
    String table = "zamestnanci";
    String defaultSQL = "SELECT * FROM "+table+" ORDER BY id";


    DefaultTableModel tabModel;
    DefaultTableModel tabModel2;
    DefaultTableModel tabModel3;
    DefaultComboBoxModel ComboBoxModel1;
    DefaultComboBoxModel ComboBoxModel2;
    String stlpecFilter;

    /** Creates new form MyFrame */
    public MyFrame() {

        if (PripojenieKMySQL()) {
            initComponents();

            NaplnJTable(defaultSQL);
            NaplnComboBox1(jComboBox1);
        }

    }

    private boolean PripojenieKMySQL() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, username, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

    private boolean VratResultSet(String SQL) {
        try {
            rs = stmt.executeQuery(SQL);
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

     private boolean VykonajUpdatovaciDotaz(String SQL) {
        try {
            stmt.executeUpdate(SQL);
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private void VykonajDotaz(String SQL) {
        try {
            stmt = con.createStatement();
            stmt.executeQuery(SQL);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

     private void NaplnJTable(String SQL) {
        if (VratResultSet(SQL)) {
            try {
                ResultSetMetaData md = rs.getMetaData();
                int pocetStlpcov = md.getColumnCount();
                String[] hlavicky = new String[pocetStlpcov];

                for(int i=0; i<pocetStlpcov; i++) {
                    hlavicky[i] = md.getColumnName(i+1);
                }

                Object[][] data = null;
                tabModel = new DefaultTableModel(data, hlavicky);

                while (rs.next()) {
                    String[] record = new String[pocetStlpcov];
                    for(int i=0; i<pocetStlpcov; i++) {
                        record[i] = rs.getString(i+1);
                    }
                    tabModel.addRow(record);
                }
                jTable1.setModel(tabModel);


            } catch(Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("NaplnJTable: Chyba v SQL");
        }
    }

     private void NaplnJTableZamestnanci(int plat) {
        try {
            String[] hlavicky = {"Meno", "Priezvisko"};

            Object[][] data2 = null;
            tabModel2 = new DefaultTableModel(data2, hlavicky);

            String SQL = "SELECT meno, priezvisko FROM "+table+" WHERE plat >="+plat;
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String[] record = new String[2];
                for(int i=0; i<2; i++) {
                    record[i] = rs.getString(i+1);
                }
                tabModel2.addRow(record);
            }
            jTable2.setModel(tabModel2);

            Object[][] data3 = null;
            tabModel3 = new DefaultTableModel(data3, hlavicky);

            SQL = "SELECT meno, priezvisko FROM "+table+" WHERE plat <"+plat;
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String[] record = new String[2];
                for(int i=0; i<2; i++) {
                    record[i] = rs.getString(i+1);
                }
                tabModel3.addRow(record);
            }
            jTable3.setModel(tabModel3);

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void NaplnComboBox1(JComboBox myCombo) {
        ComboBoxModel1 = new DefaultComboBoxModel();

        try {
            ResultSetMetaData md = rs.getMetaData();
            int pocetStlpcov = md.getColumnCount();

            for(int i=0; i<pocetStlpcov; i++) {
                ComboBoxModel1.addElement(md.getColumnName(i+1));
            }
            
            myCombo.setModel(ComboBoxModel1);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void NaplnComboBox2(JComboBox myCombo, String stlpec) {
        ComboBoxModel2 = new DefaultComboBoxModel();

        try {
            String SQL = "SELECT DISTINCT "+stlpec+" FROM "+table;
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                ComboBoxModel2.addElement(rs.getString(1));
            }
            
            myCombo.setModel(ComboBoxModel2);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPridajZaznamButton = new javax.swing.JButton();
        jMeno = new javax.swing.JTextField();
        jPriezvisko = new javax.swing.JTextField();
        jPlat = new javax.swing.JTextField();
        jPocetDeti = new javax.swing.JTextField();
        jRokNarodenia = new javax.swing.JTextField();
        jPriemernyPlatButton = new javax.swing.JButton();
        jPriemernyPlatLabel = new javax.swing.JLabel();
        jVymazRiadok = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPlatFilter = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jZrusFilterButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPridajZaznamButton.setText("Pridaj záznam");
        jPridajZaznamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPridajZaznamButtonActionPerformed(evt);
            }
        });

        jMeno.setText("Meno");

        jPriezvisko.setText("Priezvisko");

        jPlat.setText("Plat");

        jPocetDeti.setText("Pocet deti");

        jRokNarodenia.setText("Rok narodenia");

        jPriemernyPlatButton.setText("Priemerný plat");
        jPriemernyPlatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPriemernyPlatButtonActionPerformed(evt);
            }
        });

        jPriemernyPlatLabel.setText("= priemerný plat =");

        jVymazRiadok.setText("Vymaž riadok");
        jVymazRiadok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVymazRiadokActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPlatFilter.setText("500");

        jButton1.setText("Vyfiltruj podľa platu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jZrusFilterButton.setText("Zruš filter");
        jZrusFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jZrusFilterButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jZrusFilterButton)
                .add(434, 434, 434))
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jMeno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 135, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jRokNarodenia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(12, 12, 12)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPriezvisko, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPridajZaznamButton)))
                            .add(layout.createSequentialGroup()
                                .add(jPlat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(2, 2, 2)
                                .add(jPocetDeti, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(251, 251, 251)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jVymazRiadok)
                            .add(layout.createSequentialGroup()
                                .add(21, 21, 21)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPriemernyPlatLabel)
                                    .add(jPriemernyPlatButton)))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPlatFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 57, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jZrusFilterButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jMeno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPriezvisko, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jPlat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jPocetDeti, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jRokNarodenia, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jPridajZaznamButton))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED))
                    .add(layout.createSequentialGroup()
                        .add(jVymazRiadok)
                        .add(18, 18, 18)
                        .add(jPriemernyPlatLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPriemernyPlatButton)
                        .add(15, 15, 15)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jPlatFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPridajZaznamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPridajZaznamButtonActionPerformed
        // TODO add your handling code here:
        String SQL = "INSERT INTO "+table+" (id, meno, priezvisko, plat, pocet_deti, rok_narodenia) VALUES ("
                +"'0',"
                +"'"+jMeno.getText()+"',"
                +"'"+jPriezvisko.getText()+"',"
                +"'"+jPlat.getText()+"',"
                +"'"+jPocetDeti.getText()+"',"
                +"'"+jRokNarodenia.getText()+"')";
        if (VykonajUpdatovaciDotaz(SQL)) {
            NaplnJTable(defaultSQL);
            jMeno.setText("Meno");
            jPriezvisko.setText("Priezvisko");
            jPlat.setText("Plat");
            jPocetDeti.setText("Pocet deti");
            jRokNarodenia.setText("Rok narodenia");
        }
    }//GEN-LAST:event_jPridajZaznamButtonActionPerformed

    private void jPriemernyPlatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPriemernyPlatButtonActionPerformed
        // TODO add your handling code here:
        String SQL = "SELECT AVG(plat) as priemernyplat FROM "+table;

        if (VratResultSet(SQL)) {
            try {
                rs.first();
                String priemernyPlat = rs.getString("priemernyplat");
                jPriemernyPlatLabel.setText(""+priemernyPlat+"€");
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jPriemernyPlatButtonActionPerformed

    private void jVymazRiadokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVymazRiadokActionPerformed
        // TODO add your handling code here:
        int vybrany_riadok = jTable1.getSelectedRow();
        int id =  Integer.parseInt(""+tabModel.getValueAt(vybrany_riadok, 0));

        if (vybrany_riadok != -1) {
            String SQL = "DELETE FROM "+table+" WHERE id='"+id+"'";
            if (VykonajUpdatovaciDotaz(SQL)) {
                NaplnJTable(defaultSQL);
            }
        }
    }//GEN-LAST:event_jVymazRiadokActionPerformed

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        NaplnComboBox2(jComboBox2, ComboBoxModel1.getSelectedItem().toString());
        stlpecFilter = ComboBoxModel1.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String SQL = "SELECT * FROM "+table+" WHERE "+stlpecFilter+" = '"+ComboBoxModel2.getSelectedItem().toString()+"'";
        NaplnJTable(SQL);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        NaplnJTableZamestnanci(Integer.parseInt(jPlatFilter.getText()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jZrusFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jZrusFilterButtonActionPerformed
        // TODO add your handling code here:
        NaplnJTable(defaultSQL);
    }//GEN-LAST:event_jZrusFilterButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JTextField jMeno;
    private javax.swing.JTextField jPlat;
    private javax.swing.JTextField jPlatFilter;
    private javax.swing.JTextField jPocetDeti;
    private javax.swing.JButton jPridajZaznamButton;
    private javax.swing.JButton jPriemernyPlatButton;
    private javax.swing.JLabel jPriemernyPlatLabel;
    private javax.swing.JTextField jPriezvisko;
    private javax.swing.JTextField jRokNarodenia;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jVymazRiadok;
    private javax.swing.JButton jZrusFilterButton;
    // End of variables declaration//GEN-END:variables

}
