/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu.scheduling_cs302;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author EJ
 */
public class SJFNE {
    
    private JLabel process;
    static Thread thread;
    static int speed = 400;
    
    public void displayProcess(JPanel panel, JLabel WT, JLabel TT, JLabel job, JLabel time, JTable table){
        thread = new Thread(){
            public void run(){
                try{
                    DecimalFormat df2 = new DecimalFormat(".##");
                    List<Jobs> jobs = new ArrayList();
                    jobs.clear();
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

                    int count = 1;
                    double averageTT = 0;
                    double averageWT = 0;
                    List<Jobs> rows;
                    while (!jobs.isEmpty()) {
                        rows = new ArrayList();    
                        for (int i = 0; i < jobs.size(); i++) {
                            int at = jobs.get(i).getArrivalTime();
                            int bt = jobs.get(i).getCPUBurstTime();
                            int index = jobs.get(i).getIndexOfProcess();
                            if (at <= (count - 1)) {
                                rows.add(new Jobs(index, bt, at));
                            }
                        }
                        rows.sort(Comparator.comparing(Jobs::getCPUBurstTime));
                        int indexOfProcess = rows.get(0).getIndexOfProcess();
                        int BT = rows.get(0).getCPUBurstTime();
                        int AT = rows.get(0).getArrivalTime();
                        job.setText("JOB " + indexOfProcess);
                        for (int i = 0; i < BT; i++) {
                            updateStatus(table, indexOfProcess, i, BT);
                            time.setText(count + "->" + ++count);
                            simulateProcess(indexOfProcess, (count-1), panel);
                            Thread.sleep(speed);
                        }
                        int TAT = Math.abs((count - 1) - AT);
                        int WTT = Math.abs(TAT - BT);
                        updateTable(table, TAT, WTT, indexOfProcess);
                        averageTT += (float) TAT / 10;
                        TT.setText(df2.format(averageTT));
                        averageWT += (float) WTT / 10;
                        WT.setText(df2.format(averageWT));
                        Toolkit.getDefaultToolkit().beep();
                        jobs.remove(getIndex(jobs, indexOfProcess));
                    }
                }catch(Exception e){e.printStackTrace();}
            }
        };
        thread.start();   
    }
    
    private void updateTable(JTable table, int TT, int WT, int index) {
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        model.setValueAt(TT, index - 1, 3);
        model.setValueAt(WT, index - 1, 4);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumn col4 = table.getColumnModel().getColumn(3);
        col4.setCellRenderer(dtcr);
        TableColumn col5 = table.getColumnModel().getColumn(4);
        col5.setCellRenderer(dtcr);
    }
    
    private int getIndex(List<Jobs> al, int index) {
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).getIndexOfProcess() == index) {
                return i;
            }
        }
        return 0;
    }
    
    private void updateStatus(JTable table, int index, int count, int BT) {
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumn col6 = table.getColumnModel().getColumn(5);
        col6.setCellRenderer(dtcr);
        
        DecimalFormat df2 = new DecimalFormat(".##");
        count += 1;
        double status = (float) count / BT * 100;
        if (status < 100) {
            model.setValueAt(df2.format(status) + "%", index - 1, 5);
        }
        else {
            model.setValueAt("Completed", index - 1, 5);
        }
    }
    
    public void stopSimulation() {
        thread.stop();
        this.speed = 400;
    }
    
    public void pauseSimulation() {
        thread.suspend();
    }
    
    public void resumeSimulation() {
        this.speed = 400;
        thread.resume();
    }
    
    public void finishSimulation() {
        this.speed = 1;
    }
    
    private void simulateProcess(int indexOfProcess, int count, JPanel panel){
        /////////////////////////////////////////
        //***********DISPLAY PROCESS***********//
        ////////////////////////////////////////
        MigLayout mig = new MigLayout("wrap 43");
        panel.setLayout(mig);
        process = new JLabel("<html>" +indexOfProcess+ "<br><br><br><br>" +count+ "</html>", SwingConstants.CENTER);
        process.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        process.setOpaque(true);
        if(indexOfProcess == 1){process.setBackground(Color.getHSBColor(10, 180, 255));}
        if(indexOfProcess == 2){process.setBackground(Color.getHSBColor(1, 250, 100));}
        if(indexOfProcess == 3){process.setBackground(Color.cyan);}
        if(indexOfProcess == 4){process.setBackground(Color.orange);}
        if(indexOfProcess == 5){process.setBackground(Color.green);}
        if(indexOfProcess == 6){process.setBackground(Color.yellow);}
        if(indexOfProcess == 7){process.setBackground(Color.magenta);}
        if(indexOfProcess == 8){process.setBackground(Color.pink);}
        if(indexOfProcess == 9){process.setBackground(Color.red);}
        if(indexOfProcess == 10){process.setBackground(Color.getHSBColor(66, 158, 244));}                             
        process.setPreferredSize(new Dimension(25, 80));
        panel.add(process);
        panel.revalidate();
        panel.repaint();
        
        /////////////////////////////////////////
        //***********DISPLAY PROCESS***********//
        ////////////////////////////////////////
    }
}
