package recruitmenttask.taskconsumer;

import junit.framework.TestCase;

public class TaskConsumerTest extends TestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    /*

        I encapsulate this method (is private, and I get parameters for it from class variables)
        so you have to do some changes in a code to run this test.

        1. Make computeResult method in TaskConsumer class public (It's a JUnit's requirement to generate a test method)
        2. Change return type for Double and return equationResult

        3. Comment a line with the taskEquation variable definition
        4. Define in method an example Expression String (e.x. 10+5) instead of taskEquation variable (comment this line)

        5. uncomment test's lines

        inject there an example queue!
        */
    public void testComputeResult() throws Exception {
//        TaskConsumer tc = new TaskConsumer();
//        Double re = tc.computeResult();
//        assertEquals("Results are not equal", 15.0, re);
    }

}
