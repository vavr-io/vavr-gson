package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple1;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple1Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple1<?> t = Tuple.of(1);
        assert gson.toJson(t).equals("[1]");
    }

    @Test
    public void deserialize() {
        Tuple1<Integer> t =
                gson.fromJson("[1]", new TypeToken<Tuple1<Integer>>(){}.getType());
        assert t._1.equals(1);
    }
}
