package ru.otus.lesson05;

import ru.otus.lesson05.Services.CreateObjServ;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * VM options  -XX:+UseG1GC
 *
 */


public class StartGC {
    public static void main(String[] args) throws Exception{

        long size = 100000L;

        Benchmark benchmark = new Benchmark();

        CreateObjServ service = new CreateObjServ();

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        ObjectName name = new ObjectName("ru.otus.lesson05:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mBeanServer.registerMBean(mbean, name);

        mbean.setSize(size);

        try {
            service.createObj(mbean.getSize(), benchmark);
        } catch (OutOfMemoryError error) {
            System.out.println(error.getLocalizedMessage());
        }
        benchmark.getCurrentStatistics();
        benchmark.printStatisticsToTerminal();
    }
}
