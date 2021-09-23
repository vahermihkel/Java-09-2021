package ee.mihkel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Üks", "Kaks", "Kolm");
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        List<Number> number = Arrays.asList(1L, 2L, 3L);
        List<Boolean> booleans = Arrays.asList(true,false);
        List<Person> people = Arrays.asList(new Person(),new Person());

        System.out.println(GenericsController.getFirst(strings));
        System.out.println(GenericsController.getFirst(numbers));
        System.out.println(GenericsController.getFirst(booleans));
        System.out.println(GenericsController.getFirst(people));

        GenericsController.printList(strings);
        GenericsController.printList(numbers);
        GenericsController.printList(booleans);
        GenericsController.printList(people);

        GenericsController.sum(longs);
        GenericsController.sum(number);

//        GenericsController.printSuperList(longs);
        GenericsController.printSuperList(number);
    }
}
