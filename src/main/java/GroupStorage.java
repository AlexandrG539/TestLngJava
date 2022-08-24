import java.util.LinkedList;
import java.util.TreeMap;

public class GroupStorage {
    //Хранилище групп представляет собой список Map-деревьев (по одному дереву для кажого столбца входных данных).
    //Ключем выступает строка, значением - ссылка на объект, хранящий группу, к которой эта строка принадлежит.
    private LinkedList<TreeMap<String, GroupLink>> groupStorage = new LinkedList<>();
    private int groupCount = 0; // Хранит количество групп, в которых больше одного элемента

}
