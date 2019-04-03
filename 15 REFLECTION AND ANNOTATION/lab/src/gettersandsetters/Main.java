package gettersandsetters;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import reflection.Reflection;

@SuppressWarnings("Duplicates")
public class Main {

	public static void main(String[] args) {
		Method[] refClassMethods = Reflection.class.getDeclaredMethods();
		List<Method> getters = new LinkedList<>();
		List<Method> setters = new LinkedList<>();
		
		for (Method method : refClassMethods) {
			if (isGetter(method)) {
				getters.add(method);
			}
			
			if (isSetter(method)) {
				setters.add(method);
			}
		}
		
		System.out.println(getters
				.stream()
				.map(e -> String.format("%s will return %s",
						e.getName(),
						e.getReturnType().getSimpleName()))
				.collect(Collectors.joining(System.lineSeparator())));
		
		System.out.println(setters
				.stream()
				.map(e -> String.format("%s and will set field of %s",
						e.getName(),
						e.getParameterTypes()[0].getSimpleName()))
				.collect(Collectors.joining(System.lineSeparator())));
	}
	
	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get")) {
			return false;
		}
		
		if (method.getParameterTypes().length > 0) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		
		if (!(method.getParameterTypes().length == 1)) {
			return false;
		}
		
		return true;
	}

}
