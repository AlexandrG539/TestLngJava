

public class SplittedString implements Comparable<SplittedString> {
    private final String[] data;
    public SplittedString (String str) {
        data = str.split(";");
    }

    @Override
    // Нужна сортировка по количеству непустых элементов.
    public int compareTo (SplittedString other) {
        int countA = this.countElems();
        int countB = other.countElems();
        if (countA == countB) {
            String strA = String.join("", this.data);
            String strB = String.join("", other.data);
            return strA.compareTo(strB);
        }
            
        return countA > countB ? 1 : -1;
    }
    
    private int countElems() { //подсчитываает количество непустых элементов
        if (this.data.length == 0)
            return 0;
        int count = 0;
        for (String str: data) {
            if (!str.equals("\"\""))
                count++;
        }
        return count;
    }
}
