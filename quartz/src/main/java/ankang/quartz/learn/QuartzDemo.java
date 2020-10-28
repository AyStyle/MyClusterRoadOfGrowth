package ankang.quartz.learn;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-10-28
 */
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException {
        // 1.创建爱你任务调度器
        final Scheduler scheduler = createScheduler();

        // 2.创建一个任务
        final JobDetail job = createJob();

        // 3.创建任务一个触发器
        final Trigger trigger = createTrigger();

        // 4.使用任务调度器根据时间触发器执行任务
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

    /**
     * 1.创建爱你任务调度器
     * @return
     */
    private static Scheduler createScheduler() throws SchedulerException {
        final SchedulerFactory factory = new StdSchedulerFactory();
        final Scheduler scheduler = factory.getScheduler();

        return scheduler;
    }
    /**
     * 2.创建一个任务
     */
    private static JobDetail createJob(){
      return JobBuilder.newJob(DemoJob.class).withIdentity("ankang","group").build();
    }

    /**
     * 3.创建任务一个触发器
     * @return
     */
    private static Trigger createTrigger(){
        final CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger","group")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?"))
                .build();

        return trigger;
    }

    public static class DemoJob implements Job{

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("execute job: " + System.currentTimeMillis());
        }
    }
}
