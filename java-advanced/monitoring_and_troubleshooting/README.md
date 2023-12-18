# Monitoring and troubleshooting

Prepare answers to following questions:
1. Which interface JDK tools use to connect to JVM locally?
   - JVM Tool Interface
2. What is difference between profiling and traceability?
   - profiling - statistics of performance metrics - is more general, wider view.
   - traceability - possibility of digging into individual steps of execution process.

## OutOfMemory (OOM) error troubleshooting
### Analyze heap dump
The memory leak is caused by infinitive adding objects to ArrayList `field` 
at com.epam.jmp.mat.heap.Process.execute (Process.java:27).

### OQL
Difference in syntax is around arrays notations:
In jhat we use `[Ljava.lang.Object;` instead of `java.lang.Object[]`

## Deadlock troubleshooting
1. `jstack -l 988`
```
...
Found one Java-level deadlock:
=============================
"Thread 1":
  waiting to lock monitor 0x0000028da0a371d0 (object 0x000000076bb23178, a java.lang.Object),
  which is held by "Thread 2"

"Thread 2":
  waiting to lock monitor 0x0000028da0a37470 (object 0x000000076bb23168, a java.lang.Object),
  which is held by "Thread 1"

Java stack information for the threads listed above:
===================================================
"Thread 1":
        at com.epam.jmp.mat.deadlock.SimulateDeadLock.method1(SimulateDeadLock.java:24)
        - waiting to lock <0x000000076bb23178> (a java.lang.Object)
        - locked <0x000000076bb23168> (a java.lang.Object)
        at com.epam.jmp.mat.deadlock.DeadLockMain$1.run(DeadLockMain.java:11)
"Thread 2":
        at com.epam.jmp.mat.deadlock.SimulateDeadLock.method2(SimulateDeadLock.java:44)
        - waiting to lock <0x000000076bb23168> (a java.lang.Object)
        - locked <0x000000076bb23178> (a java.lang.Object)
        at com.epam.jmp.mat.deadlock.DeadLockMain$2.run(DeadLockMain.java:18)

Found 1 deadlock.
```
2. `kill -3 988`
```
bash: kill: (988) - No such process
```
3. `jvisualvm` - Deadlock detected, content as above
4. `Ctrl + Break` - as above
5. `jcmd 988 Thread.print` - as above


## Remote JVM profiling
screenshoot

## Inspect a Flight Recording
```
java -jar -Xmx100m -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=dumponexit=true,filename=flight.jfr heap-1.0.0-SNAPSHOT.jar
Unrecognized VM option 'UnlockCommercialFeatures'
Error: Could not create the Java Virtual Machine.
Error: A fatal exception has occurred. Program will exit.
```


## jinfo
```
λ ./jinfo 10200
Java System Properties:
#Mon Dec 18 22:31:21 CET 2023
java.specification.version=17
sun.cpu.isalist=amd64
sun.jnu.encoding=Cp1250
java.class.path=simple-1.0.0-SNAPSHOT.jar
java.vm.vendor=Oracle Corporation
sun.arch.data.model=64
user.variant=
java.vendor.url=https\://java.oracle.com/
os.name=Windows 10
java.vm.specification.version=17
sun.java.launcher=SUN_STANDARD
user.country=PL
sun.boot.library.path=C\:\\Program Files\\Java\\jdk-17.0.5\\bin
sun.java.command=simple-1.0.0-SNAPSHOT.jar
jdk.debug=release
sun.cpu.endian=little
...
java.vm.info=mixed mode, sharing
java.vendor=Oracle Corporation
java.vm.version=17.0.5+9-LTS-191
sun.io.unicode.encoding=UnicodeLittle
java.class.version=61.0

VM Flags:
-XX:CICompilerCount=3 -XX:ConcGCThreads=1 -XX:G1ConcRefinementThreads=4 -XX:G1EagerReclaimRemSetThreshold=16 -XX:G1HeapRegionSize=2097152 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=167772160 -XX:MarkStackSize=4194304 -XX:MaxHeapSize=2654994432 -XX:MaxNewSize=1591738368 -XX:MinHeapDeltaBytes=2097152 -XX:MinHeapSize=8388608 -XX:NonNMethodCodeHeapSize=5832780 -XX:NonProfiledCodeHeapSize=122912730 -XX:ProfiledCodeHeapSize=122912730 -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:SoftMaxHeapSize=2654994432 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation

VM Arguments:
java_command: simple-1.0.0-SNAPSHOT.jar
java_class_path (initial): simple-1.0.0-SNAPSHOT.jar
Launcher Type: SUN_STANDARD
```