package htwb.ai;

import htwb.ai.MyTest;

import java.util.NoSuchElementException;

public class TestObjekt {

    @MyTest(testString = "hallo")
    public String testOne(int x, String y) {
        return "One";
    }

    @MyTest(testInt = 3)
    public int testTwo() {
        return 2;
    }

    @MyTest
    private void testThree() {
    }

    @MyTest
    public boolean testFour() {
        return true;
    }

    @MyTest
    public boolean testFive() {
        return false;
    }

    @MyTest
    public boolean testSix(int x) {
        return false;
    }

    @MyTest
    public String testSeven(int x, String y) {
        return "One";
    }

    @MyTest
    public boolean testEight() {
        throw new NullPointerException("Haha");
    }

    @MyTest
    public boolean testNine() throws IllegalAccessException {
        throw new IllegalAccessException("");


    }

    @MyTest
    public boolean testTen() throws InstantiationException {
        throw new InstantiationException("Haha");

    }

    @MyTest
    public boolean testEleven() {
        throw new NoSuchElementException("Jo");
    }

}
