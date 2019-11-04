package htwb.ai.PauliHan;

import htwb.ai.MyTest;
import htwb.ai.TestObjekt;

import java.lang.annotation.*;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

//hier nur herum getestet was so geht und was nicht
public class MyTestExample {

	  public static void main( String[] args ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	    {
		  TestObjekt obj = new TestObjekt();

		  Class c = obj.getClass();		//oder TestObjekt.class;
//		  Method m = c.getDeclaredMethod(name, parameterTypes)	//besser eig
		  Method m = c.getMethod("testTwelve");
            System.out.println(m.toString());
//wichtig für rückgabecheck
		  if(m.getReturnType() == String.class) {	//boolean.class
			  System.out.println("Rückgabewert ist String");
		  }
		  Method[] m2 = c.getMethods();
//wichtig für ergebnis der methode
		  System.out.println("Ergebnis von Methode testOne: " + m2[1].invoke(obj));


//wichtig für parameter checken
		  Parameter[] ps = m2[1].getParameters();

		  if(ps.length==0)
			  System.out.println("Parameter länge ist null");
//		  System.out.println(ps[0] +" "+  ps[1]);


		  System.out.println("result of method with invoke: "+m2[1].invoke(obj));

		  if(Modifier.isPublic(m2[1].getModifiers())) {
			  String x = Modifier.toString(m2[0].getModifiers());
			  System.out.println("this method is: " + x);
			  if(x.equals("public"))
				  System.out.println("yes, its public");
		  }


		  if(m2[1].getParameterCount() == 0) {	//wichtig
			  System.out.println("Parametercount works");
		  }

		  MyTest an = m.getAnnotation(MyTest.class);
//		  MyTest mt = (MyTest) an;
//		  System.out.println(an.testString());

		  System.out.println(m2[0] + " + " + m2[1] + " + "+ m2[2]);
		  readAnnotationOn(m2[0]);

	    }

	  public static void readAnnotationOn(AnnotatedElement element)
	   {
	      try
	      {
	         System.out.println("\n Finding annotations on " + element.getClass().getName());
	         Annotation[] annotations = element.getAnnotations();
	         System.out.println("eine anno: " + annotations[0]);
	         for (Annotation annotation : annotations)
	         {
//wichtig für checken ob mytest vorhanden
	            if (annotation instanceof MyTest)
	            {
	            	MyTest x = (MyTest) annotation;
	            	System.out.println("testString :" + x.testString());
	            	System.out.println("testInt :" + x.testInt());
	            }
	         }
	      } catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	   }

}
