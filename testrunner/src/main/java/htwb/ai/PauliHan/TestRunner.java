package htwb.ai.PauliHan;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

//alle bedingungen hintereinander. vllt so richtig für abgabe
public class TestRunner {

    private static Parameter[] parameters;

    public static void main(String[] args) {
        // System.out.println(args[0]);
        System.out.println(myTestRunner(args[0]));
    }

    public static Method[] getMethods(String classname) {
        if(classname.isEmpty()){
            throw new IllegalArgumentException("The Classname can not be empty or Null !");
        }
        Class clazz = null;
        try {
            clazz = Class.forName("htwb.ai.PauliHan." + classname);
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();

        return methods;
    }

    public static String checkModifiers(Method methodToCheck) {
        String returningString = "";
        parameters = null;
        if (Modifier.toString(methodToCheck.getModifiers()).equals("public")) {
            parameters = methodToCheck.getParameters();
        } else {
            returningString += "error due to not being public\n";
        }

        if (parameters != null) {
            if (parameters.length != 0) {
                returningString += "error due to having parameters\n";
            }
        }

        return returningString;
    }

    public static String checkReturnType(Method methodToCheck, TestObjekt obj){
        String returnString = "";
        if(methodToCheck.getReturnType().equals(boolean.class)){
            boolean result = false;
            try {
                result = (Boolean) methodToCheck.invoke(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if(result){
                returnString += "passed\n";
            }else if(!result){
                returnString += "failed\n";
            }
        }else{
            returnString += "error due to not returning a boolean type\n";
        }

        return returnString;
    }

    public static String myTestRunner(String classname) {
        String output = "------- TEST RESULTS FOR MyClassTest -------\n";
        TestObjekt obj = new TestObjekt();    //obj.getClass();

        for (Method method : getMethods(classname)) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof MyTest) {
                    output += "Result for " + method.getName() + ": ";
                    output += checkModifiers(method);
//                    String modifierString = Modifier.toString(method.getModifiers());
//                    if (modifierString.equals("public")) {
//                        Parameter[] paramters = method.getParameters();
//                        if (paramters.length == 0) {
                    output += checkReturnType(method,obj);
//                    if (method.getReturnType() == boolean.class) {
//                        try {
//                            boolean erg = (Boolean) method.invoke(obj);
//                            if (erg == true) {
//                                output += "passed\n";
//                            } else {
//                                output += "failed\n";
//                            }
//                        } catch (IllegalAccessException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (IllegalArgumentException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    } else {
//                        output += "error due to not returning a boolean type\n";
//                    }
//                        } else {
//                            output += "error due to having parameters\n";
//                        }
//                    } else {
//                        output += "error due to not being public\n";
//                    }
                }
            }
        }
        return output;
    }
}
