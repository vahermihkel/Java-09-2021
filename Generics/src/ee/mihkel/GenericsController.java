package ee.mihkel;

import java.util.List;

public class GenericsController {
    public static <T> T getFirst(List<T> array) {
        if (!array.isEmpty()) {
            return array.get(0);
        } else {
            return null;
        }
    }

    // unbounded
    public static void printList(List<?> array) {
        for (Object o:array) {
            System.out.println(o);
        }
    }

    // lower bound
    public static void sum(List<? extends Number> array) {
        Number sum = 0;
        double sum2 = sum.doubleValue();
        for (Number n:array) {
            sum2 += n.doubleValue();
        }
    }

    // upper bound
    public static void printSuperList(List<? super Number> array) {
        for (Object o:array) {
            System.out.println(o);
        }
    }
}
