package streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class CreatingStreams {
    public static void main(String[] args) {
        String names = String.join(",", "Gomez", "Morticia", "Wednesday", "Pugsley");
        System.out.println(names);

        String[] munsters = {"Herman", "Lily", "Eddie", "Marilyn", "Grandpa"};
        names = String.join(";", munsters);
        System.out.println(names);

        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(100)
                .collect(Collectors.toList());
        System.out.println(nums);

        Stream.iterate(LocalDate.now(), d -> d.plusMonths(1))
                .limit(12)
                .forEach(System.out::println);

        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .summaryStatistics();
        System.out.println(stats);

        List<String> bradyBunch = Arrays.asList("Greg", "Marcia", "Peter", "Jan", "Bobby", "Cindy");
        names = String.join(",", bradyBunch);
        System.out.println(names);

        List<Integer> ints = IntStream.range(10, 15)
                .boxed()
//              .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        System.out.println(ints);

        List<Long> longs = LongStream.rangeClosed(10, 15)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);

        Map<String, Integer> map = Stream.of("this", "is", "a", "list", "of", "strings")
                .collect(Collectors.toMap(s -> s, String::length));

        map.forEach((word, length) -> System.out.printf(
                "The length of %s is %d%n", word, length));

    }
}
