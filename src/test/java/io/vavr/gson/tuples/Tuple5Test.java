package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple5;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple5Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple5<?, ?, ?, ?, ?> t = Tuple.of(1, 2, 3, 4, 5);
        assert gson.toJson(t).equals("[1,2,3,4,5]");
    }

    @Test
    public void deserialize() {
        Tuple5<Integer, Float, Double, String, Integer> t =
                gson.fromJson("[1,2,3,4,5]", new TypeToken<Tuple5<Integer, Float, Double, String, Integer>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
        assert t._4.equals("4");
        assert t._5.equals(5);
    }
}
