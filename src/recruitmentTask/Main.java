package recruitmenttask;

import recruitmenttask.taskconsumer.TaskConsumerThread;
import recruitmenttask.taskproducer.TaskProducerThread;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author Rafał Zawadzki
 */
public class Main {
    private static String configFileName = "config.properties";

    public static void main(String[] args) {
        initilizeProperties();

        Properties prop = new Properties();
        OutputStream output;
        InputStream input = null;
        try {
            input = new FileInputStream(configFileName);
            prop.load(input);
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
            System.out.println("You didn't give any argument.");
            System.out.println("If you hadn't specified the queue length value in the config file. The default would be 100.");
            Integer queueN = Integer.parseInt(prop.getProperty(PropertiesEnum.MAX_QUEUE_LENGTH.name()));
            initializeQueue(queueN);
        }
        try {
            output = new FileOutputStream(configFileName);
            prop.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer producersN = Integer.parseInt(prop.getProperty(PropertiesEnum.NUMBER_OF_TASK_PRODUCERS.name()));
        Integer consumersN = Integer.parseInt(prop.getProperty(PropertiesEnum.NUMBER_OF_TASK_CONSUMERS.name()));

        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < producersN; i++) {
            exec.execute(new TaskProducerThread());
        }

        // wait until the queue becomes full (it's a task requirement)
        Long startTime = System.currentTimeMillis();
        Long timeLimit = new Long(prop.getProperty(PropertiesEnum.FILLING_QUEUE_TIME_LIMIT.name()));
        while (true) {
            if (FirstQueue.getInstance().isFull()) {
                for (int i = 0; i < consumersN; i++) {
                    exec.execute(new TaskConsumerThread());
                }
                break;
            }

            Long currentTime = System.currentTimeMillis();
            Long spendTime = currentTime - startTime;
            if (spendTime >= timeLimit) {
                System.out.println("Time limit for filling queue has been reached.");
                break;
            }
        }

        try {
            input.close();
        } catch (IOException|NullPointerException e) {
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
        FirstQueue.initQueue(size);
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
            Integer maxQueueLength = 100;
            Integer maxLengthOfTasksString = 100;
            Long fillingQueueTimeLimit = Long.valueOf(1000 * 60 * 1); //mnutes in miliseconds

            prop.setProperty(PropertiesEnum.NUMBER_OF_TASK_PRODUCERS.name(), numberOfTaskProducers.toString());
            prop.setProperty(PropertiesEnum.NUMBER_OF_TASK_CONSUMERS.name(), numberOfTaskConsumers.toString());
            prop.setProperty(PropertiesEnum.MAX_QUEUE_LENGTH.name(), maxQueueLength.toString());
            prop.setProperty(PropertiesEnum.MAX_LENGTH_OF_TASKS_STRING.name(), maxLengthOfTasksString.toString());
            prop.setProperty(PropertiesEnum.MAX_LENGTH_OF_TASKS_STRING.name(), maxLengthOfTasksString.toString());
            prop.setProperty(PropertiesEnum.FILLING_QUEUE_TIME_LIMIT.name(), fillingQueueTimeLimit.toString());

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
