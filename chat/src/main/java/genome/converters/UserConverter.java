package genome.converters;

import java.util.function.Function;

public class UserConverter {
    public static <T,V> V convert(T input, Function<T,V> function){
        return function.apply(input);
    }
}
