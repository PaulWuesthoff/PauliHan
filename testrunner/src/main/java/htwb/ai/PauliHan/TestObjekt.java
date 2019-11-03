package htwb.ai.PauliHan;

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

}
