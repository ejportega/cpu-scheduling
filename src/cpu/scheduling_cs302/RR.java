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
import java.util.List;
import java.util.ListIterator;
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
public class RR {
    
    private JLabel process;
    static Thread thread;
    static int speed = 400;
    
    public void displayProcess(JPanel panel, JLabel WT, JLabel TT, int quantumTime, JLabel job, JLabel time, JTable table){
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
                    // SORT JOBS by ARRIVAL TIME
                    jobs.sort((Jobs j1, Jobs j2)->j1.getArrivalTime()-j2.getArrivalTime()); 
                   int startTime = 0;
                   double averageTT = 0;
                   double averageWT = 0;
                   do{
                        ListIterator<Jobs> itr = jobs.listIterator();
                        int count;
                        int endProcess;
                        int temp= 0;
                        //int check = jobList.size();
                        int tempBT = 0;
                        int index = 0;
                        while(itr.hasNext()){
                            Jobs data = itr.next();
                            int indexOfProcess = data.getIndexOfProcess();
                            int CPUBurstTime = data.getCPUBurstTime();
                            int arrivalTime = data.getArrivalTime();
                            if(jobs.size() == 1){ endProcess = CPUBurstTime+1; System.out.println(jobs.size());}
                            else if(CPUBurstTime < quantumTime){ endProcess = CPUBurstTime;}
                            else{endProcess = quantumTime;}
                            
//                            if (index == 0) {endProcess += 1;}
                            job.setText("JOB " + indexOfProcess);
                            for(count = startTime;  temp < endProcess; count++){
                                updateStatus(table, indexOfProcess, count);
                                time.setText(count + "->" + (count + 1));
                                if(CPUBurstTime >= 1){
                                    CPUBurstTime--;
                                    simulateProcess(indexOfProcess, (count + 1), panel);
                                }
                                else{temp = endProcess;}
                                
                                Thread.sleep(speed);
                                temp++;
                            }
                            
                            itr.set(new Jobs(indexOfProcess,CPUBurstTime,arrivalTime));
                            if(CPUBurstTime == 0){
                                if (count > 208) {
                                    count -= 1;
                                }
                                int TAT = Math.abs(count - arrivalTime);
                                int WTT = Math.abs(TAT - getBT(indexOfProcess));
                                updateTable(table, TAT, WTT, indexOfProcess);
                                averageTT += (float) TAT / 10;
                                TT.setText(df2.format(averageTT));
                                averageWT += (float) WTT / 10;
                                WT.setText(df2.format(averageWT));
//                                System.out.println(indexOfProcess + "\t" + TAT + "\t" + getBT(indexOfProcess) + "\t" + averageWT);
                                itr.remove();
                                Toolkit.getDefaultToolkit().beep();
                            }
                            //jobList.add(new Jobs(indexOfProcess, arrivalTime, CPUBurstTime));
                            //itr.remove();
                            //q.add(new (indexOfProcess, arrivalTime, CPUBurstTime));
                            startTime = count;
                            temp = 0;
                            index++;
                        }
                    }while(true);
                }catch(Exception e){}
            }
        };
        thread.start();   
    }
    
    private int getBT(int index) {
        List<Jobs> bt = new ArrayList();
        bt.clear();
        bt.add(new Jobs(1, 20, 2));
        bt.add(new Jobs(2, 25, 1));
        bt.add(new Jobs(3, 12, 0));
        bt.add(new Jobs(4, 30, 0));
        bt.add(new Jobs(5, 5, 0));
        bt.add(new Jobs(6, 10, 3));
        bt.add(new Jobs(7, 6, 10));
        bt.add(new Jobs(8, 30, 25));
        bt.add(new Jobs(9, 50, 21));
        bt.add(new Jobs(10, 20, 6)); 
        
        return bt.get(index - 1).getCPUBurstTime();
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
    
    private void updateStatus(JTable table, int index, int count) {
        DefaultTableModel model =(DefaultTableModel) table.getModel();
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumn col6 = table.getColumnModel().getColumn(5);
        col6.setCellRenderer(dtcr);
        
        DecimalFormat df2 = new DecimalFormat(".##");
        
        count += 1;
        double status = 0;
        System.out.println(count + "\t" + index);
        if (index == 1) {status = (float) count / 137 * 100;}
        else if (index == 2) {status = (float) count / 131 * 100;}
        else if (index == 3) {status = (float) count / 72 * 100;}
        else if (index == 4) {status = (float) count / 184 * 100;}
        else if (index == 5) {status = (float) count / 19 * 100;}
        else if (index == 6) {status = (float) count / 96 * 100;}
        else if (index == 7) {status = (float) count / 53 * 100;}
        else if (index == 8) {status = (float) count / 193 * 100;}
        else if (index == 9) {status = (float) count / 208 * 100;}
        else if (index == 10) {status = (float) count / 143 * 100;}
        
        if (status < 100) {
            model.setValueAt(df2.format(status) + "%", index - 1, 5);
        }
        else {
            System.out.println(index + "\t" + count + "\t" + status);
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
