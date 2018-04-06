//**********************************************************************
// Copyright (c) 2018 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package test;

import java.util.concurrent.atomic.AtomicLong;

public class MainTest
{

    static long test(long start, int number, int times)
    {
        AtomicLong l = new AtomicLong(start);
        long total = 0;
        for (int j = 0; j < times; j++)
        {
            long time = System.currentTimeMillis();
            for (int i = 0; i < number; i++)
            {
                long l2 = l.incrementAndGet();
                if (l2 < 0)
                {
                    l2 = l2 ^ 0x8000000000000000L;
                }
                //System.out.println(l.get() + ": " + l2);
                processIteration(i);
            }
            time = System.currentTimeMillis() - time;
            System.out.println();
            System.out.println(String.format("Time %d: %d ms", j, time));
            total += time;
        }
        total = total / times;
        return total;
    }

    /**
     * @param i
     */
    private static void processIteration(int i)
    {
        if (i % 100 == 0)
            System.out.print(".");
        if (i % 100000 == 0)
            System.out.println();
    }

    static long test2(long start, int number, int times)
    {
        AtomicLong l = new AtomicLong(start);
        long total = 0;
        for (int j = 0; j < times; j++)
        {
            long time = System.currentTimeMillis();
            for (int i = 0; i < number; i++)
            {
                long l2 = l.incrementAndGet();
                if (l.get() == Long.MAX_VALUE)
                {
                    l.set(0);
                }
                //System.out.println(l.get() + ": " + l2);
                processIteration(i);
            }
            time = System.currentTimeMillis() - time;
            System.out.println(String.format("Time %d: %d ms", j, time));
            total += time;
        }
        total = total / times;
        return total;
    }

    public static void main(String[] args)
    {
        int times = 100;
        int number = 2000000;
        long start = Long.MAX_VALUE - 1000000;
        //long total1 = test(start, number, times);
        long total2 = test2(start, number, times);
        System.out.println();
        //System.out.println(String.format("Bitwise: %dx, %d -> %d ms", times, number, total1));
        System.out.println(String.format("Atomic: %dx, %d -> %d ms", times, number, total2));
    }
}
