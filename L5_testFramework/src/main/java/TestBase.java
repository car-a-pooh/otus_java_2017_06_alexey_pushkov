import annotations.AfterClass;
import annotations.BeforeClass;

/**
 * Created by carapooh on 09.07.2017.
 */
public class TestBase {
    private static int passed = 0, failed = 0;

    public static void addPassed(){
        passed++;
    }

    public static void addFailed(){
        failed++;
    }

    @BeforeClass
    public void startTestMessage(){
        System.out.println("Testing started...");
    }

    @AfterClass
    public void printTestStatistics(){
        System.out.println("Testing done. Passed: " + passed + ". Failed: " + failed + ".");
    }
}
