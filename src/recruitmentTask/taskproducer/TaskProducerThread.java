package recruitmenttask.taskproducer;

import recruitmenttask.FirstQueue;
import recruitmenttask.Task;

/**
 * @author Rafał Zawadzki
 */
public class TaskProducerThread implements Runnable {
    @Override
    public void run() {
        TaskProducer tProducer = new TaskProducer();
        tProducer.setQueue(FirstQueue.getInstance());
        while (true) {
            Task tmpTask = tProducer.addToQueue();

            //if queue was full
            if (tmpTask == null) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
