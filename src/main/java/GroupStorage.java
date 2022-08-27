import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GroupStorage {
    //Хранилище критериев представляет собой список Map-деревьев (по одному дереву для кажого столбца входных данных).
    //Ключем выступает строка, значением - ссылка на объект, хранящий ссылку на группу,
    //к которой эта строка принадлежит.
    private final LinkedList<TreeMap<String, GroupLink>> valueStorage = new LinkedList<>();

    //Существующие группы хранятся для дальнейшей печати
    private final LinkedList<Group> groupStorage = new LinkedList<>();

    //Количество групп, включающих в себя более одного элемента.
    private int bigGroupCount = 0;

    //Возвращает список групп, к которым пренадлежит строка.
    public LinkedList<GroupLink> checkGroups(String[] str) {
        LinkedList<GroupLink> result = new LinkedList<>();
        Iterator<TreeMap<String, GroupLink>> it = valueStorage.listIterator();
        for (String s : str) {
            if (it.hasNext()) { //Пока есть следующие Map с ключами
                TreeMap<String, GroupLink> map = it.next(); //Получить следующий Map
                if (map.containsKey(s)) { // Если в столбце есть ключ (строка)
                    GroupLink link = map.get(s); // получить GroupLink
                    if (!result.contains(link)) { // если нет дубликата
                        result.add(link); //занести в результат GroupLink
                    }
                }
            }
            else
                break;
        }
        return result;
    }

    public void addString(GroupLink groupLink, String[] str) {
        if (groupLink == null) { // Если нет группы, к которой принадлежит строка
            Group newGroup = new Group(); //То ее нужно создать
            groupStorage.add(newGroup); //И добавить в список групп
            groupLink = new GroupLink(newGroup);//Далле работаем с groupLink на новую группу
        }
        int prevSize = groupLink.getGroup().groupSize(); //Размер группы до добавления элемента
        // (элемент может не добавиться, так как нужно удалять дубликаты).
        groupLink.getGroup().addString(str); //Добавление строки в хранилище группы
        int size = groupLink.getGroup().groupSize();
        if (size == 2 && size != prevSize ) //Размер группы превысил 1 элемент
            bigGroupCount++;
        // Если в хранилище критериев не хватает столбцов - его надо расширить
        for (int i = str.length - valueStorage.size(); i > 0; i--) {
            TreeMap<String, GroupLink> toAdd = new TreeMap<>();
            valueStorage.add(toAdd);
        }
        //Обновление критериев
        Iterator<TreeMap<String, GroupLink>> it = valueStorage.iterator();
        for (String s: str) {
            TreeMap<String, GroupLink> map = it.next();
            if (s.equals("\"\"")) {
                continue;
            }
            if (!map.containsKey(s)) {
                map.put(s, groupLink);
            }
        }
    }
    //Соединить группы, к которым принадлежит строка в одну, вурнуть GroupLink на новую группу.
    public GroupLink mergeGroups(LinkedList<GroupLink> groups) throws Exception {
        Iterator<GroupLink> it = groups.iterator();
        GroupLink first;
        if (!it.hasNext()) {
            throw new Exception("Wrong function argument");
        }
        first = it.next();
        while (it.hasNext()) {
            GroupLink other = it.next();
            mergePair(first, other);
        }
        return first;
    }

    //Объединяет две группы, удаляет вторую группу из списка
    private void mergePair(GroupLink firstLink, GroupLink secondLink) {
        Group first = firstLink.getGroup();
        if (first.groupSize() == 1)
            bigGroupCount++; //Группа становится "большой" после слияния
        Group second = secondLink.getGroup();
        if (second.groupSize() > 1)
            bigGroupCount--; //"Большая" группа будет утеряна в ходе слияния
        //Необходимо объединить содержимое двух групп
        first.addGroup(second);
        //Затем заменить все ссылки
        secondLink.getGroup().giveLinksToOther(first);
        //После чего удалить вторую группу из списка групп (на нее не должно остаться ссылок)
        groupStorage.remove(second);
    }
    public void printResult() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
            out.write("Получившиееся число групп с более чем одним элементом: " + bigGroupCount + "\n");
            groupStorage.sort(Comparator.comparing(Group:: groupSize).reversed());
            int number = 1;
            for (Group group: this.groupStorage) {
                group.printGroup(out, number);
                number++;
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("IO error: " + e);
        }

    }
}
