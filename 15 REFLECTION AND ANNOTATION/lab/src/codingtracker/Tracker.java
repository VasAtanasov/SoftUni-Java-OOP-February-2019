package codingtracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tracker {
	@Author(name = "Pesho")
	public static void printMethodsByAuthor(Class<Tracker> claz) {
		Map<String, List<String>> methodsByAuthor = new HashMap<>();
		Method[] methods = claz.getDeclaredMethods();
		
		for (Method method : methods) {
			Author annotation = method.getAnnotation(Author.class);
			if (annotation != null) {
				methodsByAuthor.putIfAbsent(annotation.name(), new ArrayList<>());
				methodsByAuthor.get(annotation.name()).add(method.getName() + "()");
			}
		}
		
		for (Map.Entry<String, List<String>> kvp: methodsByAuthor.entrySet()) {
			System.out.print(kvp.getKey() + ": ");

			Collections.sort(kvp.getValue());
			System.out.println(kvp.getValue().stream().collect(Collectors.joining(", ")));
		}
	}

	@Author(name = "Pesho")
	public static void main(String[] args) {
		Tracker.printMethodsByAuthor(Tracker.class);
	}
}
