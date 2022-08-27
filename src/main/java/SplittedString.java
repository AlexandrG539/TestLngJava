public class SplittedString implements Comparable<SplittedString> {

    private final String[] str;

    public SplittedString(String[] str) {
        this.str = str;
    }
    @Override
    public int compareTo(SplittedString other) {
        for (int i = 0; i < str.length; i++) {
            if (i == other.str.length) //Дошли до конца другой строки
                return 1; //Тогда первая больше
            String s1 = this.str[i].substring(1, this.str[i].length()-1); //Получаем значения строк без ковычек
            String s2 = other.str[i].substring(1, other.str[i].length()-1);
            int compRes = s1.compareTo(s2);
            if (compRes == 0)
                continue;
            return (compRes > 0) ? 1 : -1;
        }
        return 0;
    }
    public String[] getStr() {return str;}
}
