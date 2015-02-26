package recruitmenttask.taskproducer;

import recruitmenttask.PropertiesEnum;
import recruitmenttask.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;
import java.util.Random;

/**
 * @author Rafał Zawadzki
 */
public class TaskProducer {

    private Task generateTask() {
        String rdString = generateRandomTasksString();
        return new Task(rdString);
    }

    private String generateRandomTasksString() {
        Integer maxQueueSize = getLengthOfTasksStringProperty();
        char[] outputTasksString = new char[maxQueueSize];
        String subAlphabetSigns = "-+/*";
        String subAlphabetNumbers = "0123456789";
        String alphabet = subAlphabetNumbers + subAlphabetSigns;
        Integer N = alphabet.length();
        Integer numbersN = subAlphabetNumbers.length();
        Random r = new Random(N);
        Random nr = new Random(numbersN);

        for (int i = 0; i < maxQueueSize; i++) {
            outputTasksString[i] = alphabet.charAt(r.nextInt(N));
        }
        char first = outputTasksString[0];
        char last = outputTasksString[maxQueueSize - 1];
        for (int i = 0; i < subAlphabetSigns.length(); i++) {
            if (first == subAlphabetSigns.charAt(i)) {
                outputTasksString[0] = subAlphabetNumbers.charAt(nr.nextInt());
            }
            if (last == subAlphabetSigns.charAt(i)) {
                outputTasksString[maxQueueSize - 1] = subAlphabetNumbers.charAt(nr.nextInt());
            }
        }
        outputTasksString = removeDoubleSpecialChars(outputTasksString, subAlphabetSigns, subAlphabetNumbers);
        return outputTasksString.toString();
    }

    public void addToQueue(Queue<Task> q) {
        Task t = generateTask();
        q.offer(t);
        //todo access control
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
        Random nr = new Random(subAlphabetNumbers.length());
        for (int i = 0; i < randomString.length; i++) {
            for (int j = 0; j < subAlphabetSigns.length(); j++) {
                if (randomString[i] == subAlphabetSigns.charAt(j)) {
                    if (isSpecialBefore) {
                        randomString[i] = subAlphabetNumbers.charAt(nr.nextInt());
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

}
