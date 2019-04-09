/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu.scheduling_cs302;

import java.awt.Toolkit;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author EJ
 */
public class Main extends javax.swing.JFrame {
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        populateTable();
    }
    
    public void populateTable(){
        List<Jobs> jobs = new ArrayList();
        jobs.add(new Jobs(1, 20, 2));
        jobs.add(new Jobs(2, 25, 1));
        jobs.add(new Jobs(3, 12, 0));
        jobs.add(new Jobs(4, 30, 0));
        jobs.add(new Jobs(5, 5, 0));
        jobs.add(new Jobs(6, 10, 3));
        jobs.add(new Jobs(7, 6, 10));
        jobs.add(new Jobs(8, 30, 25));
        jobs.add(new Jobs(9, 50, 21));
        jobs.add(new Jobs(10, 20, 6)); 
        int currentTime = 10;
//        for (int i = 0; i < jobs.size(); i++) {
//            System.out.println(jobs.get(i).getIndexOfProcess() + "\t" + jobs.get(i).getCPUBurstTime() + "\t" + jobs.get(i).getArrivalTime());
//        }
        
        TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        TableColumn col = table.getColumnModel().getColumn(0);
        TableColumn col2 = table.getColumnModel().getColumn(1);
        TableColumn col3 = table.getColumnModel().getColumn(2);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        col.setCellRenderer(dtcr);
        col2.setCellRenderer(dtcr);
        col3.setCellRenderer(dtcr);
        
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        for(int i = 0; i < jobs.size(); i++){
            model.addRow(new Object[]{
                jobs.get(i).getIndexOfProcess(),
                jobs.get(i).getCPUBurstTime(),
                jobs.get(i).getArrivalTime(),
            });
        }  
    }
    
    private int getTypeOfAlgorithm(){
        String type = cb_typeOfAlgorithm.getSelectedItem().toString();
        
        switch (type) {
            case "FCFS":
                return 1;
            case "RR":
                return 2;
            case "SJF":
                return 3;
            default:
                break;
        }
        return 0;
    }
    
    private void simulateGanttChart(){
        switch (getTypeOfAlgorithm()) {
            case 1:
                FCFS fcfs = new FCFS();
                fcfs.displayProcess(panel_ganttChart, lbl_WT, lbl_TT, lbl_job, lbl_time, table);
                break;
            case 3:
                SJFNE sjfne = new SJFNE();
                sjfne.displayProcess(panel_ganttChart,lbl_WT, lbl_TT, lbl_job, lbl_time, table);
                break;
            case 2:
                RR rr = new RR();
                rr.displayProcess(panel_ganttChart, lbl_WT, lbl_TT, 7,lbl_job, lbl_time, table);
                break;
            default:
                break;
        }
    }
    
    public void stopSimulation() {
        switch (getTypeOfAlgorithm()) {
            case 1:
                FCFS fcfs = new FCFS();
                fcfs.stopSimulation();
                break;
            case 3:
                SJFNE sjfne = new SJFNE();
                sjfne.stopSimulation();
                break;
            case 2:
                RR rr = new RR();
                rr.stopSimulation();
                break;
            default:
                break;
        }
    }
       
    public void resume() {
        switch (getTypeOfAlgorithm()) {
            case 1:
                FCFS fcfs = new FCFS();
                fcfs.resumeSimulation();
                break;
            case 3:
                SJFNE sjfne = new SJFNE();
                sjfne.resumeSimulation();
                break;
            case 2:
                RR rr = new RR();
                rr.resumeSimulation();
                break;
            default:
                break;
        }
    }
    
    public void pause() {
        switch (getTypeOfAlgorithm()) {
            case 1:
                FCFS fcfs = new FCFS();
                fcfs.pauseSimulation();
                break;
            case 3:
                SJFNE sjfne = new SJFNE();
                sjfne.pauseSimulation();
                break;
            case 2:
                RR rr = new RR();
                rr.pauseSimulation();
                break;
            default:
                break;
        }
    }
    
    public void finish() {
        switch (getTypeOfAlgorithm()) {
            case 1:
                FCFS fcfs = new FCFS();
                fcfs.finishSimulation();
                break;
            case 3:
                SJFNE sjfne = new SJFNE();
                sjfne.finishSimulation();
                break;
            case 2:
                RR rr = new RR();
                rr.finishSimulation();
                break;
            default:
                break;
        }
    }
    
    public void clear(){      
        lbl_TT.setText("0");
        lbl_WT.setText("0");
        lbl_job.setText("0");
        lbl_time.setText("0");
        cb_typeOfAlgorithm.setSelectedIndex(0);
        panel_ganttChart.removeAll();
        panel_ganttChart.repaint();
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        model.setRowCount(0);
        populateTable();
    }
    
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cpu.png")));
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btn_simulate = new javax.swing.JButton();
        cb_typeOfAlgorithm = new javax.swing.JComboBox<>();
        btn_stop = new javax.swing.JButton();
        btn_restart = new javax.swing.JButton();
        btn_resume = new javax.swing.JButton();
        btn_finish = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_job = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        panel_ganttChart = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_WT = new javax.swing.JLabel();
        lbl_TT = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(533, 219));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(51, 51, 51));

        table.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Burst time", "Arrival time", "Turnaround", "Waiting", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.setAutoscrolls(false);
        table.setPreferredSize(new java.awt.Dimension(226, 160));
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 513, 193));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(204, 11, 533, 219);

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(180, 219));
        jPanel2.setMinimumSize(new java.awt.Dimension(180, 219));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_simulate.setBackground(new java.awt.Color(102, 102, 102));
        btn_simulate.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        btn_simulate.setForeground(new java.awt.Color(255, 255, 255));
        btn_simulate.setText("SIMULATE");
        btn_simulate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simulateActionPerformed(evt);
            }
        });
        jPanel2.add(btn_simulate, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 64, 156, 40));

        cb_typeOfAlgorithm.setBackground(new java.awt.Color(0, 153, 255));
        cb_typeOfAlgorithm.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        cb_typeOfAlgorithm.setForeground(new java.awt.Color(255, 255, 255));
        cb_typeOfAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Algorithm", "FCFS", "SJF", "RR" }));
        cb_typeOfAlgorithm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(cb_typeOfAlgorithm, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 156, 40));

        btn_stop.setBackground(new java.awt.Color(102, 102, 102));
        btn_stop.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        btn_stop.setForeground(new java.awt.Color(255, 255, 255));
        btn_stop.setIcon(new javax.swing.ImageIcon("E:\\EJ\\Java\\Netbeans Project\\CPU Scheduling_CS302\\src\\image\\pause-icon.png")); // NOI18N
        btn_stop.setContentAreaFilled(false);
        btn_stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });
        jPanel2.add(btn_stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 110, 48, 50));

        btn_restart.setBackground(new java.awt.Color(102, 102, 102));
        btn_restart.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        btn_restart.setForeground(new java.awt.Color(255, 255, 255));
        btn_restart.setText("RESTART");
        btn_restart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restartActionPerformed(evt);
            }
        });
        jPanel2.add(btn_restart, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 166, 156, 40));

        btn_resume.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        btn_resume.setForeground(new java.awt.Color(255, 255, 255));
        btn_resume.setIcon(new javax.swing.ImageIcon("E:\\EJ\\Java\\Netbeans Project\\CPU Scheduling_CS302\\src\\image\\play-icon.png")); // NOI18N
        btn_resume.setContentAreaFilled(false);
        btn_resume.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_resume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resumeActionPerformed(evt);
            }
        });
        jPanel2.add(btn_resume, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 110, 48, 50));

        btn_finish.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 14)); // NOI18N
        btn_finish.setForeground(new java.awt.Color(255, 255, 255));
        btn_finish.setIcon(new javax.swing.ImageIcon("E:\\EJ\\Java\\Netbeans Project\\CPU Scheduling_CS302\\src\\image\\fast-forward-icon.png")); // NOI18N
        btn_finish.setContentAreaFilled(false);
        btn_finish.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finishActionPerformed(evt);
            }
        });
        jPanel2.add(btn_finish, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 48, 50));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 11, 180, 219);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("CPU");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 113, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Current Job");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 98, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Current Time");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 98, -1, -1));

        lbl_job.setFont(new java.awt.Font("DS-Digital", 1, 30)); // NOI18N
        lbl_job.setForeground(new java.awt.Color(255, 0, 0));
        lbl_job.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_job.setText("0");
        jPanel4.add(lbl_job, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 129, 106, -1));

        lbl_time.setFont(new java.awt.Font("DS-Digital", 1, 30)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(255, 0, 0));
        lbl_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_time.setText("0");
        jPanel4.add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 129, 129, -1));

        getContentPane().add(jPanel4);
        jPanel4.setBounds(750, 10, 271, 219);

        panel_ganttChart.setBackground(new java.awt.Color(51, 51, 51));
        panel_ganttChart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel_ganttChart.setMinimumSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout panel_ganttChartLayout = new javax.swing.GroupLayout(panel_ganttChart);
        panel_ganttChart.setLayout(panel_ganttChartLayout);
        panel_ganttChartLayout.setHorizontalGroup(
            panel_ganttChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1263, Short.MAX_VALUE)
        );
        panel_ganttChartLayout.setVerticalGroup(
            panel_ganttChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panel_ganttChart);
        panel_ganttChart.setBounds(10, 240, 1267, 432);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Average");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 227, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Waiting ");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 98, -1, -1));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("BatmanForeverAlternate", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Turnaround ");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 98, -1, -1));

        lbl_WT.setFont(new java.awt.Font("DS-Digital", 1, 30)); // NOI18N
        lbl_WT.setForeground(new java.awt.Color(255, 0, 0));
        lbl_WT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_WT.setText("0");
        jPanel3.add(lbl_WT, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 129, 74, -1));

        lbl_TT.setFont(new java.awt.Font("DS-Digital", 1, 30)); // NOI18N
        lbl_TT.setForeground(new java.awt.Color(255, 0, 0));
        lbl_TT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TT.setText("0");
        jPanel3.add(lbl_TT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 129, 74, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(1038, 11, 239, 219);

        jLabel4.setIcon(new javax.swing.ImageIcon("E:\\EJ\\Java\\Netbeans Project\\CPU Scheduling_CS302\\src\\image\\background4.jpg")); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 1300, 680);

        setSize(new java.awt.Dimension(1302, 717));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simulateActionPerformed
        // TODO add your handling code here:
        simulateGanttChart();
    }//GEN-LAST:event_btn_simulateActionPerformed

    private void btn_restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restartActionPerformed
        // TODO add your handling code here:
        stopSimulation();
        clear();
    }//GEN-LAST:event_btn_restartActionPerformed

    private void btn_resumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resumeActionPerformed
        // TODO add your handling code here:
        resume();
    }//GEN-LAST:event_btn_resumeActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
        pause();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finishActionPerformed
        // TODO add your handling code here:
        finish();
    }//GEN-LAST:event_btn_finishActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_finish;
    private javax.swing.JButton btn_restart;
    private javax.swing.JButton btn_resume;
    private javax.swing.JButton btn_simulate;
    private javax.swing.JButton btn_stop;
    private javax.swing.JComboBox<String> cb_typeOfAlgorithm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_TT;
    private javax.swing.JLabel lbl_WT;
    private javax.swing.JLabel lbl_job;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JPanel panel_ganttChart;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
