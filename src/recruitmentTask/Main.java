package recruitmenttask;

import java.io.*;
import java.util.Properties;

/**
 * @author Rafał Zawadzki
 */
public class Main {

    /*
    Data from the recruitment task:
    the number of taskProducers - 2
    the number of taskConcumers - 4
     */

    public static void main(String[] args) {
        initilizeProperties();
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (args.length == 1) {
            Integer tmpArgOne = validateQueueLength(args[0]);
            if (tmpArgOne > 0) {
                prop.setProperty(PropertiesEnum.MAX_QUEUE_LENGTH.name(), tmpArgOne.toString());
                initializeQueue(tmpArgOne);
            }
        } else {
            System.out.println("Error, the number of arguments is wrong!");
        }

        try {
            prop.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    private static void initializeQueue(Integer size) {
        FirstQueue.createInstance(size);
    }

    private static void initilizeProperties() {
        File pf = new File("config.properties");
        if (pf.exists()) {
            return;
        }
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("config.properties");

            // set the properties value
            Integer numberOfTaskProducers = 2;
            Integer numberOfTaskConsumers = 4;
            Integer maxQueueLength = 1000;
            Integer maxLengthOfTasksString = 1000;

            prop.setProperty(PropertiesEnum.NUMBER_OF_TASK_PRODUCERS.name(), numberOfTaskProducers.toString());
            prop.setProperty(PropertiesEnum.NUMBER_OF_TASK_CONSUMERS.name(), numberOfTaskConsumers.toString());
            prop.setProperty(PropertiesEnum.MAX_QUEUE_LENGTH.name(), maxQueueLength.toString());
            prop.setProperty(PropertiesEnum.MAX_LENGTH_OF_TASKS_STRING.name(), maxLengthOfTasksString.toString());

            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
