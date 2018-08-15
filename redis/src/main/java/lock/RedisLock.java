package lock;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description redis 实现分布式锁
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
@Deprecated
public class RedisLock {


    //public static final Logger logger = LoggerFactory.getLogger(RedisManagerProxy.class);


    //private RedisManager redisManager;

   // public RedisLock(RedisManager redisManager) {
       // this.redisManager = redisManager;
   // }

    /**
     * 得不到锁立即返回，得到锁返回设置的超时时间
     *
     * @param key
     * @return
     */
    public long tryLock(String key, long lockTimeOut) {   // TODO: 17/1/6 添加异常处理   时间单位毫秒

        long expireTime = 0; //得到锁后设置的过期时间，未得到锁返回0


        expireTime = System.currentTimeMillis() + lockTimeOut + 1;

//        if (redisManager.setnx(key, String.valueOf(expireTime)).intValue() == 1) {
//
//            return expireTime; //得到了锁返回
//        } else {
//            String curLockTimeStr = redisManager.get(key);
//
//            if (StringUtils.isBlank(curLockTimeStr) || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) { //判断是否过期 防止死锁
//
//                expireTime = System.currentTimeMillis() + lockTimeOut + 1;
//
//                curLockTimeStr = redisManager.getSet(key, String.valueOf(expireTime));
//
//                if (StringUtils.isBlank(curLockTimeStr) || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {  //仍然过期,则得到锁
//                    return expireTime;
//                } else {
//                    return 0;
//                }
//            } else {
//                return 0;
//            }
//        }

        return 0;
    }

    /**
     * 得到锁返回设置的超时时间，得不到锁等待
     *
     * @param key
     * @return
     * @throws InterruptedException
     */
    public long lock(Long key, long lockTimeOut) throws InterruptedException {  //perSleepTime 睡眠时间

//        logger.info("开始获取锁 key:{},阻塞时间:{}", key, lockTimeOut);
//
//        long startTime = System.currentTimeMillis();
//
//        long sleep = lockTimeOut / 10;
//
//        long expireTime = 0;//得到锁后设置的过期时间，未得到锁返回0
//
//        for (; ; ) {
//            expireTime = System.currentTimeMillis() + lockTimeOut + 1;
//
//            if (redisManager.setnx(key + "", String.valueOf(expireTime)) == 1) {
//                logger.info("获取锁成功 key:{},超时时间:{}", key, expireTime);
//                return expireTime; //得到了锁返回
//            } else {
//
//                logger.info("获取锁失败 key:{},超时时间:{}", key, expireTime);
//
//                String curLockTimeStr = redisManager.get(key + "");
//
//                if (StringUtils.isBlank(curLockTimeStr) || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {  //判断是否过期 防止死锁
//
//                    expireTime = System.currentTimeMillis() + lockTimeOut + 1;
//
//                    curLockTimeStr = redisManager.getSet(key + "", String.valueOf(expireTime));
//
//                    if (StringUtils.isBlank(curLockTimeStr) || System.currentTimeMillis() > Long.valueOf(curLockTimeStr)) {   //仍然过期,则得到锁
//                        logger.info("重新获取锁成功 key:{},超时时间:{}", key, expireTime);
//                        return expireTime;
//                    } else {
//                        Thread.sleep(sleep);
//                    }
//                } else {
//                    Thread.sleep(sleep);
//                }
//            }
//
//            if (lockTimeOut > 0 && ((System.currentTimeMillis() - startTime) >= lockTimeOut)) {
//                expireTime = 0;
//                return expireTime;
//            }
//        }
        return  0;

    }

    /**
     * 先判断自己运行时间是否超过了锁设置时间，是则不用解锁
     *
     * @param key
     * @param expireTime
     */
    public void unlock(String key, long expireTime) {
//        if (System.currentTimeMillis() - expireTime > 0) {
//            return;
//        }
//
//        String curLockTimeStr = redisManager.get(key);
//        if (StringUtils.isNotBlank(curLockTimeStr) && Long.valueOf(curLockTimeStr) > System.currentTimeMillis()) {
//            redisManager.del(key);
//        }
    }

}
