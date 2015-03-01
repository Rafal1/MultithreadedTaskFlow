package recruitmenttask.taskproducer;

import recruitmenttask.PropertiesEnum;
import recruitmenttask.SingletonCoverQueue;
import recruitmenttask.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * @author Rafał Zawadzki
 */
public class TaskProducer {
    private SingletonCoverQueue<Task> queue = null;

    private Task generateTask() {
        String rdString = generateRandomTasksString();
        return new Task(rdString);
    }

    private String generateRandomTasksString() {
        Integer maxStringSize = getLengthOfTasksStringProperty();
        char[] outputTasksString = new char[maxStringSize];
        String subAlphabetSigns = "-+/*";
        String subAlphabetNumbers = "0123456789";
        String alphabet = subAlphabetNumbers + subAlphabetSigns;
        Integer N = alphabet.length();
        Integer numbersN = subAlphabetNumbers.length();
        Random r = new Random();
        Random nr = new Random();

        for (int i = 0; i < maxStringSize; i++) {
            outputTasksString[i] = alphabet.charAt(r.nextInt(N));
        }
        char first = outputTasksString[0];
        char last = outputTasksString[maxStringSize - 1];
        for (int i = 0; i < subAlphabetSigns.length(); i++) {
            if (first == subAlphabetSigns.charAt(i)) {
                outputTasksString[0] = subAlphabetNumbers.charAt(nr.nextInt(numbersN));
            }
            if (last == subAlphabetSigns.charAt(i)) {
                outputTasksString[maxStringSize - 1] = subAlphabetNumbers.charAt(nr.nextInt(numbersN));
            }
        }
        outputTasksString = removeDoubleSpecialChars(outputTasksString, subAlphabetSigns, subAlphabetNumbers);
        return String.valueOf(outputTasksString);
    }

    public Task addToQueue() {
        Task t = generateTask();
        Task returnedTask;
        try {
            returnedTask = queue.offer(t);
        } catch (NullPointerException e) {
            return null;
        }
        return returnedTask;
    }

    private Integer getLengthOfTasksStringProperty() {
        Properties prop = new Properties();
        InputStream input = null;
        Integer maxQueueSize = null;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
            String tmpMax = prop.getProperty(PropertiesEnum.MAX_LENGTH_OF_TASKS_STRING.toString());
            maxQueueSize = Integer.parseInt(tmpMax);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return maxQueueSize;
    }

    private char[] removeDoubleSpecialChars(char[] randomString, String subAlphabetSigns, String subAlphabetNumbers) {
        Boolean isSpecialBefore = false;
        Random nr = new Random();
        for (int i = 0; i < randomString.length; i++) {
            for (int j = 0; j < subAlphabetSigns.length(); j++) {
                if (randomString[i] == subAlphabetSigns.charAt(j)) {
                    if (isSpecialBefore) {
                        randomString[i] = subAlphabetNumbers.charAt(nr.nextInt(subAlphabetNumbers.length()));
                        isSpecialBefore = false;
                        break;
                    } else {
                        isSpecialBefore = true;
                        break;
                    }
                }
            }
        }
        return randomString;
    }

    public void setQueue(SingletonCoverQueue<Task> queue) {
        this.queue = queue;
    }

}
