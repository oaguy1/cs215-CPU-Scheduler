package cpuscheduler;

/**
 * This class represents the ThreadQueue abstract data type.
 * It creates a queue that holds Java Threads and lets them execute for a 
 * predefined time quantum.
 *
 * @author Jasper Boyd and David Robinson
 * @version 1.0
 */
import java.util.Queue;
import java.util.LinkedList;

public class ThreadQueue extends Thread{
    private int size; 
    private int time_quantum;
    private Queue<TestThread> queue;
    private final int DEFAULT_TIME_SLICE = 500;

    /**
     * Default constructor for ThreadQueue class
     */
    public ThreadQueue() {
        size = 0; 
        time_quantum = 1;
        queue = new LinkedList<TestThread>();
    }//ThreadQueue

    /**
     * Constructor for ThreadQueue class that allows for a desired
     * time quantum to be inputed
     * @param time_quantum int value representing the desired time quantum
     * @return void
     */
    public ThreadQueue(int time_quantum) {
        this.time_quantum = time_quantum;
        queue = new LinkedList<TestThread>();
    }//ThreadQueue

    /**
     * This method adds a TestThread object to the queue
     * @param thread The TestThread object to be added to the queue
     * @return void
     */
    public void add(TestThread thread) throws InterruptedException {
        size++; 
        thread.wait(time_quantum);
        thread.reset();
        queue.add(thread);
    }//add
    
    public boolean isEmpty(){ 
        return size > 0; 
    }

    //ITERATOR DOESN"T LIKE THE COMPILER
    /**
     * This method prints out the contents of the queue
     * @return void
     */
    /*public void printQueue() {
        for(TestThread i : queue.iterator()) {
            System.out.print(i + " ");
        }//for

        System.out.println();
    }//printQueue*/
    
    /**
     * 
     * 
     */
    public TestThread getNext(){
        TestThread answer; 
        
        return answer; 
    }

    /**
     * Queue lets each TestThread run for a time quantum before preempting it
     * with the next item in the queue. Once an item has finished executing, it
     * is reset and placed back on the queue.
     * @return void
     */
    public void run() {
        TestThread current;

        while(true) {
            try {
            while(!queue.isEmpty()) {
                current = queue.remove();
                current.signal();

                this.wait(DEFAULT_TIME_SLICE * time_quantum);

                if(current.isDone()) {
                    current.reset();
                }//if
                    
                queue.add(current);
            }//while
            } catch (InterruptedException e)
            {}//try/catch

        }//while
    }//run

}//ThreadQueue
