package org.nsu.danilov;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {

    private final Queue<Runnable> tasks;
    private boolean isShutdown = false;

    public ThreadPool(int numThreads) {
        this.tasks = new LinkedList<>();
        List<Worker> workers = new LinkedList<>();

        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            if (isShutdown) {
                throw new IllegalStateException("Тредпул завершен");
            }

            tasks.add(task);
            tasks.notify();
        }
    }

    public void shutdown() {
        synchronized (tasks) {
            isShutdown = true;
            tasks.notifyAll();
        }
    }

    private class Worker extends Thread {
        public void run() {
            Runnable task;
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty() && !isShutdown) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (isShutdown && tasks.isEmpty()) {
                        break;
                    }

                    task = tasks.poll();
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.err.println("Ошибка при выполнении задачи: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(5);
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> System.out.println("Бамс"));
        }
        pool.shutdown();
    }
}
