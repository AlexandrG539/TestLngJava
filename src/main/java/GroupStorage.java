import java.util.*;

public class GroupStorage {
    //Хранилище критериев представляет собой список Map-деревьев (по одному дереву для кажого столбца входных данных).
    //Ключем выступает строка, значением - ссылка на объект, хранящий группу, к которой эта строка принадлежит.
    private LinkedList<TreeMap<String, GroupLink>> valueStorage = new LinkedList<>();


    // Существующие группы хранятся для дальнейшей печати
    private LinkedList<Group> groupStorage = new LinkedList<>();

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
        Group second = secondLink.getGroup();
        //Необходимо объединить содержимое двух групп
        first.addGroup(second);
        //Затем заменить ссылку на группу во втором GroupLink
        secondLink.setGroup(first);
        //После чего удалить вторую группу из списка групп (на нее не должно остаться ссылок)
        groupStorage.remove(second);
    }
}
