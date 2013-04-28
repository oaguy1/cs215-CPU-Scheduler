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

public class Scheduler extends Thread
{
    private CircularList queue;
    private int timeSlice;
    private static final int DEFAULT_TIME_SLICE = 1000; // 1 second

    public Scheduler() {
        timeSlice = DEFAULT_TIME_SLICE;
        queue = new CircularList();
    }

    public Scheduler(int quantum) {
        timeSlice = quantum;
        queue = new CircularList();
    }

    /**
     * adds a thread to the queue
     * @return void
     */
    public void addThread(Thread t) {
        queue.addItem(t);   
    }

    /**
     * this method puts the scheduler to sleep for a time quantum
     * @return void
     */
    private void schedulerSleep() {
        try {
            Thread.sleep(timeSlice);
        } catch (InterruptedException e) { };
    }

    public void run() {
        TestThread current;

        // set the priority of the scheduler to the highest priority
        this.setPriority(6);

        while (!queue.isEmpty()) {
            try {
                current = (TestThread)queue.getNext();

                if ( (current != null) && (current.isAlive()) ) {
                    System.out.println(" dispatching " + current);
                    current.setPriority(4);

                    schedulerSleep();

                    System.out.print("* * * Context Switch * * * ");
                    System.out.println(" preempting " + current);

                    current.setPriority(2);
                }

            } catch (NullPointerException e3) { } ;
        }
    }
}

