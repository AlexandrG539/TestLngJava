import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;


public class Group {
    // При выводе строк должны соблюдаться два дополнительных условия: строки должны быть
    // отсортированы, дубликатов быть не должно. TreeSet отвечает этим требованиям.
    private final TreeSet<SplittedString> stringStorage = new TreeSet<>();
    //Объект хранит все ссылки на себя
    private final LinkedList<GroupLink> links = new LinkedList<>();

    public int groupSize() {return stringStorage.size();}
    public void addString(String[] str) {
        stringStorage.add(new SplittedString(str));
    }
    public void addGroup(Group other) {
        this.stringStorage.addAll(other.stringStorage);
    }
    public void addLink(GroupLink link) {
        links.add(link);
    }
    public void giveLinksToOther(Group other) {
        Iterator<GroupLink> it = links.iterator();
        while (it.hasNext()) { //Для всех GroupLink
            GroupLink link = it.next();
            link.setGroup(other); //Теперь GroupLink хранит ссыылку на другую группу
            other.addLink(link); //И находится в списке другой группы
            it.remove();
        }
    }
    public void printGroup(BufferedWriter bw, int number) throws IOException {
        bw.write("Группа " + number + "\n");
        for (SplittedString str: stringStorage) {
            String[] string = str.getStr();
            for (int i = 0; i < string.length; i++) {
                if (i != 0)
                    bw.write(";");
                bw.write(string[i]);
            }
            bw.write("\n");
        }
    }
}
