package highQualityMistakes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import reflection.Reflection;

@SuppressWarnings("Duplicates")
public class Main {
	public static void main(String[] args) {
		Field[] allFields = Reflection.class.getDeclaredFields();
		
		Arrays.sort(allFields, (a, b) -> a.getName().compareTo(b.getName()));
		
		for (Field field : allFields) {
			if (!isFieldPrivate(field)) {
				System.out.println(String.format("%s must be private!",
						field.getName()));
			}
		}
		
		Method[] allMethods = Reflection.class.getDeclaredMethods();
		Arrays.sort(allMethods, Comparator.comparing(Method::getName));

		List<Method> getters = new LinkedList<>();
		List<Method> setters = new LinkedList<>();
		
		for (Method method : allMethods) {
			if (isGetter(method)) {
				getters.add(method);
			}
			
			if (isSetter(method)) {
				setters.add(method);
			}
		}
		
		
		System.out.println();
		
		for (Method method : getters) {
			if (!isMethodPublic(method)) {
				System.out.println(String.format("%s have to be public!", 
						method.getName()));
			}
		}
		
		System.out.println();

		for (Method method : setters) {
			if (!isMethodPublic(method)) {
				System.out.println(String.format("%s have to be private!", 
						method.getName()));
			}
		}
		
		System.out.println();
		System.out.println("Your code is full of bugs. You don’t understand encapsulation man");
	}
	
	private static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		
		if (!(method.getParameterTypes().length == 1)) {
			return false;
		}
		
		return true;
	}

	private static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		
		if (method.getParameterTypes().length > 0) {
			return false;
		}
		
		return true;
	}

	public static boolean isFieldPrivate(Field field) {
		return Modifier.isPrivate(field.getModifiers());
	}
	
	public static boolean isMethodPublic(Method method) {
		return Modifier.isPublic(method.getModifiers());
	}

	public static boolean isMethodPrivate(Method method) {
		return Modifier.isPrivate(method.getModifiers());
	}
}
