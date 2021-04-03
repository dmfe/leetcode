import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Sample
 */
public class Sample {
    public static void main(String[] args) {

        List<Data> nullList = null;

        List<Data> lst1 = List.of(
                new Data("111", "data one", "d_one"),
                new Data("222", "data two", "d_two")
        );

        List<Data> lst2 = List.of(
                new Data("111", "my data one", "dt_one"),
                new Data("222", "my data two", "dt_two"),
                new Data("333", "my data three", "dt_three")
        );

        List<Data> mergedList = new ArrayList<>(Stream.of(nullList, lst1)
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .collect(Collectors.toMap(Data::getId,
                        Function.identity(), (d1, d2) -> d2))
            .values());

        System.out.println(lst1);
        System.out.println(mergedList);
    }

    static class Data {

        private String id;
        private String name;
        private String shortName;

        public Data(String id, String name, String shortName) {
            this.id = id;
            this.name = name;
            this.shortName = name;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "Data[id:" + id +
                ", name: " + name +
                ", shortName: " + shortName + "]";
        }
    }
}
