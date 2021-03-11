package com.szcinda.express.service.service;

import org.springframework.util.StopWatch;

public class SnowFlakeFactory {
    //工作机器ID和数据中心ID，范围从0-31，理论上最大支持1024个节点
    private static long workerId = 0;
    private static long dataCenterId = 0;

    private static volatile SnowFlakeFactory snowFlakeFactory = null;

    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private SnowFlakeFactory() {

    }

    //双重校验锁
    public static SnowFlakeFactory getInstance() {
        if (snowFlakeFactory == null) {
            synchronized (SnowFlakeFactory.class) {
                if (snowFlakeFactory == null) {
                    snowFlakeFactory = new SnowFlakeFactory();
                }
            }
        }
        return snowFlakeFactory;
    }

    public String nextId() {
        return Long.toOctalString(this.generateNextId());
    }


    private synchronized long generateNextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (dataCenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private void geranateNextId(String code) {
        long start = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlakeFactory snowFlakeFactory = SnowFlakeFactory.getInstance();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < 1000; i++) {
            String id = snowFlakeFactory.nextId();
            System.out.println("id:" + id);
        }
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("耗时：" + totalTimeMillis);
    }
}
