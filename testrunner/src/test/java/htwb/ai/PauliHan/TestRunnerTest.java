package htwb.ai.PauliHan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerTest {

    @Test
    void mainNull() {
        TestRunner testRunner = new TestRunner();
        Assertions.assertThrows(NullPointerException.class, () -> {
            testRunner.main(new String[]{null});
        });
    }

    //public static Method[] getMethods(String classname) {
    //        Class clazz = null;
    //        try {
    //            clazz = Class.forName("htwb.ai.PauliHan." + classname);
    //        } catch (ClassNotFoundException e1) {
    //            // TODO Auto-generated catch block
    //            e1.printStackTrace();
    //        }
    //        Method[] methods = clazz.getDeclaredMethods();
    //
    //        return methods;
    //    }

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
    void checkModifiers() {
    }

    @Test
    void checkReturnType() {
    }

    @Test
    void myTestRunner() {
    }
}