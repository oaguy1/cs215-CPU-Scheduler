package cpuscheduler;

/**
 * TestThread.java
 * 
 * 
 */

import java.util.*;

class TestThread extends Thread
{
    private Scheduler sched; 
    private int number;
    private Random r;
    private int burstTime;
    
    public TestThread(int n, Scheduler s){ 
        this.sched = s; 
        this.number = n; 
        this.r = new Random(); 
        this.burstTime = calcBT();
    }

    public void run() {
        /* 
         * The thread does something
         **/
        while (true) {
            for (int i = 0; i < burstTime; i++) {
                try {
                    //Prevent overusing CPU by sleeping briefly
                    Thread.sleep(10);
                } catch (InterruptedException e) 
                {}
            }
            synchronized(sched)
            {
              sched.notify();
            };
        }
    }
    
    private int calcBT(){
        double answer; 
        int q = 100 - r.nextInt(50); 
        
        switch (this.number){
            case 1: 
                answer = q * 1.4; 
                break; 
            case 2: 
                answer = 2 * q * 1.4; 
                break; 
            case 3: 
                answer = 4 * q * 1.4; 
                break; 
            default://impossible case for compiler
                answer = q;
        }
        
        return (int) answer; 
    }
}
