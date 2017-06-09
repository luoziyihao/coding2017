package assignment0514.overflow;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;

/**
 * Created by Administrator on 2017/5/20.
 */
public class PermGenOutOfMemory {
    public static void main(String[] args) {
        //String[] strings=new String[100000000];
        Function[] functions = new Function[100000000];
        for (int i = 0; i < functions.length; i++) {
            int j = i;
            functions[i] = new Function() {
                @Override
                public Object apply(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j;
                }

                public Object apply2(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 1;
                }

                public Object apply3(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 2;
                }

                public Object apply4(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 3;
                }

                public Object apply5(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 4;
                }

                public Object apply6(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 5;
                }

                public Object apply7(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 6;
                }

                public Object apply8(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 7;
                }

                public Object apply9(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 8;
                }

                public Object apply10(Object o) {

                    return "aklfdjliausdfjlkadfnkalfuakdfnkauf" + j + 9;
                }
            };
        }
    }
}

class ClassGenerator {
    Path path = Paths.get("classgen");

    public void generateClass() {

    }
}