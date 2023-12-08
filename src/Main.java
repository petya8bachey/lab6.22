import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static final float coff = 1.5F;
    public static void main(String[] args) throws Exception {
        /*
        Имеется N камней веса А1,А2,...,АN.
        Необходимо разбить их на две кучи таким образом, чтобы веса куч отличались не более чем в 1.5 раза.
        Если этого сделать нельзя, то указать это.
        Основная стратегия решения заключается в том, что каждый следующий камень кладется в кучу с меньшим текущим весом. При этом в первую кучу надо положить камень максимального веса. Покажем, что этого достаточно, чтобы гарантировать правильное решение задачи. По окончании распределения камней по кучам возможны 2 ситуации:
        1. Все камни попали во вторую кучу, а ее вес остался меньше половины веса первой кучи. Понятно, что в этом случае камни требуемым образом разбить нельзя, следовательно решения не существует.
        2. Случай 1. не верен. Тогда возможны следующие ситуации.
        а) Все камни попали во вторую кучу. В этом случае ясно, что веса куч отличаются не более чем на половину первой кучи, если вес первой кучи больше, или не более чем вес последнего камня, положенного во вторую кучу. В любом из этих случаев требуемое условие выполняется.
        б) В первую кучу попали и другие камни. Тогда ясно, что веса куч отличаются не более чем на вес самого тяжелого камня, кроме первого. Следовательно и в этом случае условие задачи выполняется.
        На начальном этапе в первую кучу кладется самый тяжелый камень, а во вторую - два следующих по весу камня. Для оставшихся камней реализуется описанная для задачи 2 стратегия.
         */

        File file = new File("src/text.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<Integer> arrayList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            arrayList.add(scanner.nextInt());
        }

        Collections.sort(arrayList);
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();

        int i = arrayList.size() - 1;
        array1.add(arrayList.get(i--));
        array2.add(arrayList.get(i--));
        array2.add(arrayList.get(i--));

        for (; i > 0; i--) {
            if (sumOfList(array2) >= sumOfList(array1)) {
                array1.add(arrayList.get(i));
            } else {
                array2.add(arrayList.get(i));
            }
        }
        if (((float)
                Math.max(sumOfList(array1), sumOfList(array2)) /
                Math.min(sumOfList(array1), sumOfList(array2))) < coff) {
            System.out.println(array1);
            System.out.println(sumOfList(array1));
            System.out.println(array2);
            System.out.println(sumOfList(array2));
        } else {
            System.out.println("=(");
        }
    }

    public static int sumOfList(ArrayList<Integer> arrayList) {
        int result = 0;
        for (Integer integer : arrayList) {
            result += integer;
        }
        return result;
    }
}