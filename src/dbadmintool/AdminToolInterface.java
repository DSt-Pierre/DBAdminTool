/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbadmintool;

import javax.swing.DefaultListModel;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Dave
 */
public class AdminToolInterface extends javax.swing.JFrame {

    private SQLInterface connection = new mySQLConnector();

    /**
     * Creates new form AdminToolInterface
     */
    public AdminToolInterface() {
        initComponents();
        devInitComponents();
    }

    /**
     * Developer defined initialization
     */
    private void devInitComponents() {
        clearDatabaseList();
        clearTableList();
        clearTableData();
        listDatabases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Login.main(connection, this);
    }

    /**
     * refresh function
     */
    public void refresh() {
        clearDatabaseList();
        clearTableList();
        if (connection.IsConnected()) {
            loadDatabases();
        }
    }

    /**
     * clear database list
     */
    private void clearDatabaseList() {
        DefaultListModel model = new DefaultListModel();
        listDatabases.setModel(model);
        model.removeAllElements();
    }

    /**
     * clear table list
     */
    private void clearTableList() {
        DefaultListModel model = new DefaultListModel();
        listTables.setModel(model);
        model.removeAllElements();
    }

    private void clearTableData() {
        DefaultTableModel model = new DefaultTableModel();
        tableData.setModel(model);
        model.setRowCount(0);
        model.setColumnCount(0);
    }

    /**
     * load database names
     */
    private void loadDatabases() {
        List<String> Databases = connection.getDatabases();
        DefaultListModel model = new DefaultListModel();
        listDatabases.setModel(model);
        for (String itr : Databases) {
            model.addElement(itr);
        }
        if (Databases.size() == 1){
            listDatabases.setSelectedIndex(0);
        }
    }

    /**
     *
     * @param Database Database name
     */
    private void loadTables(String Database) {
        clearTableData();
        List<String> Tables = connection.getTables(Database);
        DefaultListModel model = new DefaultListModel();
        listTables.setModel(model);
        for (String itr : Tables) {
            model.addElement(itr);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDatabases = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listTables = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Databases");

        listDatabases.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listDatabases.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDatabasesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listDatabases);

        listTables.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listTables.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listTablesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listTables);

        jLabel2.setText("Tables");

        tableData.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableData);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Action: Close Application
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void listDatabasesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listDatabasesValueChanged
        loadTables(listDatabases.getSelectedValue().toString());
    }//GEN-LAST:event_listDatabasesValueChanged

    private void listTablesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listTablesValueChanged
       
        clearTableData();
        if (listTables.getSelectedIndex()<0){
            return;
        }
        
        DefaultTableModel model;
        try{
            model = buildTableModel(connection.getTableData(listTables.getSelectedValue().toString(), listDatabases.getSelectedValue().toString()));
        }catch(SQLException ex){
            String[] arr = {"resultset error : " + ex.getMessage()};
                Message.main(arr);
                return;
        }
        tableData.setModel(model);

    }//GEN-LAST:event_listTablesValueChanged
    /** 
     * 
     * @param rs resultset to convert
     * @return table model
     * @throws SQLException 
     */
    private DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
         // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }
        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

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
            java.util.logging.Logger.getLogger(AdminToolInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminToolInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminToolInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminToolInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminToolInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listDatabases;
    private javax.swing.JList listTables;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}
