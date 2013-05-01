package cpuscheduler;

/**
 * TestThread.java
 * 
 * This thread is used to demonstrate how the scheduler operates.
 * This thread runs forever, periodically displaying its name.
 *
 * @author Jasper Boyd and David Robinson
 * @version 1.0
 */

class TestThread extends Thread {
    private String name;
    private int burstTime;
    private int workDone;

    /**
     * Basic constructor that allows user to input a desired name and
     * time burst for thread
     * @param name The desired name for this instance of TestThread
     * @param bust_time The desired burst time for this instance of TestThread
     * @return TestThread object
     */
    public TestThread(String name, int burst_time) {
        this.name = name;
        this.burstTime = burstTime;
    }//TestThread

    /**
     * @return String value containing the name of this instance
     */
    public String toString () {
        return name;
    }//TestThread

    /**
     * @return boolean value of whether the thread has finished its work
     */
    public boolean isDone() {
        return !(workDone < burstTime);
    }//isFinished

    /**
     * Reset the work done by this thread
     * @return void
     */
    public void reset() {
        workDone = 0;
    }//resetWork

    /**
     * This method has the thread do work until its burst time is finised
     * @return void
     */
    public void run() {
        while(workDone < burstTime) {
            try {
                //Prevent overusing CPU by sleeping briefly
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//try

            workDone++;
        }//while

        this.notifyAll();
    }//run

}//TestThread
