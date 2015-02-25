package recruitmenttask;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Rafał Zawadzki
 */
public class FirstQueue {
    private static volatile LinkedBlockingQueue<Task> realQueue = null;
    private static volatile Boolean isInitialized = false;
    private static volatile Integer maxSizeOfQueue;

    private FirstQueue() {
    }

    public static LinkedBlockingQueue createInstance(Integer size) {
        if (realQueue == null) {
            synchronized (FirstQueue.class) {
                if (realQueue == null) {
                    realQueue = new LinkedBlockingQueue<Task>(size);
                    maxSizeOfQueue = size;
                    isInitialized = true;
                }
            }
        }
        return realQueue;
    }

    public LinkedBlockingQueue<Task> getInstance() {
        return realQueue;
    }

    public static Boolean isHalfFull() {
        if (isInitialized) {
            Integer remCapacity = new Integer(realQueue.remainingCapacity());
            Float EmptyPercentage = remCapacity.floatValue() / maxSizeOfQueue.floatValue();
            if (EmptyPercentage < 0.5) {
                return true;
            }
        } else {
            System.out.println("recruitmenttask.FirstQueue hasn't been initilized yet.");
            return null;
        }
        return false;
    }
}
