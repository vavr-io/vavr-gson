package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple6;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple6Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple6<?, ?, ?, ?, ?, ?> t = Tuple.of(1, 2, 3, 4, 5, 6);
        assert gson.toJson(t).equals("[1,2,3,4,5,6]");
    }

    @Test
    public void deserialize() {
        Tuple6<Integer, Float, Double, String, Integer, Integer> t =
                gson.fromJson("[1,2,3,4,5,6]", new TypeToken<Tuple6<Integer, Float, Double, String, Integer, Integer>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
        assert t._4.equals("4");
        assert t._5.equals(5);
        assert t._6.equals(6);
    }
}
