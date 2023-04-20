package functionpackage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
/**
 * functionpackage      Name of the package in which the new file is created
 * giangthao              Current user system login name
 * 15/04/2023              Current system date
 * java_8_recipes      Name of the current project
 */
public class FirstNameExtractor {

        public static void main(String[] args) {
            List<String> names = new ArrayList<>();
            names.add("Doe, John");
            names.add("Smith, Jane");
            names.add("Johnson, Robert");

            Function<String, String> firstNameExtractor = (name) -> name.split(", ")[1];
            List<String> firstNames = map(names, firstNameExtractor);

            System.out.println("First names: " + firstNames);
        }

        public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
            List<R> result = new ArrayList<>();
            for (T element : list) {
                result.add(function.apply(element));
            }
            return result;
        }


}
