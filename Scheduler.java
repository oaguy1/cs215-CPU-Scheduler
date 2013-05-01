package cpuscheduler;

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
    private int count_0;
    private int count_1;
    private int count_2;
    private boolean sleeping; 
    private static final int DEFAULT_TIME_SLICE = 500; // 1/2 second
    private static final int QUEUE_ZERO_SLICES = 1;
    private static final int QUEUE_ONE_SLICES = 4;
    private static final int QUEUE_TWO_SLICES = 8;

    public Scheduler() {
        this.timeSlice = DEFAULT_TIME_SLICE;
        this.queue_0 = new ThreadQueue();
        this.queue_1 = new ThreadQueue();
        this.queue_2 = new ThreadQueue();
        this.sleeping = false; 
    }//Scheduler

    public Scheduler(int quantum) {
        this.timeSlice = quantum;
        this.queue_0 = new ThreadQueue();
        this.queue_1 = new ThreadQueue();
        this.queue_2 = new ThreadQueue();
        this.sleeping = false;
    }//Scheduler

    /**
     * adds a thread to the queue
     * @return void
     */
    public void addThread(int queue, TestThread t) throws InterruptedException {
        switch(queue) {
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
                System.err.println("Invalid queue number " + queue);
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
        TestThread current;

        this.setPriority(Thread.MAX_PRIORITY);

        while (!queue_0.isEmpty() || !queue_1.isEmpty() || !queue_2.isEmpty()) {
            try {
         
                if(count_0 < 4) {
                    current = (TestThread)queue_0.getNext();
                    count_0++;
                } else if(count_1 < 4) {
                    current = (TestThread)queue_1.getNext();
                    count_1++;
                    if(count_1 != 4) {
                        count_0 = 0;
                    }//if
                } else if(count_2 < 4) {
                    current = (TestThread)queue_2.getNext();
                    count_2++;
                    if(count_2 != 4) {
                        count_1 = 0;
                    }//if
                } else {
                    current = (TestThread)queue_0.getNext();
                    count_0 = 1;
                    count_1 = 0;
                    count_2 = 0;
                }//if

                if ( (current != null) && (current.isAlive()) ) {
                    System.out.println(" dispatching " + current);
                    current.setPriority(4);

                    schedulerSleep();

                    System.out.print("* * * Context Switch * * * ");
                    System.out.println(" preempting " + current);

                    current.setPriority(2);
                }//if

            } catch (NullPointerException e3) { 
                e3.printStackTrace();
            }//try
        }//while
    }//run
}//Scheduler

