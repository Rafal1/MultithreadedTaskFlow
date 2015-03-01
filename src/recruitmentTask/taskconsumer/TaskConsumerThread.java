package recruitmenttask.taskconsumer;

import recruitmenttask.FirstQueue;
import recruitmenttask.Task;

/**
 * @author Rafał Zawadzki
 */
public class TaskConsumerThread implements Runnable {
    @Override
    public void run() {
        Task t;
        TaskConsumer tConsumer = new TaskConsumer();
        tConsumer.setQueue(FirstQueue.getInstance());
        while (true) {
            t = tConsumer.consumeTask();

            //if queue is empty or sth went wrong
            if (t == null) {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
