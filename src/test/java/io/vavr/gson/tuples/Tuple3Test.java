package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple3Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple3<?, ?, ?> t = Tuple.of(1, 2, 3);
        assert gson.toJson(t).equals("[1,2,3]");
    }

    @Test
    public void deserialize() {
        Tuple3<Integer, Float, Double> t =
                gson.fromJson("[1,2,3]", new TypeToken<Tuple3<Integer, Float, Double>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
    }
}
