package assignment0514.overflow;

/**
 * Created by Administrator on 2017/5/20.
 */
public class HeapOutOfMemory {
    public static void main(String[] args) {
        String[] strings = new String[100000000];
        strings[0] = "1234";
        for (int i = 1; i < strings.length; i++) {
            strings[i] = new String(" jdfkluiew") + strings[i - 1];
        }
    }
}
