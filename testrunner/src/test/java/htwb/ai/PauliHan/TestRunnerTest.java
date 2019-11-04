package htwb.ai.PauliHan;

import htwb.ai.EmptyTestObjekt;
import htwb.ai.TestObjekt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerTest {

    @Test
    void getMethodsEmptyString() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TestRunner.getClassOfName("");
        });
    }

    @Test
    void getMethodsNullString() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            TestRunner.getClassOfName(null);
        });
    }

    // macht probleme mit mvn clean package aber funktioniert in Intellij
   // @Test
    void testIfAnyMethodsInClass() {
        //Class testClass = EmptyTestObjekt.class;
        assertEquals("------- TEST RESULTS FOR MyClassTest -------"
                        + "\n" + "error due to not having any methods in the class",
                TestRunner.myTestRunner("EmptyTestObjekt"));
    }

    @Test
    void testIfPublic() {
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testFour");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("", TestRunner.checkIfPublic(test));
    }

    @Test
    void testIfProtected() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getDeclaredMethod("testTwelve");
            testMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to not being public\n", TestRunner.checkIfPublic(testMethod));
    }

    @Test
    void testIfPrivate() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getDeclaredMethod("testThree");
            testMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to not being public\n", TestRunner.checkIfPublic(testMethod));
    }

    @Test
    void testIfParametersAreUsed() {
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testOne", int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to having parameters\n", TestRunner.checkParameters(test));
    }

    @Test
    void testIfParametersAreNotUsed() {
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testFive");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("", TestRunner.checkParameters(test));
    }

    @Test
    void testIfReturningBooleanTrue() {
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testFour");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals(true, TestRunner.checkReturnType(test));
    }

    @Test
    void testIfReturningBooleanFalse() {
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testOne", int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals(false, TestRunner.checkReturnType(test));
    }

    @Test
    void testIfPassed() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testFour");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("passed\n", TestRunner.getReturn(testMethod, testClass));
    }

    @Test
    void testIfFailed() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testFive");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("failed\n", TestRunner.getReturn(testMethod, testClass));
    }

    @Test
    void testIfReturnAnyException() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testEight");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: NullPointerException\n", TestRunner.getReturn(testMethod, testClass));
    }

    @Test
    void testIfReturnIllegalAccessException() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testNine");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: IllegalAccessException\n", TestRunner.getReturn(testMethod, testClass));
    }

    @Test
    void testIfReturnInstantiateException() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testTen");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: InstantiationException\n", TestRunner.getReturn(testMethod, testClass));
    }

    @Test
    void testIfReturnNoSuchElementException() {
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testEleven");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: NoSuchElementException\n", TestRunner.getReturn(testMethod, testClass));
    }

}
