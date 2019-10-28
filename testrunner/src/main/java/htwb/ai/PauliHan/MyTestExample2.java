package htwb.ai.PauliHan;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

//alle bedingungen hintereinander. vllt so richtig für abgabe
public class MyTestExample2 {

	public static void main(String[] args) {

		TestObjekt obj = new TestObjekt();
		Class clazz = obj.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		
		for(Method method : methods) 
		{
			Annotation[] annotations = method.getAnnotations();
			System.out.println("-Method: " + method.getName());
			for(Annotation annotation : annotations) 
			{
				if(annotation instanceof MyTest)
				{
					System.out.println("Annotation check");
					MyTest x = (MyTest) annotation;
//	            	System.out.println("testString :" + x.testString());
//	            	System.out.println("testString2 :" + x.testString2());
	            	
	            	String modifierString = Modifier.toString(method.getModifiers());
	            	if(modifierString.equals("public")) 
	            	{
						System.out.println("Modifier check");
	            		Parameter[] paramters = method.getParameters();
	            		if(paramters.length == 0) 
	            		{
	            			System.out.println("Parameter check");
	            			if(method.getReturnType() == boolean.class)
	            			{
	            				System.out.println("boolean check");
	            				try {
									boolean erg = (Boolean) method.invoke(obj);
									System.out.println(erg);
									if(erg == true)
										System.out.println("FERTIG");
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	            			}
	            		}
	            	}	
				}
			}
		}
	}
}
