package ee.mihkel;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Töötab");

//        while(false) {
//            System.out.println("Tere");
//        }


        List<String> massiiv = new ArrayList<>();
        massiiv.add("Üks");
        massiiv.add("Kaks");
        massiiv.add("Kolm");

        for (int i = 0; i < massiiv.size(); i++) {
            System.out.println(i + massiiv.get(i));
        }

        for ( String s : massiiv) {
            System.out.println(s); // sout
            Thread.sleep();
        }


    }
}
