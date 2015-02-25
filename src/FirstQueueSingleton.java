import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Rafał Zawadzki
 */
public class FirstQueueSingleton {
    private static volatile LinkedBlockingQueue<Task> realQueue = null;
    private static volatile Boolean isInitialized = false;
    private static volatile Integer maxSizeOfQueue;

    private FirstQueueSingleton() {
    }

    public static LinkedBlockingQueue createInstance(Integer size) {
        if (realQueue == null) {
            synchronized (FirstQueueSingleton.class) {
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

    public Boolean isHalfFull() {
        if (isInitialized) {
            Integer remCapacity = new Integer(realQueue.remainingCapacity());
            Float EmptyPercentage = remCapacity.floatValue() / maxSizeOfQueue.floatValue();
            if (EmptyPercentage < 0.5) {
                return true;
            }
        } else {
            System.out.println("FirstQueue hasn't been initilized yet.");
            return null;
        }
        return false;
    }
}
