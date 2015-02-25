/**
 * @author Rafał Zawadzki
 */
public class Main {
    private static Integer MAX_QUEUE_LENGTH;
    private static Integer NUMBER_OF_TASK_PRODUCERS = 2;
    private static Integer NUMBER_OF_TASK_CONSUMERS = 4;

    /*
    Data from the recruitment task:
    the number of taskProducers - 2
    the number of taskConcumers - 4
     */

    public static void main(String[] args) {
        if (args.length == 1) {
            Integer tmpArgOne = validateQueueLength(args[0]);
            if (tmpArgOne > 0) {
                MAX_QUEUE_LENGTH = tmpArgOne;
                initializeQueue();
            }
        } else {
            System.out.println("Error, the number of arguments is wrong!");
        }
    }

    private static Integer validateQueueLength(String ql) {
        Integer tmpMaxQueueLength = -1;
        try {
            tmpMaxQueueLength = Integer.parseInt(ql);
        } catch (NumberFormatException e) {
            System.out.println("The first argument should be a number from the range from 1 to " + Integer.MAX_VALUE);
        }
        if (tmpMaxQueueLength > 0) {
            return tmpMaxQueueLength;
        }
        return null;
    }

    private static void initializeQueue() {


    }
}
