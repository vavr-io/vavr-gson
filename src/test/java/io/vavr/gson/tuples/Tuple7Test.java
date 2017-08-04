package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple7;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple7Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple7<?, ?, ?, ?, ?, ?, ?> t = Tuple.of(1, 2, 3, 4, 5, 6, 7);
        assert gson.toJson(t).equals("[1,2,3,4,5,6,7]");
    }

    @Test
    public void deserialize() {
        Tuple7<Integer, Float, Double, String, Integer, Integer, Integer> t =
                gson.fromJson("[1,2,3,4,5,6,7]", new TypeToken<Tuple7<Integer, Float, Double, String, Integer, Integer, Integer>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
        assert t._4.equals("4");
        assert t._5.equals(5);
        assert t._6.equals(6);
        assert t._7.equals(7);
    }
}
