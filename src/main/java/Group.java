import java.util.TreeSet;

public class Group {

    // При выводе строк должны соблюдаться два дополнительных условия: строки должны быть
    // отсортированы, дубликатов быть не должно. TreeSet отвечает этим требованиям.
    private TreeSet<SplittedString> stringStorage = new TreeSet<>();
    private int elemCount = 0;

    public void addString(SplittedString str) {
        stringStorage.add(str);
        elemCount++;
    }

    public int getElemCount() {return elemCount;}
}
