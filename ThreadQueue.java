package cpuscheduler;

/**
 * This class represents the ThreadQueue abstract data type. It creates a queue
 * that holds Java Threads and lets them execute for a predefined time quantum.
 *
 * @author Jasper Boyd and David Robinson
 * @version 1.0
 */
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadQueue extends Thread {

    private int name;
    private int size;
    private int timeQuantum;
    private boolean sleeping;
    private LinkedList<TestThread> queue;
    private final int DEFAULT_TIME_SLICE = 500;

    /**
     * Default constructor for ThreadQueue class
     */
    public ThreadQueue() {
        size = 0;
        timeQuantum = 1;
        queue = new LinkedList<TestThread>();
        sleeping = false;
    }//ThreadQueue

    /**
     * Constructor with name
     */
    public ThreadQueue(int n, int tq) {
        name = n;
        size = 0;
        timeQuantum = tq;
        queue = new LinkedList<TestThread>();
        sleeping = false;
    }//ThreadQueue

    /**
     * This method adds a TestThread object to the queue
     *
     * @param thread The TestThread object to be added to the queue
     * @return void
     */
    public void add(TestThread thread) throws InterruptedException {
        size++;
        synchronized (thread) {
            thread.wait(timeQuantum);
        }
        queue.add(thread);
    }//add

    public boolean isEmpty() {
        return size < 0;
    }

    public int name() {
        return name;
    }

    public void startSleeping() throws InterruptedException {
        synchronized (this) {
            sleeping = true;
            this.wait(); 
            sleeping = false; 
        }
    }
    
    public synchronized void stopSleeping () {
        sleeping = false; 
    }

    //ITERATOR DOESN"T LIKE THE COMPILER
    /**
     * This method prints out the contents of the queue
     *
     * @return void
     */
    /*public void printQueue() {
     for(TestThread i : queue.iterator()) {
     System.out.print(i + " ");
     }//for

     System.out.println();
     }//printQueue*/
    /**
     * getNext() gets and removes the head of the queue
     *
     * @return the first of the queue;
     */
    public TestThread getNext() {
        return queue.pollFirst();
    }

    /**
     * Queue lets each TestThread run for a time quantum before preempting it
     * with the next item in the queue. Once an item has finished executing, it
     * is reset and placed back on the queue.
     *
     * @return void
     */
    public void run() {
        TestThread currentThread;
        try {
            this.startSleeping();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                while (!queue.isEmpty()) {
                    currentThread = queue.remove();

                    System.out.println("Dispatching " + currentThread.toString() + " from Queue: " + this.name);
                    System.out.println("Burst Time: " + currentThread.burstTime());

                    synchronized (currentThread) {
                        currentThread.notify();
                    }

                    synchronized (this) {
                        Thread.sleep(DEFAULT_TIME_SLICE * timeQuantum);
                    }
                    
                    synchronized(currentThread){ 
                        currentThread.startSleeping(); 
                    } 

                    if (currentThread.isDone()) {
                        System.out.println ("Completing " + currentThread.toString()); 
                        
                    } else { 
                        queue.add(currentThread);
                    }
                }//while
            } catch (InterruptedException e) {
            }//try/catch

        }//while
    }//run
}//ThreadQueue
