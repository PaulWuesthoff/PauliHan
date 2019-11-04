package htwb.ai.PauliHan;


import htwb.ai.MyTest;
import htwb.ai.TestObjekt;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

//alle bedingungen hintereinander. vllt so richtig für abgabe
public class TestRunner {

    public static void main(String[] args) {
        // System.out.println(args[0]);
        System.out.println(myTestRunner(args[0]));
    }

    public static String myTestRunner(String classname) {
        String output = "------- TEST RESULTS FOR MyClassTest -------\n";
        TestObjekt obj = new TestObjekt();    //obj.getClass();
        Class clazz = getClassOfName(classname);
        
        if(checkMethodsOfClass(clazz)) {

        	Method[] methods = getMethodsOfClass(clazz);

        	for (Method method : methods) {
	            Annotation[] annotations = method.getAnnotations();

	            for (Annotation annotation : annotations) {
	                if (annotation instanceof MyTest) {
	                    output += "Result for " + method.getName() + ": ";
	                    output += checkIfPublic(method);
	                	output += checkParameters(method);

	                	if(!checkReturnType(method)) {
	                		output += "error due to not returning a boolean type\n";
	                	}

	                	if(checkReturnType(method)) {
		                	if(method.getParameters().length == 0) {
	                    		output += getReturn(method,clazz);
	                    	}
	                	}
	                }
	            }
        	}
        }else {
        	output += "error due to not having any methods in the class";
        }
        return output;
    }
    
    public static Class getClassOfName(String classname){
    	if(classname.isEmpty()){
            throw new IllegalArgumentException("The Classname can not be empty or Null !");
        }
        Class clazz = null;
        try {
            clazz = Class.forName("htwb.ai." + classname);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	return clazz;
    	
    }
    
    public static boolean checkMethodsOfClass(Class clazz) {
    	if(clazz.getDeclaredMethods().length == 0) {
    		return false;
    	}
    	return true;
    }
    
    public static Method[] getMethodsOfClass(Class clazz) {
//        if(classname.isEmpty()){
//            throw new IllegalArgumentException("The Classname can not be empty or Null !");
//        }
//        Class clazz = null;
//        try {
//            clazz = Class.forName("htwb.ai.PauliHan." + classname);
//        } catch (ClassNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
        Method[] methods = clazz.getDeclaredMethods();
        return methods;
    }
    
    public static String checkIfPublic(Method methodToCheck) {
    	String returningString = "";
    	if (!Modifier.toString(methodToCheck.getModifiers()).equals("public")) {
    		returningString = "error due to not being public\n";
    	} 		
         return returningString;   
    }
    
    public static String checkParameters(Method methodToCheck) {
        String returningString = "";
//        parameters = null;
        Parameter[] parameters = methodToCheck.getParameters();

        if (parameters != null) {
            if (parameters.length != 0) {
                returningString += "error due to having parameters\n";
            }
        }
        return returningString;
    }
    
    public static boolean checkReturnType(Method methodToCheck) {

        if(methodToCheck.getReturnType().equals(boolean.class)){
        	return true;
        }
        else return false;
    }
    
    public static String getReturn(Method methodToCheck, Class clazz){
        String returnString = "";
            boolean result = false;

            try {
                Object object = clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                result = (Boolean) methodToCheck.invoke(object);
            } catch (IllegalAccessException e) {
                return "error due to: " + e.getCause().getClass().getSimpleName()+"\n";
            } catch (InvocationTargetException e) {
              return  "error due to: " + e.getCause().getClass().getSimpleName()+"\n";
            } catch (InstantiationException e) {
                return "error due to: " + e.getCause().getClass().getSimpleName()+"\n";
            } catch (NoSuchMethodException e) {
                return "error due to: " + e.getCause().getClass().getSimpleName()+"\n";
            }
        if(result){
                returnString += "passed\n";
            }else if(!result){
                returnString += "failed\n";
            }
        return returnString;
    }
    
}
