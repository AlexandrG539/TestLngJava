import java.util.LinkedList;


//Класс занимается обработкой входящих строк, формированием критериев принадлежности к группе,
//распределением строк по хранилищам.
public class StringSorter {
    GroupStorage storage;
    public StringSorter(GroupStorage storage) {
        this.storage = storage;
    }
    public void handleString(String[] str) throws Exception {
        //Получить список групп, к которым принадлежит строка.
        LinkedList<GroupLink> groups = storage.checkGroups(str);
        GroupLink groupLink = null; // объект, хранящий ссылку на группу, к которой принадлежит строка
        if (!groups.isEmpty()) {//Если строка принадлежит к существующим группам
            groupLink = storage.mergeGroups(groups); //Объединить группы в одну
        }
        //Добавить строку к соответствующей группе
        storage.addString(groupLink, str);
    }

}
