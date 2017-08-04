package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple4;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple4Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple4<?, ?, ?, ?> t = Tuple.of(1, 2, 3, 4);
        assert gson.toJson(t).equals("[1,2,3,4]");
    }

    @Test
    public void deserialize() {
        Tuple4<Integer, Float, Double, String> t =
                gson.fromJson("[1,2,3,4]", new TypeToken<Tuple4<Integer, Float, Double, String>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
        assert t._4.equals("4");
    }
}
