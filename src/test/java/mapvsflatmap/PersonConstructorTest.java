package mapvsflatmap;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class PersonConstructorTest {

    @Test
    public void personConstructors() {
        Stream.of("Spock", "Data")
                .map(Person::new)
                .forEach(System.out::println);

        Stream.of("Montgomery Scott", "Hikaru Sulu")
                .map(name -> name.split(" "))
                .map(Person::new)
                .forEach(System.out::println);

        Stream.of("James Tiberius Kirk", "Peter Quincy Taggert")
                .map(name -> name.split(" "))
                .map(Person::new)
                .forEach(System.out::println);
    }
}