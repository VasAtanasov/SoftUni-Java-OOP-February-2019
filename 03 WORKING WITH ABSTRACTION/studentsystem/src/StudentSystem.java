import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public boolean contains(String name) {
        return this.repo.containsKey(name);
    }

    public void save(String name, Student student) {
        this.repo.putIfAbsent(name, student);
    }

    public Student getByName(String name) {
        return this.repo.get(name);
    }
}
