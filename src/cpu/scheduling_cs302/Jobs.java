/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu.scheduling_cs302;

/**
 *
 * @author EJ
 */
public class Jobs  {
    
    private int numberOfProcess, arrivalTime, CPUBurstTime, indexOfProcess;
    
    public Jobs(int indexOfProcess, int CPUBurstime, int arrivalTime){
        this.indexOfProcess = indexOfProcess;
        this.arrivalTime = arrivalTime;
        this.CPUBurstTime = CPUBurstime;
    }
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    
    public int getCPUBurstTime(){
        return this.CPUBurstTime;
    }
    
    public int getIndexOfProcess(){
        return this.indexOfProcess;
    }
}
