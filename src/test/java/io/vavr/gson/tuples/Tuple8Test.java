package io.vavr.gson.tuples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple8;
import io.vavr.gson.VavrGson;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tuple8Test {

    private static Gson gson;

    @BeforeClass
    public static void before() {
        GsonBuilder builder = new GsonBuilder();
        VavrGson.registerAll(builder);
        gson = builder.create();
    }

    @Test
    public void serialize() {
        Tuple8<?, ?, ?, ?, ?, ?, ?, ?> t8 = Tuple.of(1, 2, 3, 4, 5, 6, 7, 8);
        assert gson.toJson(t8).equals("[1,2,3,4,5,6,7,8]");
    }

    @Test
    public void deserialize() {
        Tuple8<Integer, Float, Double, String, Integer, Integer, Integer, Integer> t =
                gson.fromJson("[1,2,3,4,5,6,7,8]", new TypeToken<Tuple8<Integer, Float, Double, String, Integer, Integer, Integer, Integer>>(){}.getType());
        assert t._1.equals(1);
        assert t._2.equals(2f);
        assert t._3.equals(3.0);
        assert t._4.equals("4");
        assert t._5.equals(5);
        assert t._6.equals(6);
        assert t._7.equals(7);
        assert t._8.equals(8);
    }
}
