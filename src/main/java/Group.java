import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

public class Group {
    // При выводе строк должны соблюдаться два дополнительных условия: строки должны быть
    // отсортированы, дубликатов быть не должно. TreeSet отвечает этим требованиям.
    private LinkedList<String[]> stringStorage = new LinkedList<>();

    public void addString(String[] str) {
        if (!stringStorage.contains(str)) {
            stringStorage.add(str);
        }
    }
    public void addGroup(Group other) {
        this.stringStorage.addAll(other.stringStorage);
    }
}
