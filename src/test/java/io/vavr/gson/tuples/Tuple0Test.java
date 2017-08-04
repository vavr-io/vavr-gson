package io.vavr.gson.tuples;

import com.google.gson.reflect.TypeToken;
import io.vavr.Tuple;
import io.vavr.Tuple0;
import io.vavr.gson.AbstractTest;
import org.junit.Test;

public class Tuple0Test extends AbstractTest {

    @Test
    public void serialize() {
        Tuple0 t = Tuple.empty();
        assert gson.toJson(t).equals("[]");
    }

    @Test
    public void deserialize() {
        Tuple0 t = gson.fromJson("[]", new TypeToken<Tuple0>(){}.getType());
        assert t != null;
    }
}
