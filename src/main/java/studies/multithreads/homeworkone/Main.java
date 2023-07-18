package studies.multithreads.homeworkone;

public class Main {
    public static void main(String[] args) {
        ThreadGroup threads = new ThreadGroup("threads");
        final MyThread thread1 = new MyThread(threads, "Первый поток");
        final MyThread thread2 = new MyThread(threads, "Второй поток");
        final MyThread thread3 = new MyThread(threads, "Третий поток");
        final MyThread thread4 = new MyThread(threads, "Четвертый поток");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.sleep(15000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " попытка завершения во время сна");
        }
        threads.interrupt();
    }
}
