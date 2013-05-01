package cpuscheduler;

import java.util.Random;

/**
 * TestScheduler.java
 *
 */

public class TestScheduler
{
    public static void main(String args[]) {
        /**
         * This must run at the highest priority to ensure that
         * it can create the scheduler and the example threads.
         * If it did not run at the highest priority, it is possible
         * that the scheduler could preempt this and not allow it to
         * create the example threads.
         */
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Scheduler CPUScheduler = new Scheduler();

        TestThread t1 = new TestThread(1, CPUScheduler);
        t1.start();
        CPUScheduler.addThread(t1);

        TestThread t2 = new TestThread(2, CPUScheduler);
        t2.start();
        CPUScheduler.addThread(t2);

        TestThread t3 = new TestThread(3, CPUScheduler);
        t3.start();
        CPUScheduler.addThread(t3);

        CPUScheduler.start();

    }
}
