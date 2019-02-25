import java.util.HashMap;
import java.util.Map;

public class Classes {

    public static Map<String, Class> allClasses = new HashMap<>() {{
        put("Person", Person.class);
        put("Team", Team.class);
    }};
}
