package recruitmenttask.taskconsumer;

import junit.framework.TestCase;

public class TaskConsumerTest extends TestCase {

    /*

    I encapsulate this method (is private, and I get parameters for it from class variables)
    so you have to do some changes in a code to run this test.

    Make computeResult method in TaskConsumer class public (It's a JUnit's requirement to generate a test method)
    Change return type for Double and return equationResult

    Comment a line with the taskEquation variable definition
    Define in method an example Expression String (e.x. 10+5) instead of taskEquation variable (comment this line)
    Comment a line with the displayResult() method invocation

    */
    public void testComputeResult() throws Exception {
        TaskConsumer tc = new TaskConsumer();
        Double re = tc.computeResult();
        assertEquals("Results are not equal", 15.0, re);
    }

}
