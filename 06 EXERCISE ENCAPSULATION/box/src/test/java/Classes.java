import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Classes {

    private Classes() {

    }

    public static Map<String, Class> allClasses;

    static {
        allClasses = new HashMap<>();
        try {
            initClasses();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initClasses() throws IOException, ClassNotFoundException {
        String path = System.getProperty("user.dir") + "/src/main";

        String[] a = Files.find(Paths.get(path),
                Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile())
                .map(p -> p.getFileName().toString())
                .map(s -> s.substring(0, s.lastIndexOf('.')))
                .toArray(String[]::new);

        for (String className : a) {
            Class<?> cls = Class.forName(className);
            allClasses.put(className, cls);
        }
    }
}
