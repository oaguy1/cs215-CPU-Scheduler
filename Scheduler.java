import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Scheduler.java
 *
 * This class is a simple round-robin scheduler.
 * The idea for this scheduler came from "Java Threads"
 * by Oaks and Wong (Oreilly, 1999).
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

public class Scheduler extends Thread {
    private ThreadQueue queue_0;
    private ThreadQueue queue_1;
    private ThreadQueue queue_2;
    private int timeSlice;
    private int totalCount; 
    private int currentThreadNumber; 
    private int count_0;
    private int count_1;
    private int count_2;
    private boolean sleeping; 
    private static final int DEFAULT_TIME_SLICE = 500; // 1/2 second
    private static final int QUEUE_ZERO_SLICES = 1;
    private static final int QUEUE_ONE_SLICES = 4;
    private static final int QUEUE_TWO_SLICES = 8;

    public Scheduler() {
        timeSlice = DEFAULT_TIME_SLICE;
        queue_0 = new ThreadQueue(0, QUEUE_ZERO_SLICES);
        queue_1 = new ThreadQueue(1, QUEUE_ONE_SLICES);
        queue_2 = new ThreadQueue(2, QUEUE_TWO_SLICES);
        queue_0.start(); 
        queue_1.start(); 
        queue_2.start(); 
        sleeping = false; 
        totalCount = 0; 
        currentThreadNumber = 0; 
    }//Scheduler

    public Scheduler(int quantum) {
        timeSlice = quantum;
        queue_0 = new ThreadQueue(0, QUEUE_ZERO_SLICES);
        queue_1 = new ThreadQueue(1, QUEUE_ONE_SLICES);
        queue_2 = new ThreadQueue(2, QUEUE_TWO_SLICES);
        queue_0.start(); 
        queue_1.start(); 
        queue_2.start(); 
        sleeping = false;
        totalCount = 0; 
        currentThreadNumber = 0; 
    }//Scheduler
    
    public synchronized void reduceThreadCount(){ 
        totalCount--; 
    }

    /**
     * adds a thread to the queue
     * @return void
     */
    public void addThread(TestThread t) throws InterruptedException {
        totalCount++; 
        currentThreadNumber++; 
        int bt = t.burstTime(); 
        int select; 
        if (bt < 750){ 
            select = 0; 
        } else if (bt < 1250){ 
            select = 1; 
        } else { 
            select = 2; 
        }
        
        switch(select) {
            case 0:
                queue_0.add(t);
                break;
            case 1:
                queue_1.add(t);
                break;
            case 2:
                queue_2.add(t);
                break;
            default:
                System.err.println("Invalid queue number " + select);
        }//switch
    }//addThread

    /**
     * this method puts the scheduler to sleep for a time quantum
     * @return void
     */
    private void schedulerSleep() {
        try {
            synchronized(this)
            {
                  sleeping = true;
                  wait(timeSlice);
                  sleeping = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//try
    }//schedulerSleep


    public void run() {
        ThreadQueue currentQueue;

        this.setPriority(Thread.MAX_PRIORITY);

        while (!queue_0.isEmpty() || !queue_1.isEmpty() || !queue_2.isEmpty()) {
            try {
                if(count_0 < 4) {
                    currentQueue = queue_0; 
                    count_0++;
                } else if(count_1 < 4) {
                    currentQueue = queue_1;
                    count_1++;
                    if(count_1 != 4) {
                        count_0 = 0;
                    }//if
                } else if(count_2 < 4) {
                    currentQueue = queue_2;
                    count_2++;
                    if(count_2 != 4) {
                        count_1 = 0;
                    }//if
                } else {
                    currentQueue = queue_0;
                    count_0 = 1;
                    count_1 = 0;
                    count_2 = 0;
                }//if
                
                synchronized(currentQueue){ 
                    currentQueue.notify();
                }
                
                
                schedulerSleep();
                
                if (totalCount < 6){
                    TestThread toAdd = new TestThread(currentThreadNumber + 1); 
                    toAdd.start(); 
                    try {
                        this.addThread(toAdd);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (NullPointerException e3) { 
                e3.printStackTrace();
            }//try
        }//while
    }//run
}//Scheduler

