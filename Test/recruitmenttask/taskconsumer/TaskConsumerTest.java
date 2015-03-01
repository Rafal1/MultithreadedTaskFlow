package recruitmenttask.taskconsumer;

import junit.framework.TestCase;
import recruitmenttask.FirstQueue;
import recruitmenttask.SingletonCoverQueue;
import recruitmenttask.Task;

public class TaskConsumerTest extends TestCase {

    public void testComputeResultFullExample() throws Exception {
        FirstQueue.initQueue(1);
        SingletonCoverQueue<Task> q = FirstQueue.getInstance();
        Task gt = new Task("10+11-3");
        q.offer(gt);

        TaskConsumer tc = new TaskConsumer();
        tc.setQueue(q);
        tc.consumeTask();
        Double tResult = tc.getLastEquationResult();
        assertNotNull("Task's result is null", tResult);
        assertEquals("Results should be the same", 18.0, tResult);
    }

    /*
        I encapsulate this method (is private, and I get parameters for it from class variables)
        so you have to do some changes in a code to run this test.

        1. Make computeResult() method in TaskConsumer class public (It's a JUnit's requirement to generate a test method)
        2. Change return type for Double and add line: return equationResult at the bottom of the method;

        3. Comment a line with the taskEquation variable definition
        4. Define in method an example Expression String (e.x. 10+5) instead of taskEquation variable (comment this line)

        5. uncomment test's lines the following method
    */
    public void testComputeResult() throws Exception {
//        TaskConsumer tc = new TaskConsumer();
//        Double re = tc.computeResult();
//        assertEquals("Results are not equal", 15.0, re);
    }

}
