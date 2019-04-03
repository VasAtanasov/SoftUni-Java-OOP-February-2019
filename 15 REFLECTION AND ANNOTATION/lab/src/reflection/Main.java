package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<Reflection> aClass = Reflection.class;
		System.out.println(aClass);
		System.out.println(aClass.getSuperclass());
		Class[] interfaces = aClass.getInterfaces();
		for (Class anInterface : interfaces) {
			System.out.println(anInterface);
		}
		
		Reflection ref = aClass.getDeclaredConstructor().newInstance();
		System.out.println(ref);
		
		Field[] ctor = Reflection.class.getDeclaredFields();
		
		for (Field constructor : ctor) {
			System.out.println(constructor.getName());
		}
	}

}