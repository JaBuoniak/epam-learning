# Task 1 - Experiment
Create `HashMap<Integer, Integer>`. 

The first thread adds elements into the map, the other go along the given map and sum the values. 

Threads should work before catching `ConcurrentModificationException`. 

Try to fix the problem with `ConcurrentHashMap` and `Collections.synchronizedMap()`. 

What has happened after simple Map implementation exchanging? \
How it can be fixed in code? 

Try to write your custom `ThreadSafeMap` with synchronization and without. 

Run your samples with different versions of Java (6, 8, and 10, 11) and measure the performance. \
Provide a simple report to your mentor.
