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
    private int burst_time;
    private int work_done;

    /**
     * Basic constructor that allows user to input a desired name and
     * time burst for thread
     * @param name The desired name for this instance of TestThread
     * @param bust_time The desired burst time for this instance of TestThread
     * @return TestThread object
     */
    public TestThread(String name, int burst_time) {
        this.name = name;
        this.burst_time = burst_time;
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
    public boolean isFinished() {
        return !(work_done < burstTime);
    }//isFinished

    /**
     * This method has the thread do work until its burst time is finised
     * @return void
     */
    public void run() {
        while(work_done < burstTime) {
            try {
                //Prevent overusing CPU by sleeping briefly
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//try

            work_done++;
        }//while
    }//run

}//TestThread
