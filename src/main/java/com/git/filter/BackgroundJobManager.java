/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.git.filter;

import com.git.TransferRequest.SmsManager;
import com.git.TransferRequest.TransferInitiate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Gold
 */
@WebListener
public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new ConfirmPaystackPayment(), 0, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new ThreadRunner(), 0, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new TransferInitiate(), 0, 5, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(new SmsManager(), 0, 5, TimeUnit.SECONDS);
//        //scheduler.scheduleAtFixedRate(new SomeHourlyJob(), 0, 1, TimeUnit.HOURS);
//        scheduler.scheduleAtFixedRate(new ThreadRunner(), 0, 5, TimeUnit.SECONDS);
//        scheduler.scheduleAtFixedRate(new ThreadRunnerEmail(), 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
        System.out.println("***Shutdown***");

    }

}
