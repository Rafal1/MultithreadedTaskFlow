import java.util.Queue;

/**
 * @author Rafał Zawadzki
 */
public class FirstQueueSingleton {
    private static volatile FirstQueueSingleton firstGlobalQueue = null;

    private FirstQueueSingleton() {}

    public static FirstQueueSingleton getInstance() {
        if(firstGlobalQueue == null) {
            synchronized (FirstQueueSingleton.class) {
                if(firstGlobalQueue == null){
                    firstGlobalQueue = new FirstQueueSingleton();
                }
            }
        }
        return firstGlobalQueue;
    }

    

}
