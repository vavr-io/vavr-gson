package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple2Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple2<?, ?> t = Tuple.of(1, 2);
        assert gson.toJson(t).equals("[1,2]");
    }

    @Test
    public void deserialize() {
        Tuple2<Integer, Float> t =
                gson.fromJson("[1,2]", new TypeToken<Tuple2<Integer, Float>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
    }
}
