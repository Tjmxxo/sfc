package cc.xpress.plantimer;

/**
 * @Create By Tjmxxo
 */
public class Schedulers {
//    static StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
//    static Properties props = new Properties();
//
//    public static void endPlan(String jobName, String groupName, Date time, int planName) throws SchedulerException {
//        Scheduler scheduler = schedulerFactory.getScheduler(groupName + "start");
//        if (scheduler == null) {
//            scheduler = getScheduler(groupName + "start");
//        }
//        ends(jobName, groupName, time, planName, scheduler);
//    }
//
//    public static void startPlan(String jobName, String groupName, Date time, int planName) throws SchedulerException {
//        Scheduler scheduler = schedulerFactory.getScheduler(groupName + "end");
//        if (scheduler == null) {
//            scheduler = getScheduler(groupName + "end");
//        }
//        starts(jobName, groupName, time, planName, scheduler);
//    }
//
//    static void starts(String jobName, String groupName, Date time, int planName, Scheduler scheduler) throws SchedulerException {
//
//        JobDetail job = JobBuilder.newJob(StartSeat.class).withIdentity(jobName, groupName).usingJobData("planId",planName).build();
//
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", groupName).startAt(time).build();
//
//        scheduler.scheduleJob(job, trigger);
//        scheduler.start();
//
//    }
//
//    static void ends(String jobName, String groupName, Date time, int planName, Scheduler scheduler) throws SchedulerException {
//
//        JobDetail jobs = JobBuilder.newJob(OverSeat.class).withIdentity("r", groupName).usingJobData("planId",planName).build();
//
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", jobName).startAt(time).build();
//
//        scheduler.scheduleJob(jobs, trigger);
//        scheduler.start();
//
//    }
//
//    static synchronized Scheduler getScheduler(String schedulerName) throws SchedulerException {
//        props.put("org.quartz.scheduler.instanceName", schedulerName);
//
//        props.put("org.quartz.threadPool.threadCount", "10");
//
//        schedulerFactory.initialize(props);
//
//        return schedulerFactory.getScheduler();
//    }

}
