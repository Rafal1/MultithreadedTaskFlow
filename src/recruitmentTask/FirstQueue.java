package recruitmenttask;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Rafał Zawadzki
 */
public class FirstQueue implements SingletonCoverQueue<Task> {
    private static FirstQueue firstQueueInstance;
    private static LinkedBlockingQueue<Task> realQueue = null;
    private static Boolean isInitialized = false;
    private static Integer maxSizeOfQueue;
    private Integer calculatedHalfCapacity = null;
    private Boolean isFilled = false;
    private static String wrongInitializeInfo = "FirstQueue hasn't been initilized yet.";

    private FirstQueue() {
    }

    public static void initQueue(Integer size) {
        if (realQueue == null) {
            synchronized (FirstQueue.class) {
                if (realQueue == null) {
                    realQueue = new LinkedBlockingQueue<>(size);
                    maxSizeOfQueue = size;
                    firstQueueInstance = new FirstQueue();
                    isInitialized = true;
                }
            }
        }
    }

    public synchronized static FirstQueue getInstance() {
        return firstQueueInstance;
    }

    public Task offer(Task t) {
        synchronized (this) {
            if (!checkInitialization()) {
                return null;
            }
            if (isFilled) {
                return null;
            }
            if (realQueue.offer(t)) {
                if (realQueue.remainingCapacity() == 0) { //after the last successful operation
                    isFilled = true;
                }
                return t;
            } else {
                return null;
            }
        }
    }

    public synchronized Task poll() {
        synchronized (this) {
            if (!checkInitialization()) {
                return null;
            }
            Task t = realQueue.poll();
            if (isHalfFull()) {
                isFilled = false;
            }
            if (t != null) {
                return t;
            }
            return null;
        }
    }

    private Boolean isHalfFull() {
        if (!checkInitialization()) {
            return null;
        }
        if (calculatedHalfCapacity == null) {
            Double halfCapacity = maxSizeOfQueue.doubleValue() / 2.0;
            halfCapacity = Math.ceil(halfCapacity); // if queue has an odd number as length
            calculatedHalfCapacity = halfCapacity.intValue();
        }
        Integer remCapacity = new Integer(realQueue.remainingCapacity());
        Integer elementsInQueue = maxSizeOfQueue - remCapacity;
        if (calculatedHalfCapacity.equals(elementsInQueue)) {
            return true;
        }
        return false;
    }

    private Boolean checkInitialization() {
        if (isInitialized) {
            return true;
        } else {
            System.out.println(wrongInitializeInfo);
            return false;
        }
    }

    public Boolean isFull() {
        if (realQueue.remainingCapacity() == 0) {
            return true;
        }
        return false;
    }

}
