package htwb.ai.PauliHan;

import htwb.ai.TestObjekt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerTest {

    @Test
    void mainNull() {

        Assertions.assertThrows(NullPointerException.class, () -> {
            TestRunner.main(new String[]{null});
        });
    }

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

    @Test
    void myTestRunner() {
    }

    @Test
    void getClassOfName() {
    }

    @Test
    void checkMethodsOfClass() {
    }

    @Test
    void getMethodsOfClass() {
    }

    @Test
    void checkIfPublic() {
    }

    @Test
    void checkParameters() {
    }

    @Test
    void checkReturnType() {
    }

    @Test
    void getReturn() {
    }
    @Test
    void testIfPublic(){
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testFour");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("",TestRunner.checkIfPublic(test));
    }
    @Test
    void testIfParametersAreUsed(){
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testOne", int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to having parameters\n",TestRunner.checkParameters(test));
    }
    @Test
    void testIfReturningBoolean(){
        Class testClass = TestObjekt.class;
        Method test = null;
        try {
            test = testClass.getMethod("testOne", int.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals(false,TestRunner.checkReturnType(test));
    }
    @Test
    void testIfReturnTrue(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testFour");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("passed\n",TestRunner.getReturn(testMethod,testClass));
    }
    @Test
    void testIfReturnFalse(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testFive");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("failed\n",TestRunner.getReturn(testMethod,testClass));
    }
    @Test
    void testIfReturnAnyException(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testEight");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: NullPointerException\n",TestRunner.getReturn(testMethod,testClass));
    }
    @Test
    void testIfReturnIllegalAccessException(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testNine");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: IllegalAccessException\n",TestRunner.getReturn(testMethod,testClass));
    }
    @Test
    void testIfReturnInstantiateException(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testTen");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: InstantiationException\n",TestRunner.getReturn(testMethod,testClass));
    }
    @Test
    void testIfReturnNoSuchElementException(){
        Class testClass = TestObjekt.class;
        Method testMethod = null;
        try {
            testMethod = testClass.getMethod("testEleven");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertEquals("error due to: NoSuchElementException\n",TestRunner.getReturn(testMethod,testClass));
    }
}