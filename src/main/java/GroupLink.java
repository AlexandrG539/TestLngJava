
// Так как в процессе обработки возникает необходимость изменить ссылку на группу у множества элементов,
// используется "ссылка на ссылку", аналог указателя на указатель из языка C.
public class GroupLink {

    private Group group;

    public GroupLink(Group group) {
        this.group = group;
        this.group.addLink(this);
    }

    public void setGroup(Group group) {this.group = group;}

    public Group getGroup() {return group;}

    //Если 2 объекта ссылаются на один и тот же Group, то они равны (необходимо для исключения дубликатов).
    public boolean equals(Object other) {
        if (!(other instanceof GroupLink))
            return false;
        else
            return (((GroupLink) other).group == this.group);
    }
}
