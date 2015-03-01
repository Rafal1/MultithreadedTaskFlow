package recruitmenttask.taskconsumer;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import recruitmenttask.SingletonCoverQueue;
import recruitmenttask.Task;

/**
 * @author Rafał Zawadzki
 */
public class TaskConsumer {
    private Task currentTask;
    private Double lastEquationResult = null;
    private SingletonCoverQueue<Task> queue = null;

    public Task consumeTask() {
        try {
            currentTask = queue.poll();
            computeResult();
            displayTasksResult();
        } catch (NullPointerException e) {
            return null;
        }
        return currentTask;
    }

    private void displayTasksResult() {
        System.out.println("Task's result: " + lastEquationResult);
    }

    private void computeResult() {
        String taskEquation = currentTask.getTasksString();
        Expression ex = new ExpressionBuilder(taskEquation).build();
        try {
            lastEquationResult = ex.evaluate();
        } catch (ArithmeticException e) { //division by 0
            lastEquationResult = 0.0;
        }
    }

    //created only for testing purposes (tests are required by client)
    public Double getLastEquationResult() {
        return lastEquationResult;
    }

    public void setQueue(SingletonCoverQueue<Task> q) {
        this.queue = q;
    }

}
