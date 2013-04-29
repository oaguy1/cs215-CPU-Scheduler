CPU Scheduler Emulator
======================

Introduction
------------

This project seeks to emulate a multi-level CPU Scheduler for CS215 at Clark 
University. The original description of the assignment can be found 
[here](http://cs.clarku.edu/~fgreen/courses/cs215/CS215Assgn/CS215A5S13.html).

In this README, we hope to explain some of the choices we made when creating 
this scheduler. First, here are some basics:

* Our CPU has three queues
    * Each queue is assigned a time quantum
    * The *n+1* queue has twice the time quantum than queue *n*
* There is a maximum of six processes requesting to use the CPU
    * Each of these processes has a randomly set time burst between 1 - 10000
        nanoseconds
    * Processes age as they are places on the queue. Processes that reach an age
        25x that of their queue's time quantum are moved up a queue

How we did it
-------------

Details to come!
