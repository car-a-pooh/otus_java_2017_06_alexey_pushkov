import annotations.After;
import annotations.Before;
import annotations.Test;

/**
 * Created by carapooh on 09.07.2017.
 */
public class FrameworkTest extends TestBase {

    @After
    public void afterAnnotationTest(){
        System.out.println("Method marked with annotation @After called.");
    }

    @Before
    public void beforeAnnotationTest(){
        System.out.println("Method marked with annotation @Before called.");
    }

    @Test
    public void testAnnotationTest1(){
        System.out.println("Method1 marked with annotation @Test called.");
    }

    @Test
    public void testAnnotationTest2() throws Exception {
        System.out.println("Method2 marked with annotation @Test called.");
        throw new Exception();
    }

    @Test
    public void testAnnotationTest3(){
        System.out.println("Method3 marked with annotation @Test called.");
    }

    @Test(enabled = false)
    public void testAnnotationTest4(){
        System.out.println("Method4 marked with annotation @Test called.");
    }
}
