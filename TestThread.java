package cpuscheduler;

import java.util.*; 

/**
 * TestThread.java
 *
 * This thread is used to demonstrate how the scheduler operates. This thread
 * runs forever, periodically displaying its name.
 *
 * @author Jasper Boyd and David Robinson
 * @version 1.0
 */
class TestThread extends Thread {
    private Random r; 
    private boolean sleeping;
    private String name;
    private int burstTime;
    private int workDone;
    private static final int DEFAULT_BURST = 500;
    private static final int MEDIUM_BURST = 1000;
    private static final int LARGE_BURST = 3000; 

    /**
     * Basic constructor that allows user to input a desired name and time burst
     * for thread
     *
     * @param name The desired name for this instance of TestThread
     * @param bust_time The desired burst time for this instance of TestThread
     * @return TestThread object
     */
    public TestThread(String n) {
        r = new Random(); 
        this.name = n;
        this.burstTime = genBurstTime();
        sleeping = false;   
    }//TestThread
    
    private int genBurstTime(){ 
        int selector = r.nextInt(10); 
        switch (selector){
            case 0: 
                return calcLargeBurst(); 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
                return calcMediumBurst(); 
            default: 
                return calcDefaultBurst(); 
        }//switch
    }//genBurstTime()
    
    private int calcLargeBurst() { 
        int choice = r.nextInt(1); 
        if (choice == 0){ 
           return LARGE_BURST + r.nextInt(DEFAULT_BURST); 
        } else { 
           return LARGE_BURST - r.nextInt(DEFAULT_BURST);  
        }
    }
    
    private int calcMediumBurst() { 
        int choice = r.nextInt(1); 
        if (choice == 0){ 
           return MEDIUM_BURST + r.nextInt(DEFAULT_BURST); 
        } else { 
           return MEDIUM_BURST - r.nextInt(DEFAULT_BURST/2);  
        }
    }
    
    private int calcDefaultBurst() { 
        int choice = r.nextInt(1); 
        if (choice == 0){ 
           return MEDIUM_BURST + r.nextInt(DEFAULT_BURST/4); 
        } else { 
           return MEDIUM_BURST - r.nextInt(DEFAULT_BURST/4);  
        }
    }

    /**
     * @return String value containing the name of this instance
     */
    public String toString() {
        return name;
    }//TestThread
    
    public int burstTime(){ 
        return burstTime; 
    }

    public String name() {
        return name;
    }

    public boolean sleeping() {
        return sleeping;
    }
    
    public synchronized void startSleeping(){ 
        sleeping = true; 
    }
    
    public synchronized void stopSleeping(){ 
        sleeping = false; 
    }

    /**
     * @return boolean value of whether the thread has finished its work
     */
    public synchronized boolean isDone() {
        return !(workDone < burstTime);
    }//isFinished

    /**
     * Reset the work done by this thread
     *
     * @return void
     */
    public synchronized void reset() {
        System.out.println("resetting thread: " + this.name());
        workDone = 0;
        burstTime = genBurstTime();
    }//resetWork

    /**
     * This method has the thread do work until its burst time is finised
     *
     * @return void
     */
    public void run() {
        try {
            synchronized (this) {
                sleeping = true;
                wait();
                sleeping = false;
                while (workDone < burstTime) {
                    try {
                        //Prevent overusing CPU by sleeping briefly
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//try
                    workDone++;
                    while (sleeping){ 
                        wait(); 
                        sleeping = false; 
                    }
                }//while
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//try
    }//run
}//TestThread
