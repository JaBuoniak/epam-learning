# Monitoring and troubleshooting

Prepare answers to following questions:
1. Which interface JDK tools use to connect to JVM locally?
   - JMX
2. What is difference between profiling and traceability?
   - profiling - statistics of performance metrics - is more general, wider view.
   - traceability - possibility of digging into individual steps of execution process.

## OutOfMemory (OOM) error troubleshooting
### Analyze heap dump
The memory leak is caused by infinitive adding objects to ArrayList `field` 
at com.epam.jmp.mat.heap.Process.execute (Process.java:27).

