package io.vavr.gson;

import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Option;
import org.junit.Test;

import java.lang.reflect.Type;

public class OptionTest extends AbstractTest {

    private static final Type OPT_TYPE = new TypeToken<Option<Integer>>(){}.getType();

    @Test(expected = JsonParseException.class)
    public void badJson1() {
        gson.fromJson("1", OPT_TYPE);
    }

    @Test(expected = JsonParseException.class)
    public void badJson2() {
        gson.fromJson("[\"defined\"]", OPT_TYPE);
    }

    @Test(expected = JsonParseException.class)
    public void badJson3() {
        gson.fromJson("[\"defined\",4,2]", OPT_TYPE);
    }

    @Test(expected = JsonParseException.class)
    public void badJson4() {
        gson.fromJson("[]", OPT_TYPE);
    }

    @Test
    public void serializeNone() {
        assert gson.toJson(Option.none()).equals("[\"undefined\"]");
    }

    @Test
    public void serializeSome() {
        assert gson.toJson(Option.some(42)).equals("[\"defined\",42]");
    }

    @Test
    public void deserializeSimpleType() {
        Option<?> opt = gson.fromJson("[\"defined\",2]", Option.class);
        assert opt.isDefined();
        assert opt.get() instanceof JsonPrimitive;
        assert ((JsonPrimitive) opt.get()).getAsInt() == 2;
    }

    @Test
    public void deserializeNone() {
        Option<Integer> opt = gson.fromJson("[\"undefined\"]", OPT_TYPE);
        assert !opt.isDefined();
    }

    @Test
    public void deserializeSome() {
        Option<Integer> opt = gson.fromJson("[\"defined\",2]", OPT_TYPE);
        assert opt.isDefined();
        assert opt.get() == 2;
    }

}
