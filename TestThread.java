/**
 * TestThread.java
 * 
 * This thread is used to demonstrate how the scheduler operates.
 * This thread runs forever, periodically displaying its name.
 *
 * @author Greg Gagne, Peter Galvin, Avi Silberschatz
 * @version 1.0 - July 15, 1999.
 * Copyright 2000 by Greg Gagne, Peter Galvin, Avi Silberschatz
 * Applied Operating Systems Concepts - John Wiley and Sons, Inc.
 */

import java.util.*;

class TestThread extends Thread
{
    private String name;
    private Random r;
    private int burstTime;

    public TestThread(String id, int burstTime) {
        name = id;
        r = new Random();
        this.burstTime = burstTime;
    }

    public String toString () {
        return name;
    }

    public void run() {
        /* 
         * The thread does something
         **/
        while (true) {
            for (int i = 0; i < burstTime; i++)
                try {
                    //Prevent overusing CPU by sleeping briefly
                    Thread.sleep(10);
                } catch (InterruptedException e) 
                {}
            ;
        }
    }
}
