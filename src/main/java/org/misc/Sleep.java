package org.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Sleep
{
    static int LIMIT = 124;

    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(160);
        executor.submit(() -> {
            ArrayList<byte[]> b = new ArrayList<>();
            Random r = new Random();

            for (int i = 0; i < LIMIT; i++)
            {
                b.add(null);
            }

            for (int i = 0; i < 1024000; i++)
            {
                byte[] a = new byte[1008 * 1000];
                b.set(r.nextInt(LIMIT), a);

                // System.out.println(i + "MB " + b.size());
            }
        });

        Thread.sleep(1010101011L);
    }

}
