package zk.zkLock.testLock;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
public class TestLock {


//
//    public static void main(String[] args) {
//
//        final  RedisLock lock = new RedisLock();
//
//        lock.setHost("192.168.201.206");
//        lock.setPort(6371);
//        lock.setLockTimeOut(1000);
//        lock.setPerSleep(10000);
//
//
//
//        Thread th = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long l = 0;
//                try {
//                    l = lock.lock("11223");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock("11223",l);
//
//                }
//
//                System.out.println("------------1----------------"+l);
//            }
//        });
//
//
//
//        Thread th1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long l = 0;
//                try {
//                    l = lock.lock("112233");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("------------2---------------"+l);
//            }
//        });
//
//
//        Thread th2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long l = 0;
//                try {
//                    l = lock.lock("112233");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("------------3----------------------"+l);
//            }
//        });
//
//
//        Thread th3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long l = 0;
//                try {
//                    l = lock.lock("112233");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("------------4----------------"+l);
//            }
//        });
//
//
//        Thread th4 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                long l = lock.tryLock("112233");
//
//                System.out.println("------------5--------------------"+l);
//            }
//        });
//
//        th.start();
//        th1.start();
//        th2.start();
//        th3.start();
//        th4.start();
//    }
}
