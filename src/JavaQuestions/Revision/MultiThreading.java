package JavaQuestions.Revision;

public class MultiThreading {

    //Using thread class
    static class UsingClass{
        static class Thread1 extends Thread{
            Thread1(String name) {
                super(name);
            }
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Inside thread " + Thread1.currentThread().getName()+ " : " +i); //currentthread gives you name of current thread
                }
            }
        }
        public static void main(String[] args) {
            System.out.println("Main is starting");
            Thread thread1=new Thread1("Thread 1");
            //thread1.setDaemon(true); //Here call this method to mark the thread as daemon thread. Program execution completes when all user threads' execution got complete and it does not wait for daemon thread to complete
            thread1.start();
            System.out.println("Main is exiting");
        }
    }

    static class UsingInterface{
        static class Thread2 implements Runnable{
            @Override
            public void run() {
                System.out.println("Inside thread 2 : " + Thread.currentThread().getName());
            }
        }
        public static void main(String[] args) {
            Thread thread2 = new Thread(new Thread2(), "thread2");
            thread2.start();
        }
    }

}
