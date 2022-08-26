import java.util.LinkedList;


//Класс занимается обработкой входящих строк, формированием критериев принадлежности к группе,
//распределением строк по хранилищам.
public class StringSorter {
    GroupStorage storage;
    public StringSorter(GroupStorage storage) {
        this.storage = storage;
    }
    public void handleString(String[] str) {
        //Получить список групп, к которым принадлежит строка.
        LinkedList<GroupLink> groups = storage.checkGroups(str);

    }

}
