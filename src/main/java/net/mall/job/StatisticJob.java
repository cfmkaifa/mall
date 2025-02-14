/*
 *
 *
 *
 *
 */
package net.mall.job;

import java.util.Calendar;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.inject.Inject;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.mall.service.StatisticService;

/**
 * Job - 统计
 *
 * @author huanghy
 * @version 6.1
 */
@Lazy(false)
@Component
public class StatisticJob {

    @Inject
    private StatisticService statisticService;
    private ReentrantReadWriteLock STATISTIC_LOCK = new ReentrantReadWriteLock();

    /**
     * 收集
     */
    @Scheduled(cron = "${job.statistic_collect.cron}")
    public void collect() {
        if (STATISTIC_LOCK.writeLock().tryLock()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            statisticService.collect(year, month, day);
            STATISTIC_LOCK.writeLock().unlock();
        }
    }
}