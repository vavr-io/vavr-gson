package io.vavr.gson.tuples;

import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple2;
import io.vavr.Tuple8;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class TupleNTest extends AbstractTest {

    @Test(expected = JsonParseException.class)
    public void badJson() {
        gson.fromJson("1", new TypeToken<Tuple2<Integer, Integer>>(){}.getType());
    }

    @Test(expected = JsonParseException.class)
    public void badArrayLength() {
        gson.fromJson("[1, 2, 3]", new TypeToken<Tuple2<Integer, Integer>>(){}.getType());
    }

    @Test(expected = JsonParseException.class)
    public void tooBigArray() {
        gson.fromJson("[1, 2, 3, 4, 5, 6, 7, 8, 9]", Tuple8.class);
    }

    @Test
    public void deserializeSimpleType() {
        Object obj = gson.fromJson("[1,2]", Tuple2.class);
        assert obj.getClass() == Tuple2.class;
        Tuple2<?, ?> t = (Tuple2<?, ?>) obj;
        assert t._1 instanceof JsonPrimitive;
        assert ((JsonPrimitive) t._1).getAsInt() == 1;
        assert t._2 instanceof JsonPrimitive;
        assert ((JsonPrimitive) t._2).getAsInt() == 2;
    }
}
