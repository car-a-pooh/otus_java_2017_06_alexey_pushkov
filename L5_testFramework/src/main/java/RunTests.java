import annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by carapooh on 09.07.2017.
 */
public class RunTests {

    public static void main(String[] args) throws Exception {

        for (String arg : args) {
            List<Method> beforeClass = new ArrayList<>();
            List<Method> before = new ArrayList<>();
            List<Method> tests = new ArrayList<>();
            List<Method> after = new ArrayList<>();
            List<Method> afterClass = new ArrayList<>();
            Class<?> testClass = Class.forName(arg);
            Set<Method> methods = new HashSet<>(Arrays.asList(testClass.getDeclaredMethods()));
            for (Method method : testClass.getMethods()){
                methods.add(method);
            }
            for (Method method : methods) {
                if (method.isAnnotationPresent(BeforeClass.class)) {
                    beforeClass.add(method);
                }
                if (method.isAnnotationPresent(Before.class)) {
                    before.add(method);
                }
                if (method.isAnnotationPresent(Test.class)) {
                    tests.add(method);
                }
                if (method.isAnnotationPresent(After.class)) {
                    after.add(method);
                }
                if (method.isAnnotationPresent(AfterClass.class)) {
                    afterClass.add(method);
                }
            }
            for (Method method : beforeClass) {
                method.invoke(testClass.newInstance());
            }
            for (Method method : tests){
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;
                if (test.enabled()) {
                    Object object = testClass.newInstance();
                    boolean isInstanceOfTestBase = object instanceof TestBase;
                    for (Method beforeMethod : before) {
                        beforeMethod.invoke(object);
                    }
                    try {
                        method.invoke(object);
                        if (isInstanceOfTestBase) {
                            TestBase.addPassed();
                        }
                    } catch (Throwable t) {
                        if (isInstanceOfTestBase) {
                            TestBase.addFailed();
                        }
                    }
                    for (Method afterMethod : after) {
                        afterMethod.invoke(object);
                    }
                }
            }
            for (Method method : afterClass) {
                method.invoke(testClass.newInstance());
            }
        }
    }
}
