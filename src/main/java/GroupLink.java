
// Так как в процессе обработки возникает необходимость изменить ссылку на группу у множества элементов,
// используется "ссылка на ссылку", аналог указателя на указатель из языка C.
public class GroupLink {

    private Group group;

    public GroupLink(Group group) {this.group = group;}

    public void setGroup(Group group) {this.group = group;}

    public Group getGroup() {return group;}
}
